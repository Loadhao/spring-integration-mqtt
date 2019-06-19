/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: DMSMqttPublisher
 * Author:   LoadHao
 * Date:     2019/6/17 13:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.publisher;

import cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttPublisherAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;

/**
 * 功能描述: <br>
 * 〈DMS Mqtt消息发布者〉
 *
 * @author LoadHao
 * @date 2019/6/17
 */
public class MqttPublisher extends AbstractMqttPublisherAdapter {

    /**
     * <功能描述>:
     * 设置客户端id（唯一）
     *
     * @param clientId 客户端id
     * @author LoadHao
     **/
    @Override
    public MqttPublisher setClientId(String clientId) {
        return (MqttPublisher) super.setClientId(clientId);
    }

    /**
     * <功能描述>:
     * bean注入
     *
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttPublisherAdapter
     * @author LoadHao
     **/
    @Override
    public MqttPublisher postBean() {
        return (MqttPublisher) super.postBean();
    }

    /**
     * <功能描述>:
     * 设置发布消息的处理程序
     *
     * @param messageHandler 布消息的处理程序
     * @author LoadHao
     */
    @Override
    public MqttPublisher setMessageHandler(MqttPahoMessageHandler messageHandler) {
        return (MqttPublisher) super.setMessageHandler(messageHandler);
    }

    /**
     * <功能描述>:
     * 设置发布消息通道
     *
     * @param channel 发布消息的通道
     * @author LoadHao
     **/
    @Override
    public MqttPublisher setOutputChannel(MessageChannel channel) {
        return (MqttPublisher) super.setOutputChannel(channel);
    }

    /**
     * <功能描述>:
     * 消息发布是否异步
     *
     * @param async 是否异步
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttPublisherAdapter
     **/
    @Override
    public MqttPublisher setAsync(boolean async) {
        return (MqttPublisher) super.setAsync(async);
    }

    /**
     * <功能描述>:
     * 设置发布的消息是否持久化
     *
     * @param defaultRetained 是否持久化
     * @return cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttPublisherAdapter
     * @author LoadHao
     **/
    @Override
    public MqttPublisher setDefaultRetained(boolean defaultRetained) {
        return (MqttPublisher) super.setDefaultRetained(defaultRetained);
    }

    /**
     * <功能描述>:
     * 设置发布的消息默认的qos
     *
     * @param defaultQos 默认的qos
     * @author LoadHao
     **/
    @Override
    public MqttPublisher setDefaultQos(int defaultQos) {
        return (MqttPublisher) super.setDefaultQos(defaultQos);
    }
}
