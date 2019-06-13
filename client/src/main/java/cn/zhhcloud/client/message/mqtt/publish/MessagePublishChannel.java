/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MessagePublishChannel
 * Author:   LoadHao
 * Date:     2019/6/11 16:43
 * Description: 消息发布通道
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt.publish;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

/**
 * 功能描述: <br>
 * 〈消息发布通道〉
 *
 * @author LoadHao
 * @date 2019/6/11
 */
public class MessagePublishChannel implements MessageChannel {

    @Override
    public boolean send(Message<?> message) {
        return false;
    }

    @Override
    public boolean send(Message<?> message, long l) {
        return false;
    }
}
