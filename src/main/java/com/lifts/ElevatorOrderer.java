package com.lifts;

import org.springframework.stereotype.Service;

@Service
public class ElevatorOrderer implements LiftOrderer {

    @Override
    public void orderLift(Order order) {
        OrdersQueue.adToQueue(order);
    }
}
