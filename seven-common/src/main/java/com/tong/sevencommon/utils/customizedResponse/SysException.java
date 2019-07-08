package com.tong.sevencommon.utils.customizedResponse;

public class SysException extends RuntimeException{
    public static final int EC_UNKNOWN = -1 ; // EC = Error Code
    public static final int EC_SUCCESS = 0 ;
    public static final int EC_CMD_FAILED = -2;
    public static final int EC_LOGIN_FAIL = -3;
    public static final int EC_CMD_PULSE_SECOND_FAILED = -4;
    public static final int EC_CMD_PULSE_FIRST_FAILED = -5;
    public static final int EC_PARSE_FAILED = -6;

    private int errorCode;
    private Object data;

    public SysException(String message, int errorCode) {
        this(message, errorCode,null);
    }

    public SysException(String message, int errorCode, Object data) {
        super(message);
        this.errorCode = errorCode;
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode (int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
