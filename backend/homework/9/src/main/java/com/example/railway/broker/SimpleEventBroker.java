package com.example.railway.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleEventBroker<T> implements EventBroker<T> {

    private static final Logger log = LoggerFactory.getLogger(SimpleEventBroker.class);

    private final List<EventSubscriber<T>> subscribers = new CopyOnWriteArrayList<>();
    private final TaskExecutor executor;
    private final DeadLetterQueue<T> deadLetterQueue = new DeadLetterQueue<>();

    private static final int MAX_RETRIES = 3;

    public SimpleEventBroker(TaskExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void publish(T event) {
        RetryableEvent<T> retryableEvent = new RetryableEvent<>(event);

        subscribers.forEach(subscriber ->
                executor.execute(() ->
                        processWithRetry(subscriber, retryableEvent)
                )
        );
    }

    private void processWithRetry(EventSubscriber<T> subscriber,
                                  RetryableEvent<T> retryableEvent) {

        try {
            subscriber.handle(retryableEvent.getPayload());
        } catch (Exception ex) {

            retryableEvent.incrementRetry();

            log.warn(
                    "Processing failed (attempt {}): {}",
                    retryableEvent.getRetryCount(),
                    ex.getMessage()
            );

            if (retryableEvent.getRetryCount() >= MAX_RETRIES) {
                deadLetterQueue.add(retryableEvent.getPayload());
                return;
            }

            sleepBeforeRetry();
            processWithRetry(subscriber, retryableEvent);
        }
    }

    private void sleepBeforeRetry() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void subscribe(EventSubscriber<T> subscriber) {
        subscribers.add(subscriber);
    }
}
