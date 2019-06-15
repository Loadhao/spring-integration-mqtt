/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: Test
 * Author:   LoadHao
 * Date:     2019/6/14 15:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2;

import cn.zhhcloud.client.message.mqtt2.adapter.MqttPublisherAdapter;
import cn.zhhcloud.client.message.mqtt2.adapter.MqttSubscriberAdapter;
import cn.zhhcloud.client.message.mqtt2.connection.MqttClientConnFactory;
import cn.zhhcloud.client.message.mqtt2.connection.MqttConnOptions;
import cn.zhhcloud.client.message.mqtt2.recv.ClientMessageHandler;
import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.integration.dsl.context.IntegrationFlowBeanPostProcessor;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: <br>
 * 〈〉
 *
 * @author LoadHao
 * @date 2019/6/14
 */
@Component
@ConditionalOnBean(MqttConnOptions.class)
public class Test {

    @Resource
    private IntegrationFlowBeanPostProcessor integrationFlowBeanPostProcessor;

    @Resource
    private MqttPahoClientFactory mqttPahoClientFactory;

    private MqttPublisherAdapter mqttPublisherAdapter;

    private MqttPahoMessageDrivenChannelAdapter adapter;

    @PostConstruct
    public void run() {

        //订阅
        MqttSubscriberAdapter<ClientMessageHandler> mqttSubscriberAdapter = new MqttSubscriberAdapter<>();
        mqttSubscriberAdapter.setClientId("clientId");
        mqttSubscriberAdapter.setMqttPahoClientFactory(mqttPahoClientFactory);
        mqttSubscriberAdapter.setMessageHandler(new ClientMessageHandler());
        String[] strings = {"topic1", "topic2"};
        mqttSubscriberAdapter.setTopics(strings);
        integrationFlowBeanPostProcessor.postProcessBeforeInitialization(mqttSubscriberAdapter, mqttSubscriberAdapter.getClass().getName());
        this.adapter = mqttSubscriberAdapter.getAdapter();

        //发布
        MqttPublisherAdapter mqttPublisherAdapter = new MqttPublisherAdapter();
        mqttPublisherAdapter.setMqttPahoClientFactory(mqttPahoClientFactory);
        mqttPublisherAdapter.setServiceId("serviceId");
        this.mqttPublisherAdapter = mqttPublisherAdapter;
        integrationFlowBeanPostProcessor.postProcessBeforeInitialization(mqttPublisherAdapter, mqttPublisherAdapter.getClass().getName());

    }

    /**
     * 发布消息测试
     */
    @Component
    public class Test2 implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            //订阅主题：topic_test
            adapter.addTopic("topic_test");

            //发布消息到主题：topic_test
            BaseMessage baseMessage = new BaseMessage();
            baseMessage.setPayload("first blood.");
            Map<String, Object> headers = new HashMap<>(10);
            headers.put(MqttHeaders.TOPIC, "topic_test");
            headers.put(MqttHeaders.QOS, 1);
            MessageHeaders messageHeaders = new MessageHeaders(headers);
            baseMessage.setHeaders(messageHeaders);
            mqttPublisherAdapter.getInputChannel().send(baseMessage, 10);
        }
    }

    @Data
    private static class BaseMessage implements Message {
        private String payload;
        private MessageHeaders headers;
    }
}
