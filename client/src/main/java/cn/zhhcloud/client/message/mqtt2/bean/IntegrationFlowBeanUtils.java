/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: DynamicInjectionBean
 * Author:   LoadHao
 * Date:     2019/6/18 9:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.message.mqtt2.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.dsl.context.IntegrationFlowBeanPostProcessor;

import java.util.UUID;

/**
 * 功能描述: <br>
 * 〈IntegrationFlowBeanUtils〉
 *
 * @author LoadHao
 * @date 2019/6/18
 */
public class IntegrationFlowBeanUtils extends IntegrationFlowBeanPostProcessor {

    private static ApplicationContext applicationContext;

    private IntegrationFlowBeanUtils() {
    }

    public static void setApplicationCxt(ApplicationContext context) {
        applicationContext = context;
    }

    public static Object postBeforeInitialization(Object bean) {
        IntegrationFlowBeanPostProcessor integrationFlowBeanPostProcessor = new IntegrationFlowBeanPostProcessor();
        integrationFlowBeanPostProcessor.setApplicationContext(applicationContext);
        return integrationFlowBeanPostProcessor.postProcessBeforeInitialization(bean, bean.getClass().getCanonicalName() + getRandomNumber());
    }

    public static Object postAfterInitialization(Object bean) throws BeansException {
        IntegrationFlowBeanPostProcessor integrationFlowBeanPostProcessor = new IntegrationFlowBeanPostProcessor();
        integrationFlowBeanPostProcessor.setApplicationContext(applicationContext);
        return integrationFlowBeanPostProcessor.postProcessAfterInitialization(bean, bean.getClass().getCanonicalName() + getRandomNumber());
    }

    private static String getRandomNumber() {
        return System.currentTimeMillis() + UUID.randomUUID().getLeastSignificantBits() + "";
    }
}
