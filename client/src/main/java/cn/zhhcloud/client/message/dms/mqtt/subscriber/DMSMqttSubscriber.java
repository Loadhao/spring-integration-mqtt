/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: DMSMqttSubscriber
 * Author:   LoadHao
 * Date:     2019/6/18 16:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.dms.mqtt.subscriber;

import cn.zhhcloud.client.message.dms.mqtt.recv.ClientMessageHandler;
import cn.zhhcloud.client.message.mqtt2.adapter.AbstractMqttSubscriberAdapter;
import cn.zhhcloud.client.message.mqtt2.bean.IntegrationFlowBeanUtils;

/**
 * 功能描述: <br>
 * 〈DMS Mqtt消息订阅者〉
 *
 * @author LoadHao
 * @date 2019/6/18
 */
public class DMSMqttSubscriber extends AbstractMqttSubscriberAdapter {
    public static DMSMqttSubscriber CLIENT_1;
    public static DMSMqttSubscriber CLIENT_2;

    /**
     * <功能描述>:
     * 初始化DMS相关的订阅通道
     *
     * @author LoadHao
     * @date 2019/6/18 15:52
     **/
    public static void init() {
        CLIENT_1 = (DMSMqttSubscriber) IntegrationFlowBeanUtils.postBeforeInitialization(test1());
        CLIENT_2 = (DMSMqttSubscriber) IntegrationFlowBeanUtils.postBeforeInitialization(test2());
    }

    private static DMSMqttSubscriber test1() {
        DMSMqttSubscriber test1 = new DMSMqttSubscriber();
        test1.setClientId("clientId1")
                .setTopic("a")
                .setMessageHandler(new ClientMessageHandler());

        return test1;
    }

    private static DMSMqttSubscriber test2() {
        DMSMqttSubscriber test2 = new DMSMqttSubscriber();
        test2.setClientId("clientId2")
                .setTopic("a")
                .setMessageHandler(new ClientMessageHandler());

        return test2;
    }
}
