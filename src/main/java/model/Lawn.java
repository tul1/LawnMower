package model;

import java.util.Objects;

public class Lawn {
    private final int xMax;
    private final int yMax;

    public Lawn(int xMax, int yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public Lawn(Lawn lawn) {
        this.xMax = lawn.xMax;
        this.yMax = lawn.yMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lawn lawn = (Lawn) o;
        return xMax == lawn.xMax &&
                yMax == lawn.yMax;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xMax, yMax);
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }

    @Override
    public String toString() {
        return "Lawn{" +
                "xMax=" + xMax +
                ", yMax=" + yMax +
                '}';
    }
}