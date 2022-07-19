package com.yangjq.mpcommons.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yangjq
 * @Description：标记类
 * @Date：Created in 17:00 2021/12/11
 * @Modified by:
 */
@Getter
@Setter
public abstract class QueryCriteria {

  private Integer pageNum;

  private Integer pageSize;

  public Integer getPageSize() {
    return pageSize == null ? 20 : pageSize;
  }

  public Integer getPageNum() {
    return pageNum == null ? 1 : pageNum;
  }

}
