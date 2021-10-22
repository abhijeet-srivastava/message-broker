package com.walmart.ip.queue;

import com.walmart.ip.handler.TopicHandler;
import com.walmart.ip.model.Message;
import com.walmart.ip.model.Topic;
import com.walmart.ip.model.TopicSubscriber;
import com.walmart.ip.subscriber.Subscriber;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessageQueue  implements Queue{
    private final Map<String, TopicHandler> topicProcessors;


    public MessageQueue() {
        topicProcessors = new HashMap<>();
    }

    @Override
    public Topic createTopic(@NonNull String topicName) {
        Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicProcessors.put(topic.getTopicId(), topicHandler);
        System.out.printf("Created topic: %s \n", topic.getTopicName());
        return topic;
    }

    @Override
    public void subscribe(@NonNull Subscriber subscriber, @NonNull Topic topic) {
        TopicSubscriber topicSubscriber = new TopicSubscriber(subscriber);
        topic.addTopicSubscriber(topicSubscriber);
        System.out.printf("Subscriber[%s] subscribed to topic: [%s]\n", subscriber.getId(), topic.getTopicName());
    }

    @Override
    public void publish(@NonNull Topic topic, @NonNull Message message) {
        topic.addMessage(message);
        new Thread(() -> this.topicProcessors.get(topic.getTopicId()).publish()).start();

    }

    @Override
    public void resetOffSet(@NonNull Topic topic, @NonNull Subscriber subscriber, @NonNull Integer newOffSet) {
        for(TopicSubscriber topicSubscriber: topic.getTopicSubscribers()) {
            if(topicSubscriber.getSubscriber().getId().equals(subscriber.getId())) {
                topicSubscriber.getOffset().set(newOffSet);
                System.out.printf("Subscribers [%s] off set is reset to : %d\n", topicSubscriber.getSubscriber().getId(), newOffSet);
                new Thread(() -> topicProcessors.get(topic.getTopicId()).startSubscriberWorker(topicSubscriber)).start();
                break;
            }
        }
    }
}
