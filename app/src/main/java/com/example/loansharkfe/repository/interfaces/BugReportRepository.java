package com.example.loansharkfe.repository.interfaces;

import com.example.loansharkfe.model.BugReport;
import com.example.loansharkfe.util.NetworkingRunnable;

public interface BugReportRepository {

    public NetworkingRunnable createSendErrorRunnable(BugReport bugReport);
}
