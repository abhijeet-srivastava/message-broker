package com.walmart.ip.handler;

import com.walmart.ip.model.Message;
import com.walmart.ip.model.Topic;
import com.walmart.ip.model.TopicSubscriber;
import lombok.NonNull;
import lombok.SneakyThrows;

public class SubscriberWorker implements Runnable{
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(@NonNull final Topic topic, @NonNull final TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (this.topicSubscriber) {
            do {
                int currOffSet = this.topicSubscriber.getOffset().get();
                while(currOffSet >= this.topic.getMessages().size()) {
                    topicSubscriber.wait();
                }
                Message message = this.topic.getMessages().get(currOffSet);
                this.topicSubscriber.getSubscriber().consume(message);

                this.topicSubscriber.getOffset().compareAndSet(currOffSet, currOffSet+1);
            } while (true);
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
