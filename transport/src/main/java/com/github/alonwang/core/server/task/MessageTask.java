package com.github.alonwang.core.server.task;

import com.github.alonwang.core.protocol.Request;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息任务
 *
 * @author alonwang
 * @date 2020/7/28 11:11
 * @detail
 */
@Slf4j
public class MessageTask implements Task<User> {
    private final MethodWrapper wrapper;
    private final Request request;

    public MessageTask(MethodWrapper wrapper, Request request) {
        this.wrapper = wrapper;
        this.request = request;
    }

    @Override
    public void execute(User user) {
        try {
            wrapper.invoke(user, request);
        } catch (Exception e) {
            log.error("session task execute error", e);
        }
    }
}