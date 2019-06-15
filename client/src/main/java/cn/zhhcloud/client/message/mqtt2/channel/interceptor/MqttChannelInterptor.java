/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MyChannelInterptor
 * Author:   LoadHao
 * Date:     2019/6/11 18:47
 * Description: 自定义的通道拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.channel.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 * 功能描述: <br>
 * 〈自定义的通道拦截器〉
 *
 * @author LoadHao
 * @date 2019/6/11
 */
@Slf4j
public class MqttChannelInterptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("preSend message:{}, channel:{}", message.toString(), channel.toString());
        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        log.info("postSend message:{}, channel:{}, sent:{}", message.toString(), channel.toString(), sent);
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        log.info("afterSendCompletion message:{}, channel:{}, sent:{}, ex:{}", message.toString(), channel.toString(), sent, ex);
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        log.info("preReceive channel:{}", channel.toString());
        return false;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        log.info("afterSendCompletion message:{}, channel:{}", message.toString(), channel.toString());
        return message;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        log.info("afterSendCompletion message:{}, channel:{}, ex:{}", message.toString(), channel.toString(), ex);
    }
}
