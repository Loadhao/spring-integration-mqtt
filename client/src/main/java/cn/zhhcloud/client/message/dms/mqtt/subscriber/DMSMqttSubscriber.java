/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: DMSMqttSubscriber
 * Author:   LoadHao
 * Date:     2019/6/18 16:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.dms.mqtt.subscriber;

import cn.zhhcloud.client.message.dms.mqtt.interptor.DMSMqttSubChannelInterptor;
import cn.zhhcloud.client.message.dms.mqtt.receiver.Client1Recv;
import cn.zhhcloud.client.message.dms.mqtt.receiver.Client2Recv;
import cn.zhhcloud.client.message.mqtt2.subscriber.MqttSubscriber;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

/**
 * 功能描述: <br>
 * 〈DMS Mqtt消息订阅者〉
 *
 * @author LoadHao
 * @date 2019/6/18
 */
public class DMSMqttSubscriber extends MqttSubscriber {
    public static MqttSubscriber CLIENT_1;
    public static MqttSubscriber CLIENT_2;

    public static void init() {
        CLIENT_1 = client1();
        CLIENT_2 = client2();
    }

    /**
     * 订阅者一
     */
    private static MqttSubscriber client1() {
        return new MqttSubscriber()
                .setClientId("clientId1")
                .setTopic("a")
                .setMessageHandler(new Client1Recv())
                .setChannel(inputChannel())
                .postBean();
    }

    /**
     * 订阅者二
     */
    private static MqttSubscriber client2() {
        return new MqttSubscriber()
                .setClientId("clientId2")
                .setTopic("a")
                .setMessageHandler(new Client2Recv())
                .setChannel(inputChannel())
                .postBean();
    }

    /**
     * 订阅消息通道设置
     */
    private static MessageChannel inputChannel() {
        DirectChannel directChannel = new DirectChannel();
        //消息拦截
        directChannel.addInterceptor(new DMSMqttSubChannelInterptor());
        return directChannel;
    }
}
