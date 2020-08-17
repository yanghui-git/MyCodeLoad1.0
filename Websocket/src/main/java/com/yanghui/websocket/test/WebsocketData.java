package com.yanghui.websocket.test;

import lombok.Data;

import javax.websocket.Session;

@Data
public class WebsocketData {

    /**
     * websocket对应的session
     */
    private Session session;

    /**
     * 携带的数据
     */
    private Object data;

}
