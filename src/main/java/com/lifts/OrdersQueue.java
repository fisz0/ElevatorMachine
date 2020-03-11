package com.lifts;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

@Component
public final class OrdersQueue {

    private static final Queue<Order> queue = new LinkedList<>();

    public static void adToQueue(Order order) {
        queue.add(order);
    }

    public static Optional<Order> getNextOrder() {
        return Optional.ofNullable(queue.poll());
    }

    public static Integer queueSize() {
        return queue.size();
    }

    private OrdersQueue() {
    }
}
