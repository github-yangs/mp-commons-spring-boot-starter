package com.yangjq.mpcommons;

import com.github.pagehelper.Page;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分页数据封装类
 *
 * @author Created by macro on 2019/4/19.
 * @author yangjq
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageHandler<T> {

  private Integer pageNum;
  private Integer pageSize;
  private Integer totalPage;
  private Long total;
  private List<T> list;

  /**
   * 将MyBatis Plus 分页结果转化为通用结果
   */
  public static <T> PageHandler<T> of(Page<T> page) {
    return new PageHandler<>(
        page.getPageNum(),
        page.getPageSize(),
        page.getPages(),
        page.getTotal(),
        page
    );
  }
}
