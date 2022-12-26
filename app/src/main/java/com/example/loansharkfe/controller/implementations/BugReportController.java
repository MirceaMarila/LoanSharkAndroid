package com.example.loansharkfe.controller.implementations;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loansharkfe.R;
import com.example.loansharkfe.constants.BugTypes;
import com.example.loansharkfe.dto.JwtResponse;
import com.example.loansharkfe.model.BugReport;
import com.example.loansharkfe.repository.implementations.LoanSharkBugReportRepository;
import com.example.loansharkfe.repository.interfaces.BugReportRepository;
import com.example.loansharkfe.util.NetworkingRunnable;
import com.example.loansharkfe.view.SignInActivity;

import java.util.List;

public class BugReportController {

    private final Context context;
    private final TextView errorMessageField;
    private final ProgressBarController progressBarController;
    private final AppCompatActivity activity;
    private BugReportRepository bugReportRepository;

    public BugReportController(Context context, TextView errorMessageField, List<View> all_elements, ProgressBar progressBar, AppCompatActivity activity) {
        this.context = context;
        this.errorMessageField = errorMessageField;
        this.progressBarController = new ProgressBarController(all_elements, progressBar);
        this.activity = activity;
        this.bugReportRepository = new LoanSharkBugReportRepository();
    }

    public void handleBug(Exception exception, String errorMessage, BugTypes bugType) {

        errorMessageField.setText(errorMessage);

        if (bugType == BugTypes.CODE) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View dialog_view = activity.getLayoutInflater().inflate(R.layout.bug_report_dialog, null);

            TextView errorMessageView = dialog_view.findViewById(R.id.errorMessageBugReportDialog);
            Button yes = dialog_view.findViewById(R.id.yesBtnBugReportDialog);
            Button no = dialog_view.findViewById(R.id.noBtnBugReportDialog);

            builder.setView(dialog_view);
            errorMessageView.setText(errorMessage);

            AlertDialog dialog = builder.create();
            dialog.setCancelable(true);
            dialog.show();

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        dialog.dismiss();

                        BugReport bugReport = new BugReport(exception);
                        if (bugReport.getClassName().equals(""))
                            bugReport.setClassName(context.getClass().getSimpleName());
                        Toast.makeText(context, context.getClass().getSimpleName(), Toast.LENGTH_LONG).show();

                        NetworkingRunnable bugReportRunnable = bugReportRepository.createSendErrorRunnable(bugReport);
                        Thread bugReportThread = new Thread(bugReportRunnable);

                        progressBarController.showProgressBar();
                        bugReportThread.start();
                        bugReportThread.join();
                        progressBarController.hideProgressBar();

                        if (bugReportRunnable.getException() == null)
                            Toast.makeText(context, bugReportRunnable.getGenericResponse().getResponseMessage(), Toast.LENGTH_LONG).show();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e){
                        e.printStackTrace();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }
    }
}
