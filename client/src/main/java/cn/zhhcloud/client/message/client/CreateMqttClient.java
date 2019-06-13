/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: CreateMqttClient
 * Author:   LoadHao
 * Date:     2019/6/13 14:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.client;


import cn.zhhcloud.client.message.mqtt.channel.outbound.MqttOutboundChannel;
import cn.zhhcloud.client.message.mqtt.channel.outbound.gateway.MqttMessageGateway;
import cn.zhhcloud.client.message.mqtt.message.MqttMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 功能描述: <br>
 * 〈〉
 *
 * @author LoadHao
 * @date 2019/6/13
 */
public class CreateMqttClient {

    //发布：
    // 1、使用默认的消息通道
    // 2、创建一个新的消息通道

    public boolean sendMessage(MqttMessage mqttMessage) {
        // 创建一个新的消息通道
        // 连接信息、通道、gateway

        return false;
    }

//    public MqttMessageGateway createNewSendChannel(ChannelInterceptor channelInterceptor, MessageChannel messageChannel) {
//
////        return new MqttMessageGateway();
//    }


    //订阅：
    // 1、使用默认的消息通道
    // 2、创建订阅消息通道，并且有回调函数来处理订阅到的消息
}
