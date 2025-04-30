package org.example.backend.databaseapi.application.exception;

public class ErrorInfo {
    public final String url;
    public final String razon;

    public ErrorInfo(StringBuffer url, Exception ex) {
        this.url = String.valueOf(url);
        this.razon = ex.getLocalizedMessage();
    }
}
