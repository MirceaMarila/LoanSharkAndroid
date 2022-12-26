package com.example.loansharkfe.repository.implementations;

import com.example.loansharkfe.constants.HTTPMethods;
import com.example.loansharkfe.dto.GenericResponse;
import com.example.loansharkfe.repository.interfaces.BugReportRepository;
import com.example.loansharkfe.model.BugReport;
import com.example.loansharkfe.util.NetworkingRunnable;
import com.example.loansharkfe.util.Request;

public class LoanSharkBugReportRepository implements BugReportRepository {

    private final Request request = Request.getInstance();

    public LoanSharkBugReportRepository() {
    }

    public NetworkingRunnable createSendErrorRunnable(BugReport bugReport) {
        return new NetworkingRunnable() {
            @Override
            public void run() {      //DUMMY
                try{
//                    genericResponse = request.post(PathConfig.sendBugPath, bugReport);
                    genericResponse = new GenericResponse(201, "Error sent to the server.", HTTPMethods.POST, "body");
                } catch (Exception e){
                    exception = e;
                }
            }
        };
    }
}
