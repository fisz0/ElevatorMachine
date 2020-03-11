package com.lifts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void switchDirection() {
        Direction direction = Direction.DOWN;
        assertEquals(Direction.UP, direction.switchDirection());
    }
}