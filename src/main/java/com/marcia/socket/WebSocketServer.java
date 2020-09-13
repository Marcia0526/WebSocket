package com.marcia.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 会话
     */
    private Session session;


    /**
     * 建立连接
     * 服务器向客户端推送信息
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        logger.info("websocket建立连接成功；"+" session_id：" + session.getId() + "； user_name：" + username);
        this.session = session;
        try {
            Map<String, Object> map = new HashMap();
            map.put("push message to client", username);
            sendMessageTo(JSON.toJSONString(map), session.getId());
        } catch (Exception e) {
            logger.info(username + " failed to push message to client");
        }

    }

    /**
     * 连接发生错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("Server error:" + error.getMessage() + "; session_id:" + session.getId());
        error.printStackTrace();
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        logger.info("Connection closed from server");
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.info("Received message from client：" + message + "; session_id:" + session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String userName = jsonObject.getString("user_name");
            logger.info(userName+" "+textMessage);
        } catch (Exception e) {
            logger.info("Server handle message error");
        }

    }


    /**
     * 服务器向客户端推送信息
     *
     * @param message
     * @param nameId
     * @throws IOException
     */

    private void sendMessageTo(String message, String nameId){
        this.session.getAsyncRemote().sendText(nameId + "：" + message);

    }

}
