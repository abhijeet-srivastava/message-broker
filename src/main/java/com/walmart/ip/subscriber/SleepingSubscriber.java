package com.walmart.ip.subscriber;

import com.walmart.ip.model.Message;

public class SleepingSubscriber implements Subscriber {
    private final String id;
    private final int sleepTimeInMillis;

    public SleepingSubscriber(String id, int sleepTimeInMillis) {
        this.id = id;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    public String getId() {
        return this.id;
    }

    public void consume(Message msg) throws InterruptedException {
        System.out.printf("Subscriber id %s has started consuming message[%s]\n", this.id, msg.getMessage());
        Thread.sleep(sleepTimeInMillis);
        System.out.printf("Subscriber id %s has consumed message [%s] \n", this.id, msg.getMessage());
    }
}
