package com.walmart.ip.model;

import com.walmart.ip.subscriber.Subscriber;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TopicSubscriber {

    private final AtomicInteger offset;
    private final Subscriber subscriber;

    public TopicSubscriber(Subscriber subscriber) {
        this.offset = new AtomicInteger(0);
        this.subscriber = subscriber;
    }
}
