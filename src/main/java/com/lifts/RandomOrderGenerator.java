package com.lifts;

import java.util.Random;

public class RandomOrderGenerator {

    private static final Random random = new Random();

    public static Order generateOrder() {
        Integer fromLevel = random.nextInt((Lift.MAX_LEVEL - Lift.MIN_LEVEL) + 1) + Lift.MIN_LEVEL;
        Integer toLevel = random.nextInt((Lift.MAX_LEVEL - Lift.MIN_LEVEL) + 1) + Lift.MIN_LEVEL;
        if (fromLevel != toLevel) {
            return new Order(fromLevel, toLevel);
        } else {
            return generateOrder();
        }
    }

    private RandomOrderGenerator(){
    }
}
