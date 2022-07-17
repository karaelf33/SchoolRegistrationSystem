package com.example.schoolregistrationsystem.exception;

public class CommonException extends BaseException{
    public CommonException(String message) {super(message);}

    public static CommonException existRecord(){
        return new CommonException(ExceptionMessages.THIS_RECORD_IS_ALREADY_EXIST.getMessage());
    }
    public static CommonException notExistRecord(){
        return new CommonException(ExceptionMessages.THIS_RECORD_IS_NOT_ALREADY_EXIST.getMessage());
    }
}
