/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2019 www.iwhalecloud.com
 * FileName: SpringUitls
 * Author:   LoadHao
 * Date:     2019/6/14 9:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.zhhcloud.client.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 功能描述: <br>
 *
 * @author LoadHao
 * @date 2019/6/14
 */
@Slf4j
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext ctx;

    private SpringContextUtils() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static <T> T getBeanByType(Class<T> clazz) {
        Map map = ctx.getBeansOfType(clazz);
        if (map.size() == 1) {
            Object obj = null;
            for (Object o : map.keySet()) {
                obj = map.get(o);
            }
            return (T) obj;
        } else {
            if (map.size() == 0) {
                log.error("在Spring容器中没有类型为" + clazz.getName() + "的Bean");
            } else {
                log.error("在Spring容器中有多个类型为" + clazz.getName() + "的Bean");
            }
            return null;
        }
    }

    public static Object getBeanByName(String beanName) {

        if (beanName == null || "".equals(beanName) || beanName.length() == 0) {
            return null;
        }
        try {
            return ctx.getBean(beanName);
        } catch (BeansException e) {
            return null;
        }
    }

    public static <T> T getBeanByName(String beanName, Class<T> clazz) {
        return (T) getBeanByName(beanName);
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
}
