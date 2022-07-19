package com.yangjq.mpcommons.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangjq.mpcommons.annotation.QueryOperator;
import com.yangjq.mpcommons.annotation.QueryOperator.Type;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * QueryWrapper构造器
 *
 * @author yangjq
 * @since 2022/7/18
 */
public class QueryWrapperBuilder {

  public static <T, Q extends QueryCriteria> Wrapper<T> build(Class<T> clazz, Q query) {
    QueryWrapper<T> queryWrapper = new QueryWrapper<>();
    if (query == null) {
      return queryWrapper;
    }

    List<Field> fields = getFieldsWithAnnotation(query.getClass());
    QueryOperator c;
    for (Field field : fields) {
      c = field.getAnnotation(QueryOperator.class);
      String name = hump2Underline2(c.propName().trim().length()>0? c.propName():field.getName());
      field.setAccessible(true);
      Object val = null;
      try {
        val = field.get(query);
        if (val == null || "".equals(val)){
          continue;
        }
        //Set居然可以传null进来...
        if (val instanceof Set){
          if (((Set<?>) val).size() == 1 && ((Set<?>) val).iterator().next() == null){
            continue;
          }
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace(); //ignore
      }
      Type type = c.type();

      if (type.equals(Type.EQ)){
        queryWrapper.eq(name, val);
      } else if (type.equals(Type.NE)) {
        queryWrapper.ne(name, val);
      } else if (type.equals(Type.GT)) {
        queryWrapper.gt(name, val);
      } else if (type.equals(Type.LT)) {
        queryWrapper.lt(name, val);
      } else if (type.equals(Type.LIKE)) {
        queryWrapper.like(name, val);
      } else if (type.equals(Type.ASC)) {
        queryWrapper.orderByAsc((String) val);
      } else if (type.equals(Type.DESC)) {
        queryWrapper.orderByDesc((String) val);
      } else if (type.equals(Type.IN)) {
        queryWrapper.in(name, (Collection<Integer>) val);
      }
    }

    return queryWrapper;
  }

  /**
   * 获得clazz中带有@QueryOperator的Field
   */
  private static List<Field> getFieldsWithAnnotation(Class clazz){
    List<Field> result = new ArrayList<>();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (field.getAnnotation(QueryOperator.class) !=null){
        result.add(field);
      }
    }
    return result;
  }

  /**
   * 驼峰命名转为下划线
   * @return
   */
  @Deprecated
  private static String hump2Underline(String str){
    StringBuilder sb = new StringBuilder();
    boolean flag = true;
    for (int i=0,j=0;; i++){
      if (str.charAt(i) < 97){
        flag = false;
        sb.append(str.substring(j, i)).append("_").append((char)(str.charAt(i)+32));
        j=i+1;
      }
      if (i == str.length()-1){
        sb.append(str.substring(j));
        break;
      }
    }
    //str全小写
    if (flag){
      sb.append(str);
    }
    return sb.toString();
  }

  /**
   * 驼峰命名转为下划线
   * @return
   */
  private static String hump2Underline2(String str){
    StringBuilder sb = new StringBuilder(str);
    int temp=0;
    for (int i=0; i<str.length(); i++){
      if (Character.isUpperCase(str.charAt(i))){
        sb.setCharAt(i+temp, (char)(str.charAt(i)+32));
        sb.insert(i+temp++, "_");
      }
    }
    return sb.toString();
  }

}
