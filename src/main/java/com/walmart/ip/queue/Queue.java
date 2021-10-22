package com.walmart.ip.queue;

import com.walmart.ip.model.Message;
import com.walmart.ip.model.Topic;
import com.walmart.ip.subscriber.Subscriber;
import lombok.NonNull;

public interface Queue {

    public Topic createTopic(@NonNull final String topicName);

    public void subscribe(@NonNull final Subscriber subscriber, @NonNull final Topic topic);

    public void publish(@NonNull final Topic topic, @NonNull final Message message);

    public void resetOffSet(@NonNull final  Topic topic, @NonNull final  Subscriber subscriber, @NonNull final  Integer newOffSet);

}
