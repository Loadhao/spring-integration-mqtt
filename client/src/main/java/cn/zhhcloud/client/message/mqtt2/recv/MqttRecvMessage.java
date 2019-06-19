/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: RecvMessage
 * Author:   LoadHao
 * Date:     2019/6/19 14:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.recv;

/**
 * 功能描述: <br>
 * 〈Mqtt 接收到的消息参数详情接口〉
 *
 * @author LoadHao
 * @date 2019/6/19
 */
public interface MqttRecvMessage {

    String getPayload();

    String getTopic();
}
