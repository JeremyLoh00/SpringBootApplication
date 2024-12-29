package com.etiqajavatechnicalassessment.javaTechnicalAssessment.Exception;

import java.io.Serializable;

public class ResourceNotFoundException extends RuntimeException implements Serializable {

    // Add serialVersionUID to suppress the warning
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}