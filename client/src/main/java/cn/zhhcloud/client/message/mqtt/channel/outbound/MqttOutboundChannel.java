/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttOutboundChannel
 * Author:   LoadHao
 * Date:     2019/6/11 19:15
 * Description: 出站通道
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt.channel.outbound;

import cn.zhhcloud.client.message.mqtt.channel.interceptor.MqttChannelInterptor;
import cn.zhhcloud.client.message.mqtt.connection.MqttClientConnFactory;
import cn.zhhcloud.client.message.mqtt.connection.MqttConnOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * 功能描述: <br>
 * 〈出站通道：用于发布消息通道〉
 *
 * @author LoadHao
 * @date 2019/6/11
 */
@Configuration
@IntegrationComponentScan
@ConditionalOnBean(value = {MqttClientConnFactory.class, MqttConnOptions.class})
public class MqttOutboundChannel {
    public static final String OUTBOUND_CHANNEL_NAME = "messageOutboundChannel";

    @Autowired
    private MqttConnOptions mqttConnOptions;

    @Bean(name = OUTBOUND_CHANNEL_NAME)
    public MessageChannel messageOutboundChannel() {
        DirectChannel directChannel = new DirectChannel();
        //通道拦截器
        directChannel.addInterceptor(new MqttChannelInterptor());
        return directChannel;
    }

    @Bean
    public MessageHandler mqttOutbound(MqttPahoClientFactory mqttPahoClientFactory) {
        //加上一个时间戳，防止不同主机serviceId重复
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler
                ("serviceId", mqttPahoClientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(mqttConnOptions.getDefaultTopic());
        //消息是否永久保留
        /* messageHandler.setDefaultRetained(false);*/
        //消息发布服务质量
        messageHandler.setDefaultQos(Integer.valueOf(mqttConnOptions.getDefaultQos()));
        return messageHandler;
    }
}

