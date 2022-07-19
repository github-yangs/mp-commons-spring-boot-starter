package com.yangjq.mpcommons.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 *
 * 服务接口基类实现类
 *
 * @author yangjq
 * @since 2022/1/12
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends
    ServiceImpl<M, T> implements BaseService<T> {

}
