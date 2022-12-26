package com.example.loansharkfe.controller.implementations;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class ProgressBarController {

    private final List<View> all_elements;
    private final ProgressBar progressBar;

    public ProgressBarController(List<View> all_elements, ProgressBar progressBar) {
        this.all_elements = all_elements;
        this.progressBar = progressBar;
    }

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
        for(View element:all_elements){
            element.setClickable(false);
        }
    }

    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
        for(View element:all_elements){
            element.setClickable(true);
        }
    }


}
