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

package cn.zhhcloud.client.message.dms.mqtt.interptor;

import cn.zhhcloud.client.message.mqtt2.interptor.MqttChannelInterptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

/**
 * 功能描述: <br>
 * 〈DMS 发布通道拦截器〉
 *
 * @author LoadHao
 * @date 2019/6/11
 */
@Slf4j
public class DMSMqttPubChannelInterptor extends MqttChannelInterptor {

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        log.info("DMSMqttPublisher send message completion, message:{}, channel{}, send:{}, ex:{}", message, channel, sent, ex);
    }
}
