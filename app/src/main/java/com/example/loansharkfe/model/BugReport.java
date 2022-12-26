package com.example.loansharkfe.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class BugReport {

    private String className;
    private String message;
    private String stackTrace;

    public BugReport(Exception exception) {

        String[] getClass = exception.getClass().toString().split(".");
        if (getClass.length != 0)
            this.className = getClass[getClass.length-1];
        else
            this.className = "";

        this.message = exception.getMessage();

        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        this.stackTrace = sw.toString();

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
