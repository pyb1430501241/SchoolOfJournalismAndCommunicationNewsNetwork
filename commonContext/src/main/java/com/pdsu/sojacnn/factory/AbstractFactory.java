package com.pdsu.sojacnn.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author 半梦
 * @create 2021-05-10 17:39
 */
public abstract class AbstractFactory<T> {

    private static final Logger log = LoggerFactory.getLogger(AbstractFactory.class);

    /**
     * 需要生产的类
     */
    @NonNull
    private final Class<T> targetClass;

    public Class<T> getTargetClass() {
        return this.targetClass;
    }

    public String getTargetClassName() {
        return this.targetClass.getName();
    }

    protected AbstractFactory(@NonNull Class<T> clazz) {
        this.targetClass = clazz;
    }

    /**
     * 创建一个对象
     */
    @NonNull
    public T create() throws NoSuchMethodException {
        return create(Collections.emptyMap());
    }

    /**
     * 根据参数创建一个对象
     * allArgs: true 全参
     * false 非全参
     */
    @NonNull
    public T create(boolean allArgs, @NonNull Map<String, Object> args) throws NoSuchMethodException {
        return doCreate(allArgs, args);
    }

    /**
     * 重载, 默认非全参
     */
    @NonNull
    public T create(@NonNull Map<String, Object> args) throws NoSuchMethodException {
        return doCreate(false, args);
    }

    @NonNull
    private T doCreate(boolean allArgs, @NonNull Map<String, Object> args) throws NoSuchMethodException {
        if(allArgs) {
            return BeanUtils.instantiateClass(targetClass.getDeclaredConstructor(), args.values().toArray());
        } else {
            T t = BeanUtils.instantiateClass(targetClass);
            fieldBean(t, args);
            return t;
        }
    }

    private void fieldBean(@NonNull final T t, @NonNull Map<String, Object> args) {
        args.forEach((k, v) -> {
            try {
                doField(t, k, v);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.debug("field bean error", e);
            }
        });
    }

    private void doField(@NonNull T t, @NonNull String key, @NonNull Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = targetClass.getDeclaredField(key);
        field.setAccessible(true);
        field.set(t, value);
    }

}
