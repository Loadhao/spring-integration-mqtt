/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttInboundChannel
 * Author:   LoadHao
 * Date:     2019/6/12 11:06
 * Description: 入站
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt.channel.inbound;

import cn.zhhcloud.client.message.mqtt.channel.interceptor.MqttChannelInterptor;
import cn.zhhcloud.client.message.mqtt.connection.MqttClientConnFactory;
import cn.zhhcloud.client.message.mqtt.recv.ClientMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

/**
 * 功能描述: <br>
 * 〈入站通道：用于订阅消息通道〉
 *
 * @author LoadHao
 * @date 2019/6/12
 */
@Slf4j
@Component
@IntegrationComponentScan
@ConditionalOnBean(MqttClientConnFactory.class)
public class MqttInboundChannel {
    private static final String INBOUND_CHANNEL_NAME = "messageInboundChannel";

    @Bean(name = INBOUND_CHANNEL_NAME)
    public MessageChannel messageInboundChannel() {
        DirectChannel directChannel = new DirectChannel();
        //通道拦截器
        directChannel.addInterceptor(new MqttChannelInterptor());
        return directChannel;
    }

    @Bean
    public MessageProducer inbound(MqttPahoClientFactory mqttPahoClientFactory, MessageChannel messageInboundChannel) {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter
                ("clientId", mqttPahoClientFactory, "topic");
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(messageInboundChannel);
        return adapter;
    }

    /**
     * <功能描述>:
     * 订阅的Topic消息接收通道
     **/
    @Bean
    @ServiceActivator(inputChannel = INBOUND_CHANNEL_NAME)
    public MessageHandler messageHandler() {
        return new ClientMessageHandler();
    }
}
