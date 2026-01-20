package com.example.railway.service;

import com.example.railway.broker.EventSubscriber;
import com.example.railway.dto.event.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PaymentService implements EventSubscriber<PaymentEvent> {

    private static final Logger log =
            LoggerFactory.getLogger(PaymentService.class);

    // Idempotency store
    private final Set<String> processedTransactions =
            ConcurrentHashMap.newKeySet();

    @Override
    public void handle(PaymentEvent event) {

        // 🔑 Idempotency check
        if (!processedTransactions.add(event.getTransactionId())) {
            log.warn(
                    "Transaction {} already processed. Ignoring duplicate.",
                    event.getTransactionId()
            );
            return;
        }

        // Simulate payment processing
        log.info(
                "Money deducted for transaction {} (amount {})",
                event.getTransactionId(),
                event.getAmount()
        );

    }
}
