/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: ClientMessageHandler
 * Author:   LoadHao
 * Date:     2019/6/13 14:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.recv;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.integration.dispatcher.UnicastingDispatcher;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * 功能描述: <br>
 * 〈〉
 *
 * @author LoadHao
 * @date 2019/6/13
 */
public class ClientMessageHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
//       UnicastingDispatcher unicastingDispatcher = new UnicastingDispatcher();
//        unicastingDispatcher.addHandler(new ClientMessageHandler());
        String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
        //具体的客户端业务流程，可以写这儿
        System.out.println("消息头:" + message.getHeaders());
        System.out.println("订阅主题是:" + topic);
        System.out.println("消息内容是:" + message.getPayload().toString());
    }
}