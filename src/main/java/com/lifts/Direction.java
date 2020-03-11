package com.lifts;

public enum Direction {
    DOWN, UP;

    public Direction switchDirection() {
        switch(this) {
            case UP: return Direction.DOWN;
            case DOWN: return Direction.UP;
            default: throw new IllegalStateException("This should never happen: " + this + " has no opposite.");
        }
    }
}
