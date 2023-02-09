package com.example.litemanager.conmon;

/**
 * @author: hj
 * @date: 2023/2/9
 * @time: 8:43 AM
 */
public class ReturnMessage {

    private String code;
    private String message;
    private Object data;

    private String token;

    public String getToken() {
        return token;
    }

    public ReturnMessage setToken(String token) {
        this.token = token;
        return this;
    }

    public static ReturnMessage success(Object data) {
        return new ReturnMessage("200", "success", data);
    }

    public static ReturnMessage success(String msg, Object data) {
        return new ReturnMessage("200", msg, data);
    }

    public static ReturnMessage error(String message) {
        return new ReturnMessage("500", message, null);
    }

    public ReturnMessage() {
    }

    public ReturnMessage(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReturnMessage{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
