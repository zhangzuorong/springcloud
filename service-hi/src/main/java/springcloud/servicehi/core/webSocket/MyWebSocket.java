package springcloud.servicehi.core.webSocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开发公司：青岛上朝信息科技有限公司
 * 版权：青岛上朝信息科技有限公司
 * <p>
 * 类功能描述
 *
 * @author zhangzuorong
 * @created 2018/9/10.
 */
@ServerEndpoint("/webSocket")
@Component
@RestController
public class MyWebSocket {
    private static final Logger logger = LogManager.getLogger(MyWebSocket.class.getName());
    //静态变量，用来记录当前在线连接数
    private static int onlineCount = 0;

    //concurrent包的线程安全set,用来存放每个客户端对应的MyWebSocket对象，若要实现服务端与单一客户端通信的
    private static CopyOnWriteArraySet<MyWebSocket> webSockets = new CopyOnWriteArraySet<MyWebSocket>();

    //与某个客户端的链接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 链接成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
        addOnlineCount();
        logger.info("有新连接加入，当前在线人数为"+ getOnlineCount());
    }

    /**
     * 链接关闭方法
     */
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        subOnlineCount();
        logger.info("有新连接关闭，当前在线人数为"+ getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){
        String serverMsg = "你好，小程序！";
        logger.info("来自客户端的消息："+message);
        //群发消息
        for(MyWebSocket my : webSockets){
            try{
                my.sendMessage(serverMsg);
            }catch (Exception e){
                continue;
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session,Throwable error){
        logger.info("MyWebsocket出错了");
        error.printStackTrace();
    }

    /**
     * onMessage调用的方法
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }

    public static synchronized void addOnlineCount(){
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount(){
        MyWebSocket.onlineCount--;
    }

}
