package com.roderly.microbiomelabufu.infra;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private String errorCode; // Novo campo

    // Atualize o construtor e os getters/setters conforme necessário
    public ErrorDetails(Date timestamp, String message, String details, String errorCode) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
    }

    // Getters e setters para errorCode
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

