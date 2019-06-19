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

package cn.zhhcloud.client.message.dms.mqtt.receiver;

import cn.zhhcloud.client.message.mqtt2.recv.AbstractMqttRecvMessageHandler;
import cn.zhhcloud.client.message.mqtt2.recv.MqttRecvMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.transformer.Transformer;

/**
 * 功能描述: <br>
 * 〈〉
 *
 * @author LoadHao
 * @date 2019/6/13
 */
@Slf4j
public class Client1Recv extends AbstractMqttRecvMessageHandler {

    @Override
    public void mqttReceivedMessage(MqttRecvMessage mqttRecvMessage) {
        String payload = mqttRecvMessage.getPayload();
        log.info("payload = " + payload);

        String topic = mqttRecvMessage.getTopic();
        log.info("topic = " + topic);
    }
}