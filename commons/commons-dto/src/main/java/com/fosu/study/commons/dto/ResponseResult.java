package com.fosu.study.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用dto
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/21
 * @see com.fosu.study.commons.dto
 *
 */
@Data
public class ResponseResult <T> implements Serializable {
    private static final long serialVersionUID = 3468352004150968551L;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 返回对象
     */
    private T data;
    public ResponseResult() {
        super();
    }
    public ResponseResult(Integer code) {
        super();
        this.code = code;
    }
    public ResponseResult(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    public ResponseResult(Integer code, Throwable throwable) {
        super();
        this.code = code;
        this.message = throwable.getMessage();
    }
    public ResponseResult(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }
    public ResponseResult(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResponseResult<?> other = (ResponseResult<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }

    /**
     * 存放状态码
     * <p>
     * Description
     * </p>
     * @author miki
     * @version v1.0.0
     * @date 2019/08/21
     * @see com.fosu.study.commons.dto
     *
     */
     public static class CodeStatus{
        /**
         * 成功
         */
        public static final int OK = 20000;

        /**
         * 失败
         */
        public static final int FAIL = 50000;

        /**
         * 非法token
         */
        public static final int ILLEGAL_TOKEN = 50008;

        /**
         * 其他账号已登录
         */
        public static final int OTHER_CLIENTS_LOGGED_IN = 50012;

        /**
         * token超时
         */
        public static final int TOEKN_EXPIRED = 50014;
     }


}
