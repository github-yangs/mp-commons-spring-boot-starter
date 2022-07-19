package com.yangjq.mpcommons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 字段查询操作
 *
 * @Author yangjq
 * @Since 2022/1/12
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryOperator {

  /**
   * 字段名称：当数据库字段和参数字段不一致时，需要用该属性指定数据库字段（下划线类型）
   */
  String propName() default "";

  Type type() default Type.EQ;

  /**
   * 条件类型
   */
  enum Type{
    EQ, //相等
    NE, //不等
    LIKE, //模糊查询
    GT, //大于
    LT, //小于
    ASC, //排序
    DESC, //排序
    IN //在其中
  }

}
