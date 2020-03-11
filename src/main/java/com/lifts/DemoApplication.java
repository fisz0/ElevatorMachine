package com.lifts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
public class DemoApplication {

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final LiftService elevatorService = new ElevatorService();

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DemoApplication.class, args);

        executorService.submit(() -> {
            while (true) {
                Order order = RandomOrderGenerator.generateOrder();
                if (OrdersQueue.queueSize() < 100)
                    OrdersQueue.adToQueue(order);
               // Thread.sleep(50);
            }
        });

        while (true) {
            Optional<Order> orderOptional = OrdersQueue.getNextOrder();
            orderOptional.ifPresent(order -> {
                //System.out.println("Assigning order to elevator: " + order);
                DemoApplication.elevatorService.assignOrderToLift(order);
            });
        }
    }

}
