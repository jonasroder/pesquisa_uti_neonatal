package com.roderly.pesquisaneonatos.infra;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private String errorCode;
    private String traceId;

    public ErrorDetails(Date timestamp, String message, String details, String errorCode) {
        this(timestamp, message, details, errorCode, null);
    }

    public ErrorDetails(Date timestamp, String message, String details, String errorCode, String traceId) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
        this.traceId = traceId;
    }

    public Date getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
    public String getDetails() { return details; }
    public String getErrorCode() { return errorCode; }
    public String getTraceId() { return traceId; }
}

