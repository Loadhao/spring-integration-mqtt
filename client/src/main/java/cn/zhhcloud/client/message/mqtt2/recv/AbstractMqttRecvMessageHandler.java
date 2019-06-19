/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttMessageHandler
 * Author:   LoadHao
 * Date:     2019/6/19 10:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.recv;

import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;

/**
 * 功能描述: <br>
 * 〈MQTT 接收消息的处理程序〉
 *
 * @author LoadHao
 * @date 2019/6/19
 */
public abstract class AbstractMqttRecvMessageHandler implements MessageHandler, IMqttMessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        MessageHeaders headers = message.getHeaders();

        String payload = (String) message.getPayload();
        String topic = (String) headers.get(MqttHeaders.RECEIVED_TOPIC);

        mqttReceivedMessage(new MqttRecvMessage() {
            @Override
            public String getPayload() {
                return payload;
            }

            @Override
            public String getTopic() {
                return topic;
            }
        });
    }
}