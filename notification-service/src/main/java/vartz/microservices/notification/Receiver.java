package vartz.microservices.notification;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class Receiver {

    private final ObservationRegistry registry;
    private final Tracer tracer;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        Observation.createNotStarted("on-message", this.registry).observe(() -> {
            log.info("Received order: {}", message);
            log.info("Tracing: {}", this.tracer.currentSpan().context().traceId());
            latch.countDown();
        });
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
