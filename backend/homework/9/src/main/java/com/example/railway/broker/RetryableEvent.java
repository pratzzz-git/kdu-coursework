package com.example.railway.broker;

public class RetryableEvent<T> {

    private final T payload;
    private int retryCount;

    public RetryableEvent(T payload) {
        this.payload = payload;
        this.retryCount = 0;
    }

    public T getPayload() {
        return payload;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void incrementRetry() {
        this.retryCount++;
    }
}
