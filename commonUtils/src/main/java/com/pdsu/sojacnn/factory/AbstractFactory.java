package com.pdsu.sojacnn.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

/**
 * @author 半梦
 * @create 2021-05-10 17:39
 */
public abstract class AbstractFactory<T> {

    private static final Logger log = LoggerFactory.getLogger(NewsThemeFactory.class);

    /**
     * 创建一个对象
     */
    public abstract T create() throws NoSuchMethodException;

    /**
     * 根据参数创建一个对象
     * <li>k: 变量名
     * <li>v: 值
     */
    public abstract T create(Map<String, Object> args) throws NoSuchMethodException;

    /**
     * 根据参数创建一个对象
     * allArgs: true 全参
     * false 非全参
     */
    public abstract T create(boolean allArgs, Map<String, Object> args) throws NoSuchMethodException;

    protected T create(@NonNull Class<T> clazz, @NonNull Map<String, Object> args) throws NoSuchMethodException {
        return create(false, clazz, args);
    }

    protected T create(@NonNull Class<T> clazz) throws NoSuchMethodException{
        return create(clazz, Collections.emptyMap());
    }

    protected T create(boolean allArgs, @NonNull Class<T> clazz, @NonNull Map<String, Object> args) throws NoSuchMethodException {
        if(allArgs) {
            return BeanUtils.instantiateClass(clazz.getConstructor(), args.values().toArray());
        } else {
            T t = BeanUtils.instantiateClass(clazz);
            fieldBean(t, args);
            return t;
        }
    }

    private void fieldBean(T t, @NonNull Map<String, Object> args) {
        args.forEach((k, v) -> {
            try {
                doField(t, k, v);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.debug("field bean error", e);
            }
        });
    }

    private void doField(T t, @NonNull String key, @NonNull Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = t.getClass().getDeclaredField(key);
        field.setAccessible(true);
        field.set(t, value);
    }


}
