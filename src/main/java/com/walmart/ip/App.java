package com.walmart.ip;

import com.walmart.ip.model.Message;
import com.walmart.ip.model.Topic;
import com.walmart.ip.queue.MessageQueue;
import com.walmart.ip.queue.Queue;
import com.walmart.ip.subscriber.SleepingSubscriber;
import com.walmart.ip.subscriber.Subscriber;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Message Broker demo!" );
        final Queue  queue = new MessageQueue();

        Topic topic1= queue.createTopic("topic1");
        Topic topic2= queue.createTopic("topic2");
        Topic topic3 = queue.createTopic("topic3");

        Subscriber subscriber1 = new SleepingSubscriber("sub_1", 100);
        Subscriber subscriber2 = new SleepingSubscriber("sub_2", 200);
        Subscriber subscriber3 = new SleepingSubscriber("sub_3", 400);

        queue.subscribe(subscriber1, topic2);
        queue.subscribe(subscriber1, topic3);
        queue.subscribe(subscriber2, topic3);
        queue.subscribe(subscriber2, topic1);
        queue.subscribe(subscriber3, topic1);
        queue.subscribe(subscriber3, topic2);

        Message msg1 = new Message("msg1");
        Message msg2 = new Message("msg2");
        Message msg3 = new Message("msg3");
        Message msg4 = new Message("msg4");
        Message msg5 = new Message("msg5");
        Message msg6 = new Message("msg6");
        queue.publish(topic1, msg1);
        queue.publish(topic1, msg2);
        queue.publish(topic2, msg2);
        queue.publish(topic2, msg3);
        queue.publish(topic3, msg4);
        queue.publish(topic3, msg4);
        queue.publish(topic2, msg5);
        queue.publish(topic1, msg6);
    }
}
