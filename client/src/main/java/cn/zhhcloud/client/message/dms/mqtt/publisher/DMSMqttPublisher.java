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

package cn.zhhcloud.client.message.dms.mqtt.publisher;

import cn.zhhcloud.client.message.dms.mqtt.constant.DMSPublisherConstant;
import cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttPublisherAdapter;
import cn.zhhcloud.client.message.mqtt2.bean.IntegrationFlowBeanUtils;
import cn.zhhcloud.client.message.mqtt2.channel.MqttChannelInterptor;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

/**
 * 功能描述: <br>
 * 〈DMS Mqtt消息发布者〉
 *
 * @author LoadHao
 * @date 2019/6/17
 */
public class DMSMqttPublisher extends AbstractMqttPublisherAdapter {
    public static DMSMqttPublisher DEFAULT;

    private DMSMqttPublisher() {
    }

    public static void init() {
        //bean注入
        DEFAULT = (DMSMqttPublisher) IntegrationFlowBeanUtils.postBeforeInitialization(defaultPublisher());
    }

    /**
     * <功能描述>:
     * 默认发布者
     *
     * @return cn.zhhcloud.client.message.mqtt2.publisher.DMSMqttPublisher
     * @author LoadHao
     **/
    private static DMSMqttPublisher defaultPublisher() {
        DMSMqttPublisher dmsMqttPublisher = new DMSMqttPublisher();
        //通道设置
        dmsMqttPublisher.setOutputChannel(outputChannel());
        //设置ClientId
        dmsMqttPublisher.setClientId(DMSPublisherConstant.SERVICE_ID);
        //消息异步处理
        dmsMqttPublisher.setAsync(true);
        //消息的qos
        dmsMqttPublisher.setDefaultQos(1);
        //消息持久化
        dmsMqttPublisher.setDefaultRetained(false);
        return dmsMqttPublisher;
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
