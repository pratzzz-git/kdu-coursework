package com.example.railway.broker;

import org.springframework.core.task.TaskExecutor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleEventBroker<T> implements EventBroker<T> {

    private final List<EventSubscriber<T>> subscribers = new CopyOnWriteArrayList<>();
    private final TaskExecutor executor;

    public SimpleEventBroker(TaskExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void publish(T event) {
        subscribers.forEach(subscriber ->
                executor.execute(() -> subscriber.handle(event))
        );
    }

    @Override
    public void subscribe(EventSubscriber<T> subscriber) {
        subscribers.add(subscriber);
    }
}
