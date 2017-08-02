package org.amazing.framework.helper;

import org.amazing.framework.annotation.Inject;
import org.amazing.framework.util.PrintHelper;
import org.amazing.framework.util.ReflectionUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 * Created by alex on 17-7-5.
 */
public class IocHelper {
    static {
        PrintHelper.print("IocHelper loading...");
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (MapUtils.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //从beanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取bean中所有的成员变量
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            PrintHelper.print("beanField " + beanField);
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setFidld(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
        PrintHelper.print("IocHelper end...");
    }
}
