package com.walmart.ip.subscriber;

import com.walmart.ip.model.Message;

public interface Subscriber {

    public String getId();
    public void consume(Message msg) throws InterruptedException;
}
