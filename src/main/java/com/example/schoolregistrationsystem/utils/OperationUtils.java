package com.example.schoolregistrationsystem.utils;

import com.example.schoolregistrationsystem.dto.GenericDto;

import java.util.List;

public class OperationUtils {

    private OperationUtils() {  }

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static final int FAIL_CODE = 500;

    public static GenericDto returnMessageHandling(Object object, int resultCode, boolean resultFlag, String resultMessage) {

        GenericDto genericDTO = new GenericDto();

        genericDTO.setResultData(object);
        genericDTO.setResultCode(resultCode);
        genericDTO.setResultFlag(resultFlag);
        genericDTO.setResultMessage(resultMessage);

        return genericDTO;
    }

    public static GenericDto returnMessageHandling(List<Object> object, int resultCode, boolean resultFlag, String resultMessage) {

        GenericDto genericDTO = new GenericDto();

        genericDTO.setResultData(object);
        genericDTO.setResultCode(resultCode);
        genericDTO.setResultFlag(resultFlag);
        genericDTO.setResultMessage(resultMessage);

        return genericDTO;
    }

}
