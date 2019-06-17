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
import cn.zhhcloud.client.message.mqtt2.channel.interceptor.MqttChannelInterptor;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.context.IntegrationFlowBeanPostProcessor;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.messaging.MessageChannel;

/**
 * 功能描述: <br>
 * 〈DMS消息发布者〉
 *
 * @author LoadHao
 * @date 2019/6/17
 */
public class DMSMqttPublisher extends AbstractMqttPublisherAdapter {
    private static final String SERVICE_ID = "serviceId";

    public static void init(MqttPahoClientFactory mqttPahoClientFactory, IntegrationFlowBeanPostProcessor integrationFlowBeanPostProcessor) {
        DMSMqttPublisher dmsMqttPublisher = new DMSMqttPublisher();
        //连接
        dmsMqttPublisher.setMqttPahoClientFactory(mqttPahoClientFactory);
        //通道设置
        dmsMqttPublisher.setOutputChannel(outputChannel());
        //设置ClientId
        dmsMqttPublisher.setClientId(SERVICE_ID);
        //消息异步处理
        dmsMqttPublisher.setAsync(true);
        //消息的qos
        dmsMqttPublisher.setDefaultQos(1);
        //消息持久化
        dmsMqttPublisher.setDefaultRetained(false);
        integrationFlowBeanPostProcessor.postProcessBeforeInitialization(dmsMqttPublisher, dmsMqttPublisher.getClass().getName());
    }

    /**
     * 发布消息通道设置
     */
    private static MessageChannel outputChannel() {
        DirectChannel directChannel = new DirectChannel();
        //消息拦截
        directChannel.addInterceptor(new MqttChannelInterptor());
        return directChannel;
    }
}
