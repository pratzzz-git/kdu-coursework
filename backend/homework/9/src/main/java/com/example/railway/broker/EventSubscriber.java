package com.example.railway.broker;

/**
 * Contract for all event consumers.
 * Each subscriber handles a specific type of event.
 */
public interface EventSubscriber<T> {

    /**
     * Handle the incoming event.
     * Any exception thrown will be treated as a processing failure
     * and trigger retry / DLQ logic in the broker.
     */
    void handle(T event);
}
