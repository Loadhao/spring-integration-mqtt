/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 *
 * @Copyright: 2019 www.iwhalecloud.com, All rights reserved.
 * FileName: IMqttMessageHandler
 * Author:   LoadHao
 * Date:     2019/6/19 11:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.recv;

/**
 * 功能描述: <br>
 * 〈MQTT 接收消息的接口〉
 *
 * @author LoadHao
 * @date 2019/6/19
 */
public interface IMqttMessageHandler {
    /**
     * <功能描述>:
     * MQTT 接收消息的接口
     *
     * @param mqttRecvMessage 接收的消息
     * @author LoadHao
     **/
    void mqttReceivedMessage(MqttRecvMessage mqttRecvMessage);
}

