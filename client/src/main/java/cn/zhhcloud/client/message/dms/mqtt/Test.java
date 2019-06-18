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
import cn.zhhcloud.client.message.mqtt2.bean.IntegrationFlowBeanUtils;
import cn.zhhcloud.client.message.mqtt2.connection.MqttConnOptions;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 功能描述: <br>
 * 〈〉
 *
 * @author LoadHao
 * @date 2019/6/14
 */
@Component
@ConditionalOnBean(MqttConnOptions.class)
public class Test {

    @Resource
    private MqttPahoClientFactory mqttPahoClientFactory;
    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    public void run() {
        IntegrationFlowBeanUtils.setApplicationCxt(applicationContext);
        //发布
        DMSMqttPublisher.setMqttPahoClientFactory(mqttPahoClientFactory);
        DMSMqttPublisher.init();

        //订阅
        DMSMqttSubscriber.setMqttPahoClientFactory(mqttPahoClientFactory);
        DMSMqttSubscriber.init();
    }

    /**
     * 发布消息测试
     */
    @Component
    public class Test2 implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            //订阅主题：topic_test
            DMSMqttSubscriber.CLIENT_1.addSubscribeTopic("b");
            DMSMqttSubscriber.CLIENT_2.addSubscribeTopic("c");

            //发布消息到主题：topic_test
            boolean b = DMSMqttPublisher.DEFAULT.publishMessage("topic_test", "hello", 1);
            System.out.println("发布成功:" + b);
        }
    }
}
