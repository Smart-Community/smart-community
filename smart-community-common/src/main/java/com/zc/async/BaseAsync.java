package com.zc.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;

/**
 * @author 小帅气
 * @create 2020-02-23-22:08
 */
@Lazy(true)
public interface BaseAsync {

    Logger LOG = LoggerFactory.getLogger(BaseAsync.class);

    /**
     * 抽象异步方法用户处理简单异步操作
     *
     * @param objects
     */
    @Async
    void asyncMethod(Object... objects);
}
