/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MyIntegrationFlowAdapter
 * Author:   LoadHao
 * Date:     2019/6/14 10:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.adapter;

import cn.zhhcloud.client.message.mqtt2.bean.IntegrationFlowBeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlowAdapter;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述: <br>
 * 〈订阅：集成流适配器〉
 *
 * @author LoadHao
 * @date 2019/6/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public abstract class AbstractMqttSubscriberAdapter extends IntegrationFlowAdapter {
    private MessageHandler messageHandler;
    private String clientId;
    private String topic;
    private int qos = 1;
    private MqttPahoMessageDrivenChannelAdapter drivenChannelAdapter;
    private static MqttPahoClientFactory mqttPahoClientFactory;
    private MessageChannel channel;
    private final Lock topicLock = new ReentrantLock();

    /**
     * 流定义
     */
    @Override
    protected IntegrationFlowDefinition<?> buildFlow() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                clientId, mqttPahoClientFactory);
        adapter.addTopic(topic);
        adapter.setQos(qos);
        adapter.setOutputChannel(getChannel());
        drivenChannelAdapter = adapter;
        return from(adapter).handle(messageHandler);
    }

    private MessageChannel getChannel() {
        if (channel == null) {
            channel = new DirectChannel();
        }
        return channel;
    }

    protected AbstractMqttSubscriberAdapter setChannel(MessageChannel channel) {
        this.channel = channel;
        return this;
    }

    /**
     * <功能描述>:
     * bean注入
     *
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    protected AbstractMqttSubscriberAdapter postBean() {
        return (AbstractMqttSubscriberAdapter) IntegrationFlowBeanUtils.postBeforeInitialization(this);
    }

    /**
     * <功能描述>:
     * 设置订阅消息的回调类
     *
     * @param messageHandler 消息回调类
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    protected AbstractMqttSubscriberAdapter setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        return this;
    }

    /**
     * <功能描述>:
     * 设置客户端id（唯一）
     *
     * @param clientId 客户端id
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    protected AbstractMqttSubscriberAdapter setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * <功能描述>:
     * 订阅主题
     *
     * @param topic 订阅的主题
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    protected AbstractMqttSubscriberAdapter setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    /**
     * <功能描述>:
     * 订阅主题的qos
     *
     * @param qos 订阅主题的qos
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    private AbstractMqttSubscriberAdapter setQos(int qos) {
        this.qos = qos;
        return this;
    }

    private MqttPahoMessageDrivenChannelAdapter getDrivenChannelAdapter() {
        return drivenChannelAdapter;
    }

    private void setDrivenChannelAdapter(MqttPahoMessageDrivenChannelAdapter drivenChannelAdapter) {
        this.drivenChannelAdapter = drivenChannelAdapter;
    }

    /**
     * <功能描述>:
     * 设置连接工厂
     *
     * @param clientFactory 连接工厂
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    public static void setMqttPahoClientFactory(MqttPahoClientFactory clientFactory) {
        mqttPahoClientFactory = clientFactory;
    }

    /**
     * <功能描述>:
     * 获取当前Topic类所有订阅的主题
     **/
    public String[] getSubscribeTopics() {
        return drivenChannelAdapter.getTopic();
    }

    /**
     * <功能描述>:
     * 添加一个主题订阅,默认qos为1
     **/
    public void addSubscribeTopic(String topic) {
        addSubscribeTopic(topic, 1);
    }

    /**
     * <功能描述>:
     * 添加一个主题订阅
     **/
    public void addSubscribeTopic(String topic, int qos) {
        String[] topics = {topic};
        int[] qoss = {qos};
        addSubscribeTopics(topics, qoss);
    }

    /**
     * <功能描述>:
     * 添加多个主题订阅
     **/
    public void addSubscribeTopics(String[] topics, int[] qos) {
        if (null != drivenChannelAdapter) {
            int[] qoss = new int[topics.length];
            if (topics.length > qos.length) {
                for (int i = 0; i < qoss.length; i++) {
                    qoss[i] = 1;
                }
                System.arraycopy(qos, 0, qoss, 0, qos.length);
            }

            //重复的topic会抛出异常
            drivenChannelAdapter.addTopics(topics, qoss);
            log.info("{} has been add subscribe topics:{}.", drivenChannelAdapter.getBeanName(), Arrays.toString(topics));
        }
    }

    /**
     * <功能描述>:
     * 移除指定已经订阅的主题
     * 注意：如果移除未订阅的则抛出MessagingException异常.
     **/
    public void removeSubscribeTopics(String... topics) {
        if (null != drivenChannelAdapter) {
            drivenChannelAdapter.removeTopic(topics);
            log.info("{} has been removed subscribe topics:{}.", drivenChannelAdapter.getBeanName(), Arrays.asList(topics));
        }
    }

    /**
     * <功能描述>:
     * 移除订阅的所有主题
     **/
    public void removeAllSubscribeTopics() {
        if (null != drivenChannelAdapter) {
            String[] topic = drivenChannelAdapter.getTopic();
            if (topic.length > 0) {
                drivenChannelAdapter.removeTopic(topic);
            }
            log.info("{} has been removed all subscribe topics.", drivenChannelAdapter.getBeanName());
        }
    }
}
