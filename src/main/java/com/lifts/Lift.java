package com.lifts;

public interface Lift {

    Integer MAX_LEVEL = 10;
    Integer MIN_LEVEL = -2;

    Direction switchDirection();

    void pickOrder(Integer start,  Integer destination);

    Direction whereIsGoing();

    Integer getPresentPosition();

    Boolean isFreeToTakeOrder();

    void changeDirection(Direction direction);

    void move();


}
