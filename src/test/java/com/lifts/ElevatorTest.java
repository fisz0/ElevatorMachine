package com.lifts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    private final Elevator lift = new Elevator("TestElevator", Direction.DOWN, 10);

    @Test
    void move() {
        this.lift.pickOrder(12,5);
        this.lift.move();
        assertEquals(null, this.lift.getStart());
        assertEquals(null, this.lift.getDestination());
    }

    @Test
    void pickOrder() {
        lift.pickOrder(12, 5);
        assertEquals(12, this.lift.getStart());
        assertEquals(5, this.lift.getDestination());
    }

    @Test
    void whereIsGoing() {
        Direction direction = lift.whereIsGoing();
        assertEquals(Direction.DOWN, direction);
    }

    @Test
    void getPresentPosition() {
        Integer presentPosition = lift.getPresentPosition();
        assertEquals(10, presentPosition);
    }

    @Test
    void isFreeToTakeOrder() {
        Boolean isFreeToTakeOrder = lift.isFreeToTakeOrder();
        assertEquals(Boolean.TRUE, isFreeToTakeOrder);
    }

    @Test
    void switchDirection() {
        Direction direction = this.lift.switchDirection();
        assertEquals(Direction.UP, direction);
    }

    @Test
    void changeDirection() {
        this.lift.changeDirection(Direction.UP);
        assertEquals(Direction.UP, this.lift.whereIsGoing());
    }
}