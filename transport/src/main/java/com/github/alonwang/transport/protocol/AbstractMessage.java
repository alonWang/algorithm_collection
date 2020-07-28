package com.github.alonwang.transport.protocol;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author alonwang
 * @date 2020/7/13 17:15
 * @detail
 */
public abstract class AbstractMessage {
    private MessageHeader header;
    private Object body;

    public MessageHeader header() {
        return header;
    }

    public Object body() {
        return body;
    }
    public abstract void decode() throws InvalidProtocolBufferException;

    public abstract void encode();

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}