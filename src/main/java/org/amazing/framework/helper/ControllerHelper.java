package org.amazing.framework.helper;

import org.amazing.framework.annotation.Action;
import org.amazing.framework.bean.Handler;
import org.amazing.framework.bean.Request;
import org.amazing.framework.util.PrintHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by alex on 17-7-5.
 */
public final class ControllerHelper {
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            //遍历这些controller类
            for (Class<?> controllerClass : controllerClassSet) {
                //获取controller类中定义的方法
                Method[] methods = controllerClass.getMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    //遍历controller类中定义的方法
                    for (Method method : methods) {
                        //判断当前方法是否含有Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            //从Action中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //验证url映射规则
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                                    //获取请求方法与请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
