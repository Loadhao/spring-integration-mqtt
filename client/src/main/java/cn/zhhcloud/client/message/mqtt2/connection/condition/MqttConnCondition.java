/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttConnCondition
 * Author:   LoadHao
 * Date:     2019/6/13 10:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.connection.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 功能描述: <br>
 * 〈Mqtt bean的装配条件〉
 *
 * @author LoadHao
 * @date 2019/6/13
 */
public class MqttConnCondition implements Condition {
    private static final String TRUE = "true";

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        // 根据context取出对应的Env信息
        Environment environment = conditionContext.getEnvironment();
        // MQTT使能/失能
        String property = environment.getProperty("spring.mqtt.enable");
        return TRUE.equals(property);
    }
}
