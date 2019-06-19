/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: Test
 * Author:   LoadHao
 * Date:     2019/6/14 15:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.dms.mqtt;

import cn.zhhcloud.client.message.dms.mqtt.publisher.DMSMqttPublisher;
import cn.zhhcloud.client.message.dms.mqtt.subscriber.DMSMqttSubscriber;
import cn.zhhcloud.client.message.mqtt2.connection.MqttConnOptions;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * 功能描述: <br>
 * 〈测试〉
 *
 * @author LoadHao
 * @date 2019/6/14
 */
@Component
@ConditionalOnBean(MqttConnOptions.class)
public class Test implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //订阅主题：topic_test
        DMSMqttSubscriber.CLIENT_1.addSubscribeTopic("b");
        DMSMqttSubscriber.CLIENT_2.addSubscribeTopic("c");

        //发布消息到主题：topic_test
        for (int i = 0; i < 1000; i++) {
            DMSMqttPublisher.DEFAULT.publishMessage("c", "hello", 1);
        }
//        boolean b = DMSMqttPublisher.DEFAULT.publishMessage("a", "hello", 1);
//        System.out.println("发布成功:" + b);
    }
}
