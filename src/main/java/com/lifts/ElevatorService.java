package com.lifts;

import java.util.*;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class ElevatorService implements LiftService {

    private static final ExecutorService executor = Executors.newFixedThreadPool(6);

    private final Collection<Lift> lifts = new ArrayList<>();

    public ElevatorService() {
        lifts.add(new Elevator("A", Direction.UP, -2));
        lifts.add(new Elevator("B", Direction.UP, -1));
        lifts.add(new Elevator("C", Direction.UP, 0));
        lifts.add(new Elevator("D", Direction.DOWN, 10));
        lifts.add(new Elevator("E", Direction.DOWN, 9));
        lifts.add(new Elevator("F", Direction.DOWN, 8));

    }

    @Override
    public void assignOrderToLift(Order order) {
        Optional<Lift> optionalLift = this.findBestMatchingLift(order);
        optionalLift.ifPresent(lift -> {
            lift.pickOrder(order.getFromLevel(), order.getToLevel());
            System.out.println("Order: " + order + " assigned to elevator: " + lift);
            lift.move();
        });

    }

    private Optional<Lift> findBestMatchingLift(Order order) {
        Direction orderDirection = order.getFromLevel() - order.getToLevel() < 0 ? Direction.UP : Direction.DOWN;
        Collection<Lift> liftsInTheSameDirection = this.filterMatchingElevators(orderDirection, order.getFromLevel());
        Optional<Lift> chosenLift;
        if (liftsInTheSameDirection.isEmpty())
            chosenLift = Optional.ofNullable(this.findFirstFreeLift(order.getFromLevel(), order.getToLevel()));
        else
            chosenLift = Optional.ofNullable(this.findClosestLift(order.getFromLevel(), liftsInTheSameDirection));

        return chosenLift;
    }

    private Collection<Lift> filterMatchingElevators(Direction direction, Integer fromLevel) {
        return direction == Direction.UP ?
                this.lifts.stream().filter(lift -> {
                    return lift.isFreeToTakeOrder() && lift.whereIsGoing() == direction && lift.getPresentPosition() <= fromLevel;
                }).collect(Collectors.toList()) :
                this.lifts.stream().filter(lift -> {
                    return lift.isFreeToTakeOrder() && lift.whereIsGoing() == direction && lift.getPresentPosition() >= fromLevel;
                }).collect(Collectors.toList());
    }

    private Lift findFirstFreeLift(Integer fromLevel, Integer toLevel) {
        List<Lift> freeLifts = this.lifts.stream().filter(currLift -> currLift.isFreeToTakeOrder()).collect(Collectors.toList());
        if (freeLifts.isEmpty()) return null;
        Lift lift = findClosestLift(fromLevel, lifts);
        if (fromLevel < toLevel)
            lift.changeDirection(Direction.UP);
        else lift.changeDirection(Direction.DOWN);


        return lift;
    }

    private Lift findClosestLift(Integer fromLevel, Collection<Lift> lifts) {
        Iterator<Lift> iterator = lifts.iterator();
        Lift lift = iterator.next();
        while (iterator.hasNext()) {
            Lift currLift = iterator.next();
            if (Math.abs(currLift.getPresentPosition() - fromLevel) < Math.abs(lift.getPresentPosition() - fromLevel))
                lift = currLift;
        }
        return lift;
    }
}
