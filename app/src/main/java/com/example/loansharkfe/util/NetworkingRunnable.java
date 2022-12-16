package com.example.loansharkfe.util;

import com.example.loansharkfe.dto.GenericResponse;

public abstract class NetworkingRunnable implements Runnable{

    protected GenericResponse genericResponse;

    protected Exception exception;

    public NetworkingRunnable() {
    }

    public GenericResponse getGenericResponse() {
        return genericResponse;
    }

    public Exception getException() {
        return exception;
    }
}
