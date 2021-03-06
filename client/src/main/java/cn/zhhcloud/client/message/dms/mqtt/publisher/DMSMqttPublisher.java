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
import cn.zhhcloud.client.message.dms.mqtt.interptor.DMSMqttPubChannelInterptor;
import cn.zhhcloud.client.message.mqtt2.publisher.MqttPublisher;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;

/**
 * 功能描述: <br>
 * 〈DMS Mqtt消息发布者〉
 *
 * @author LoadHao
 * @date 2019/6/17
 */
public class DMSMqttPublisher extends MqttPublisher {
    public static MqttPublisher DEFAULT;

    public static void init() {
        //DMS 默认发布者
        DEFAULT = defaultPublisher();
    }

    /**
     * <功能描述>:
     * 默认发布者
     *
     * @return cn.zhhcloud.client.message.mqtt2.publisher.DMSMqttPublisher
     * @author LoadHao
     **/
    private static MqttPublisher defaultPublisher() {
        return new MqttPublisher()
                //设置ClientId
                .setClientId(DMSPublisherConstant.SERVICE_ID)
                //通道设置
                .setOutputChannel(outputChannel())
                //消息异步处理
                .setAsync(true)
                //消息的qos
                .setDefaultQos(1)
                //消息的qos
                .setDefaultRetained(false).postBean();
    }

    /**
     * 发布消息通道设置
     */
    private static MessageChannel outputChannel() {
        DirectChannel directChannel = new DirectChannel();
        //消息拦截
        directChannel.addInterceptor(new DMSMqttPubChannelInterptor());
        return directChannel;
    }
}
