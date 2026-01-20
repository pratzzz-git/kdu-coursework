package com.example.railway.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DeadLetterQueue<T> {

    private static final Logger log =
            LoggerFactory.getLogger(DeadLetterQueue.class);

    private final Queue<T> dlq = new ConcurrentLinkedQueue<>();

    public void add(T event) {
        dlq.add(event);
        log.error("Message moved to DLQ: {}", event);
    }

    public List<T> getAll() {
        return List.copyOf(dlq);
    }

    public int size() {
        return dlq.size();
    }
}
