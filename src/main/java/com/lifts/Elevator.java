package com.lifts;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Elevator implements Lift {

    private String liftId;
    private Direction direction;
    private Integer currentLevel;
    private Integer destination;
    private Integer start;

    public Elevator(String liftId, Direction direction, Integer initialLevel) {
        this.liftId = liftId;
        this.direction = direction;
        this.currentLevel = initialLevel;
    }

    @Override
    public void move() throws IllegalArgumentException {
        if (destination == null) {
            System.out.println("No destination set to this elevator");
            return;
        }
        if (currentLevel < start) this.goUp(start);
        else this.goDown(start);
        if (currentLevel < destination) this.goUp(destination);
        else this.goDown(destination);
        this.start = null;
        this.destination = null;
        System.out.println("Elevator " + this.liftId + " reached destination- free to take another order");
    }

    private void goUp(Integer startingPoint) {
        while (currentLevel < startingPoint) this.goUpByOneFloor();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goDown(Integer startingPoint) {
        while (currentLevel > startingPoint) this.goDownByOneFloor();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goUpByOneFloor() {
        this.currentLevel += 1;
        //System.out.println("Current level: "+this.currentLevel);
    }

    private void goDownByOneFloor() {
        this.currentLevel -= 1;
        //System.out.println("Current level: "+this.currentLevel);
    }

    public void pickOrder(Integer start, Integer destination) {
        this.destination = destination;
        this.start = start;
    }

    @Override
    public Direction whereIsGoing() {
        return this.direction;
    }

    @Override
    public Integer getPresentPosition() {
        return this.currentLevel;
    }

    @Override
    public Boolean isFreeToTakeOrder() {
        return this.destination == null && this.start == null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Direction switchDirection() {
        this.direction = this.direction.switchDirection();
        return this.direction;
    }

    @Override
    public void changeDirection(Direction direction) {
        this.direction = direction;
    }
}
