package com.example.railway.broker;

public interface EventSubscriber<T> {

    void handle(T event);
}
