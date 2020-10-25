package model;

import java.util.Objects;

public class Orientation {

    public enum Direction {N, S, E, W}

    private Direction direction;

    public Orientation(Direction direction) {
        this.direction = direction;
    }

    public Orientation(char dir) {
        this.direction = addDirection(dir);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Orientation{" +
                "direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orientation that = (Orientation) o;
        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction);
    }

    private Direction addDirection(char dir) {
        switch (dir) {
            case 'N':
                return Direction.N;
            case 'S':
                return Direction.S;
            case 'E':
                return Direction.E;
            case 'W':
                return Direction.W;
            default:
                throw new IllegalStateException("Direction is invalid " + dir);
        }
    }

}
