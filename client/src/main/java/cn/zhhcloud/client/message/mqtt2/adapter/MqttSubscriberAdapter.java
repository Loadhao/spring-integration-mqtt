/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: MyIntegrationFlowAdapter
 * Author:   LoadHao
 * Date:     2019/6/14 10:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.adapter;

import cn.zhhcloud.client.message.mqtt2.channel.interceptor.MqttChannelInterptor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlowAdapter;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * 功能描述: <br>
 * 〈订阅：集成流适配器〉
 *
 * @author LoadHao
 * @date 2019/6/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MqttSubscriberAdapter<T extends MessageHandler> extends IntegrationFlowAdapter {

    private T messageHandler;
    private String clientId;
    private String[] topics;
    private int[] qos = {1};
    private MqttPahoMessageDrivenChannelAdapter adapter;
    private MqttPahoClientFactory mqttPahoClientFactory;

    /**
     * 流定义
     */
    @Override
    protected IntegrationFlowDefinition<?> buildFlow() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                clientId, mqttPahoClientFactory);
        adapter.addTopic(topics);
        adapter.setQos(qos);
        adapter.setOutputChannel(messageSubscriberChannel());
        this.adapter = adapter;
        return from(adapter).handle(messageHandler);
    }

    /**
     * 订阅消息通道
     */
    private MessageChannel messageSubscriberChannel() {
        DirectChannel directChannel = new DirectChannel();
        //通道拦截器
        directChannel.addInterceptor(new MqttChannelInterptor());
        return directChannel;
    }
}
