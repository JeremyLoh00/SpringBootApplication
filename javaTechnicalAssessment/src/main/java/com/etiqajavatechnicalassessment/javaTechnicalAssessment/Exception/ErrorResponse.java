package com.etiqajavatechnicalassessment.javaTechnicalAssessment.Exception;

public class ErrorResponse {

    private int statusCode;
    private String message;

    // No-argument constructor
    public ErrorResponse() {
    }

    // Constructor with parameters
    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // Getter and Setter for statusCode
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    // Getter and Setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Optional: toString() method to make the error object more readable
    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
