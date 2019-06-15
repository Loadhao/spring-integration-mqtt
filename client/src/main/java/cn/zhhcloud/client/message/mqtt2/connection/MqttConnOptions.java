/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttConnConfig
 * Author:   LoadHao
 * Date:     2019/6/12 16:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.connection;

import cn.zhhcloud.client.message.mqtt2.connection.condition.MqttConnCondition;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * 功能描述: <br>
 * 〈mqtt连接配置参数〉
 *
 * @author LoadHao
 * @date 2019/6/12
 */
@Data
@Component
@Conditional(MqttConnCondition.class)
@ConfigurationProperties(prefix = "spring.mqtt")
public class MqttConnOptions {

    private String enable;
    private String username;
    private String password;
    private String[] url;
    private String keepAliveInterval;
    private String completionTimeout;
    private String defaultTopic;
    private String defaultQos;
}
