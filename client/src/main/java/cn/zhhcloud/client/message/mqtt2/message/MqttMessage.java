/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: BaseMessage
 * Author:   LoadHao
 * Date:     2019/6/13 14:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.message;

import lombok.Data;

/**
 * 功能描述: <br>
 * 〈MqttMessage〉
 *
 * @author LoadHao
 * @date 2019/6/13
 */
@Data
public class MqttMessage {
    private String payload;
    private String topic;
    private Integer qos = 1;
    private boolean retained = false;
}
