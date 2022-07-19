package com.yangjq.mpcommons.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yangjq.commons.Result;
import com.yangjq.mpcommons.PageHandler;
import com.yangjq.mpcommons.query.QueryCriteria;
import com.yangjq.mpcommons.query.QueryWrapperBuilder;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 基础控制器：包含基本的增删改查
 *
 * @param <S> Service类型
 * @param <T> Entity类型
 * @param <Q> QueryCriteria类型
 * @author yangjq
 * @since 2022/06/28
 */
public abstract class BaseController<S extends BaseService<T>, T extends BaseEntity, Q extends QueryCriteria> {

  @Autowired
  protected S baseService;

  @ApiOperation("新增")
  @PostMapping(value = "/save")
  public Result create(@Validated @RequestBody T resource) {
    //不接收id
    resource.setId(null);
    baseService.save(resource);
    return Result.success();
  }

  @ApiOperation("修改 by Id")
  @PostMapping(value = "/update/{id}")
  public Result update(@PathVariable String id, @RequestBody T resource) {
    //通过接收id参数保证id不为null
    resource.setId(id);
    baseService.updateById(resource);
    return Result.success();
  }

  @ApiOperation("删除 by Id")
  @PostMapping(value = "/delete/{id}")
  public Result delete(@PathVariable String id) {
    baseService.removeById(id);
    return Result.success();
  }

  @ApiOperation("查询 by Id")
  @GetMapping(value = "/query/{id}")
  public Result<T> query(@PathVariable String id) {
    return Result.success(baseService.getById(id));
  }

  @ApiOperation("查询 by Page")
  @GetMapping(value = "/list")
  protected Result<PageHandler<T>> list(Q criteria) {
    Wrapper<T> wrapper = QueryWrapperBuilder.build(baseService.getEntityClass(), criteria);
    PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
    List<T> list = baseService.list(wrapper);
    return Result.success(PageHandler.of((Page<T>) list));
  }

}
