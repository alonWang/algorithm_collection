package com.github.alonwang.transport.core.protocol;

import com.github.alonwang.transport.protobuf.Base;
import com.google.protobuf.MessageLite;

/**
 * @author alonwang
 * @date 2020/7/13 17:37
 * @detail
 */
public class ProtobufRequest implements Request<MessageLite> {
    private Base.Request request;

    @Override
    public MessageHeader header() {
        return null;
    }

    @Override
    public MessageLite body() {
        return null;
    }
}
