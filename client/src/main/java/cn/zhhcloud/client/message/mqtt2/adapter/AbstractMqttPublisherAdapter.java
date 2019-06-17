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

import cn.zhhcloud.client.message.mqtt2.MqttMessage;
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
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: <br>
 * 〈发布〉
 *
 * @author LoadHao
 * @date 2019/6/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractMqttPublisherAdapter extends IntegrationFlowAdapter {
    private MqttPahoClientFactory mqttPahoClientFactory;
    private String clientId;
    private MqttPahoMessageHandler messageHandler;
    private static MessageChannel outputChannel;
    private boolean async = true;
    private boolean defaultRetained;
    private int defaultQos = 1;

    public AbstractMqttPublisherAdapter() {
    }

    public AbstractMqttPublisherAdapter(MqttPahoClientFactory mqttPahoClientFactory, String clientId) {
        this.mqttPahoClientFactory = mqttPahoClientFactory;
        this.clientId = clientId;
    }

    public AbstractMqttPublisherAdapter(MqttPahoClientFactory mqttPahoClientFactory, String clientId, MqttPahoMessageHandler messageHandler) {
        this.mqttPahoClientFactory = mqttPahoClientFactory;
        this.clientId = clientId;
        this.messageHandler = messageHandler;
    }

    public AbstractMqttPublisherAdapter(MqttPahoClientFactory mqttPahoClientFactory, String clientId, MessageChannel channel) {
        this.mqttPahoClientFactory = mqttPahoClientFactory;
        this.clientId = clientId;
        outputChannel = channel;
    }

    public AbstractMqttPublisherAdapter(MqttPahoClientFactory mqttPahoClientFactory, String clientId, MqttPahoMessageHandler messageHandler, MessageChannel channel) {
        this.mqttPahoClientFactory = mqttPahoClientFactory;
        this.clientId = clientId;
        this.messageHandler = messageHandler;
        outputChannel = channel;
    }

    /**
     * <功能描述>:
     * IntegrationFlowDefinition
     *
     * @author LoadHao
     **/
    @Override
    protected IntegrationFlowDefinition<?> buildFlow() {
        IntegrationFlow integrationFlow = mqttOutboundFlow();
        return from(getOutputChannel()).gateway(integrationFlow);
    }

    /**
     * <功能描述>:
     * IntegrationFlow
     *
     * @author LoadHao
     **/
    private IntegrationFlow mqttOutboundFlow() {
        return IntegrationFlows.from(getOutputChannel())
                .handle(getMessageHandler())
                .get();
    }

    public MqttPahoClientFactory getMqttPahoClientFactory() {
        return mqttPahoClientFactory;
    }

    public void setMqttPahoClientFactory(MqttPahoClientFactory mqttPahoClientFactory) {
        this.mqttPahoClientFactory = mqttPahoClientFactory;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * <功能描述>:
     * MqttPahoMessageHandler
     *
     * @author LoadHao
     **/
    public MqttPahoMessageHandler getMessageHandler() {
        if (messageHandler == null) {
            MqttPahoMessageHandler defaultMessageHandler = new MqttPahoMessageHandler
                    (clientId, mqttPahoClientFactory);
            defaultMessageHandler.setAsync(async);
            //消息是否永久保留
            defaultMessageHandler.setDefaultRetained(defaultRetained);
            //消息发布服务质量
            defaultMessageHandler.setDefaultQos(defaultQos);
            this.messageHandler = defaultMessageHandler;
        }
        return this.messageHandler;
    }

    public void setMessageHandler(MqttPahoMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public MessageChannel getOutputChannel() {
        if (outputChannel == null) {
            DirectChannel defaultChannel = new DirectChannel();
            defaultChannel.addInterceptor(new MqttChannelInterptor());
            outputChannel = defaultChannel;
        }
        return outputChannel;
    }

    public void setOutputChannel(MessageChannel channel) {
        outputChannel = channel;
    }


    /**
     * <功能描述>:
     * 发布消息
     *
     * @param mqttMessage 消息内容
     * @author LoadHao
     **/
    public static boolean publishMessage(MqttMessage mqttMessage) {
        //消息头
        MessageHeaders messageHeaders = mqttHeaders(mqttMessage);
        //消息体
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setPayload(mqttMessage.getPayload());
        baseMessage.setHeaders(messageHeaders);
        return new SendMessage().send(baseMessage);
    }

    /**
     * <功能描述>:
     * 发布消息，支持超时时间
     *
     * @param mqttMessage 消息内容
     * @param timeout     超时时间
     * @return boolean 发布状态
     * @author LoadHao
     **/
    public static boolean publishMessage(MqttMessage mqttMessage, long timeout) {
        //消息头
        MessageHeaders messageHeaders = mqttHeaders(mqttMessage);
        //消息体
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setPayload(mqttMessage.getPayload());
        baseMessage.setHeaders(messageHeaders);
        return new SendMessage().send(baseMessage, timeout);
    }

    /**
     * 组装消息头信息
     */
    private static MessageHeaders mqttHeaders(MqttMessage mqttMessage) {
        Map<String, Object> headers = new HashMap<>(10);
        headers.put(MqttHeaders.TOPIC, mqttMessage.getTopic());
        headers.put(MqttHeaders.QOS, mqttMessage.getQos());
        headers.put(MqttHeaders.RETAINED, mqttMessage.isRetained());
        return new MessageHeaders(headers);
    }

    @Data
    private static class BaseMessage implements Message {
        private String payload;
        private MessageHeaders headers;
    }

    private static class SendMessage implements MessageChannel {

        @Override
        public boolean send(Message<?> message, long l) {
            if (outputChannel == null) {
                return false;
            }
            return outputChannel.send(message, l);
        }

        @Override
        public boolean send(Message<?> message) {
            if (outputChannel == null) {
                return false;
            }
            return outputChannel.send(message);
        }
    }
}
