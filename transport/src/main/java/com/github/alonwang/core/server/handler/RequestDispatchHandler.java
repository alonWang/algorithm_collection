package com.github.alonwang.core.server.handler;


import com.github.alonwang.core.Context;
import com.github.alonwang.core.protocol.Request;
import com.github.alonwang.core.server.task.MessageTask;
import com.github.alonwang.core.server.task.MethodWrapper;
import com.github.alonwang.core.server.task.User;
import com.google.common.base.Preconditions;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 找到channel对应的User，并将消息分发到业务线程池
 *
 * @author alonwang
 * @date 2020/7/13 16:46
 * @detail
 */
@ChannelHandler.Sharable
public class RequestDispatchHandler extends SimpleChannelInboundHandler<Request> {
    private static Map<Channel, User> channel2SessionMap = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        User user = channel2SessionMap.compute(ctx.channel(), (c, s) -> {
            if (null == s) {
                User createUser = new User();
                createUser.setChannel(c);
                return createUser;
            } else {
                return s;
            }

        });
        MethodWrapper wrapper = Context.getMethodRegistry().getWrapper(msg.header().getModuleId(),
                msg.header().getCommandId());
        Preconditions.checkNotNull(wrapper);
        user.execute(new MessageTask(wrapper, msg));
    }
}