package com.example.a_common.utils

import java.lang.reflect.ParameterizedType

/**
 * 工具类
 */
object Util {

    fun <T> getClass(t: Any): Class<T> {
        // 通过反射 获取父类的泛型（T）对应的class
        return (t.javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0]
                as Class<T>
    }

}