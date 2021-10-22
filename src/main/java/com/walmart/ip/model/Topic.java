package com.walmart.ip.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {
    private final String topicName;
    private final String topicId;

    private final List<Message> messages;
    private final List<TopicSubscriber> topicSubscribers;


    public Topic(String topicName, String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
        this.topicSubscribers = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    public List<TopicSubscriber> getTopicSubscribers() {
        return new ArrayList<>(topicSubscribers);
    }

    public synchronized void addMessage(@NonNull final Message message) {
        this.messages.add(message);
    }
    public synchronized void addTopicSubscriber(@NonNull final TopicSubscriber topicSubscriber) {
        this.topicSubscribers.add(topicSubscriber);
    }
}
