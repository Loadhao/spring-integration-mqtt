/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttSubscriber
 * Author:   LoadHao
 * Date:     2019/6/18 19:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.subscriber;

import cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter;
import cn.zhhcloud.client.message.mqtt2.bean.IntegrationFlowBeanUtils;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * 功能描述: <br>
 * 〈Mqtt消息订阅者〉
 *
 * @author LoadHao
 * @date 2019/6/18
 */
public class MqttSubscriber extends AbstractMqttSubscriberAdapter {

    @Override
    public MqttSubscriber setChannel(MessageChannel channel) {
        return (MqttSubscriber) super.setChannel(channel);
    }

    /**
     * <功能描述>:
     * 设置订阅消息的回调类
     *
     * @param messageHandler 消息回调类
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    @Override
    public MqttSubscriber setMessageHandler(MessageHandler messageHandler) {
        return (MqttSubscriber) super.setMessageHandler(messageHandler);
    }

    /**
     * <功能描述>:
     * 设置客户端id（唯一）
     *
     * @param clientId 客户端id
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    @Override
    public MqttSubscriber setClientId(String clientId) {
        return (MqttSubscriber) super.setClientId(clientId);
    }

    /**
     * <功能描述>:
     * 订阅主题
     *
     * @param topic 订阅的主题
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    @Override
    public MqttSubscriber setTopic(String topic) {
        return (MqttSubscriber) super.setTopic(topic);
    }

    /**
     * <功能描述>:
     * bean注入
     *
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter
     * @author LoadHao
     **/
    @Override
    public MqttSubscriber postBean() {
        return (MqttSubscriber) IntegrationFlowBeanUtils.postBeforeInitialization(this);
    }
}
