package com.jhy.entity;

/**
 *      定义返回的消息实体
 * Created by JHy on 2019/1/6.
 */
public class ResultMessage {
    private String status;      // 状态 fail、success
    private String message;     // 消息内容

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
