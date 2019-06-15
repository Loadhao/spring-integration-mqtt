/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttInboundFlowAdapter
 * Author:   LoadHao
 * Date:     2019/6/15 16:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.adapter;

import cn.zhhcloud.client.message.mqtt2.channel.interceptor.MqttChannelInterptor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowAdapter;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * 功能描述: <br>
 * 〈发布〉
 *
 * @author LoadHao
 * @date 2019/6/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MqttPublisherAdapter extends IntegrationFlowAdapter {

    private MqttPahoClientFactory mqttPahoClientFactory;
    private String serviceId;
    private MessageHandler messageHandler;
    private MessageChannel inputChannel;

    @Override
    protected IntegrationFlowDefinition<?> buildFlow() {
        IntegrationFlow integrationFlow = mqttOutboundFlow();
        this.inputChannel = integrationFlow.getInputChannel();
        return from(messagePublisherChannel()).gateway(integrationFlow);
    }

    private IntegrationFlow mqttOutboundFlow() {
        return IntegrationFlows.from(messagePublisherChannel())
                .handle(getMessageHandler())
                .get();
    }

    private MqttPahoMessageHandler getMessageHandler() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler
                (serviceId, mqttPahoClientFactory);
        messageHandler.setAsync(true);
        //消息是否永久保留
        /* messageHandler.setDefaultRetained(false);*/
        //消息发布服务质量
        messageHandler.setDefaultQos(1);
        return messageHandler;
    }

    private MessageChannel messagePublisherChannel() {
        DirectChannel directChannel = new DirectChannel();
        //通道拦截器
        directChannel.addInterceptor(new MqttChannelInterptor());
        return directChannel;
    }
}
