package com.example.schoolregistrationsystem.dto;

import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
@EqualsAndHashCode
public class GenericDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -6325709070873501087L;

    protected boolean resultFlag;
    protected String resultMessage;
    protected int resultCode;
    protected Object resultData;

    public boolean isResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
}
