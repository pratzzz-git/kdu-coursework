package com.example.railway.broker;

public interface EventBroker<T> {

    void publish(T event);

    void subscribe(EventSubscriber<T> subscriber);
}
