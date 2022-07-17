package com.example.schoolregistrationsystem.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends Exception{

    private final String message;
}
