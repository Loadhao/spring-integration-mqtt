/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttClientStarter
 * Author:   LoadHao
 * Date:     2019/6/19 15:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.dms.mqtt.run;

import cn.zhhcloud.client.message.dms.mqtt.publisher.DMSMqttPublisher;
import cn.zhhcloud.client.message.dms.mqtt.subscriber.DMSMqttSubscriber;
import cn.zhhcloud.client.message.mqtt2.bean.IntegrationFlowBeanUtils;
import cn.zhhcloud.client.message.mqtt2.connection.MqttConnOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 功能描述: <br>
 * 〈Mqtt 客户端启动器〉
 *
 * @author LoadHao
 * @date 2019/6/19
 */
@Component
@ConditionalOnBean(MqttConnOptions.class)
public class MqttClientStarter {

    @Resource
    private MqttPahoClientFactory mqttPahoClientFactory;
    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    private void run() {
        //上下文配置
        IntegrationFlowBeanUtils.setApplicationCxt(applicationContext);

        //发布者注册
        DMSMqttPublisher.setMqttPahoClientFactory(mqttPahoClientFactory);
        DMSMqttPublisher.init();

        //订阅者注册
        DMSMqttSubscriber.setMqttPahoClientFactory(mqttPahoClientFactory);
        DMSMqttSubscriber.init();
    }
}
