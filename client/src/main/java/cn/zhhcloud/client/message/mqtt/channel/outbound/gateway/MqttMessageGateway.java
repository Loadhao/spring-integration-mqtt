/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttMessageGateway
 * Author:   LoadHao
 * Date:     2019/6/12 16:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt.channel.outbound.gateway;

import cn.zhhcloud.client.message.mqtt.channel.outbound.MqttOutboundChannel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 功能描述: <br>
 * 〈消息网关〉
 *
 * @author LoadHao
 * @date 2019/6/12
 */
@ConditionalOnClass(MqttOutboundChannel.class)
@MessagingGateway(defaultRequestChannel = MqttOutboundChannel.OUTBOUND_CHANNEL_NAME)
public interface MqttMessageGateway {

    void sendToMqtt(String data);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

    void sendToMqtt(Message<?> message);

}
