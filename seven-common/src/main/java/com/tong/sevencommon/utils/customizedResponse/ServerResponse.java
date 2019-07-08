package com.tong.sevencommon.utils.customizedResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ServerResponse<T> {
    private String errorMsg;
    private Integer code;
    private T obj;

    public ServerResponse() {
    }

    public ServerResponse(String errorMsg, Integer errorCode, T obj) {
        this.errorMsg = errorMsg;
        this.code = errorCode;
        this.obj = obj;
    }

    public static ServerResponse buildFromException(SysException e) {
        return new ServerResponse(
                e.getMessage(), e.getErrorCode(), e.getData()
        );
    }

    public static ServerResponse buildOK(Object data) {
        return new ServerResponse(
                "OK", SysException.EC_SUCCESS, data
        );
    }

    public static String buildJson(String message, int code, Object data) {
        return JSON.toJSONString(new ServerResponse(
                message
                , code, data
        ));
    }

    public static String buildOkJson(Object data) {
        return JSON.toJSONString(buildOK(data), SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String buildOkJsonWithNullField(Object data) {
        return JSON.toJSONString(buildOK(data), SerializerFeature.WriteMapNullValue);
    }

    public static String buildOkJsonWithNonStringKey(Object data) {
        return JSON.toJSONString(buildOK(data), SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteMapNullValue);
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "errorMsg='" + errorMsg + '\'' +
                ", code=" + code +
                ", obj=" + obj +
                '}';
    }
}
