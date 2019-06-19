/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MqttConnOptionsConfig
 * Author:   LoadHao
 * Date:     2019/6/11 19:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.connection;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.stereotype.Component;

/**
 * 功能描述: <br>
 * 〈MQTT连接属性配置〉
 *
 * @author LoadHao
 * @date 2019/6/11
 */
@Component
@ConditionalOnBean(MqttConnOptions.class)
public class MqttClientConnFactory {

    @Autowired
    private MqttConnOptions mqttConnOptions;

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttConnOptions.getUsername());
        mqttConnectOptions.setPassword(mqttConnOptions.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs(mqttConnOptions.getUrl());
        //心跳
        mqttConnectOptions.setKeepAliveInterval(Integer.valueOf(mqttConnOptions.getKeepAliveInterval()));
        //连接超时
        mqttConnectOptions.setConnectionTimeout(Integer.valueOf(mqttConnOptions.getCompletionTimeout()));
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setMaxInflight(100000);
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory(MqttConnectOptions mqttConnectOptions) {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttConnectOptions);
        return factory;
    }
}

