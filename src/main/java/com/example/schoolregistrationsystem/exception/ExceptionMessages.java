package com.example.schoolregistrationsystem.exception;

public enum ExceptionMessages {

    THIS_RECORD_IS_ALREADY_EXIST("THIS RECORD IS ALREADY EXIST"),
    THIS_RECORD_IS_NOT_ALREADY_EXIST("THIS RECORD IS NOT EXIST"),
    THIS_REGISTRATION_ARE_DELETED("THIS REGISTRATION IS DELETED")
    ;

    private final String message;

    public String getMessage() {
        return message;
    }

    ExceptionMessages(String message) {
        this.message = message;
    }
    }
