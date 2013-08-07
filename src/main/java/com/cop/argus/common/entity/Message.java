package com.cop.argus.common.entity;

import com.google.gson.annotations.Expose;

/**
 * @author chris.liu
 */
public class Message {
    @Expose
    private String title;
    @Expose
    private String content;

    public static final Message MSG_PARAM_INVALID = new Message("抱歉",
            "亲,丢给俺的参数不对呀");

    public static final Message MSG_SERVER_INNER_ERROR = new Message("抱歉",
            "服务器不给力,攻城师们正在疯狂加班修bug!");

    public static final Message MSG_TOKEN_EXPIRED = new Message("抱歉",
            "亲,你的身份过期了,重新登录一下呗!");

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
