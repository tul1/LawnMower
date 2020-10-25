package model;

import model.Commands.Command;
import model.Orientation.Direction;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Scenario {
    private BiMap<Position, Integer> mowersPositions;
    private Map<Integer, Orientation> mowersOrientations;
    private Lawn lawn;

    public Scenario() {
        mowersPositions = HashBiMap.create();
        mowersOrientations = new HashMap<>();
    }

    public Scenario(Scenario scenario) {
        this.mowersPositions = HashBiMap.create(scenario.getMowersPositions());
        this.mowersOrientations = new HashMap(scenario.getMowersOrientations());
        this.lawn = new Lawn(scenario.getLawn());
    }

    public Map<Integer, Orientation> getMowersOrientations() {
        return mowersOrientations;
    }

    public BiMap<Position, Integer> getMowersPositions() {
        return mowersPositions;
    }

    public Lawn getLawn() {
        return lawn;
    }

    public void setLawn(Lawn lawn) {
        this.lawn = lawn;
    }

    public void moveMowerForward(Integer id) {
        Position position = mowersPositions.inverse().get(id);
        Orientation orientation = mowersOrientations.get(id);
        switch (orientation.getDirection()) {
            case N:
                if (position.getY() + 1 <= lawn.getyMax()) {
                    move(id, position, position.getX(), position.getY() + 1, orientation);
                }
                break;
            case E:
                if (position.getX() + 1 <= lawn.getxMax()) {
                    move(id, position, position.getX() + 1, position.getY(), orientation);
                }
                break;
            case S:
                if (position.getY() - 1 >= 0) {
                    move(id, position, position.getX(), position.getY() - 1, orientation);
                }
                break;
            case W:
                if (position.getX() - 1 >= 0) {
                    move(id, position, position.getX() - 1, position.getY(), orientation);
                }
                break;
            default:
                throw new IllegalStateException("Orientation is invalid " + orientation);
        }
    }

    private void move(Integer id, Position oldPosition, int x, int y, Orientation o) {
        Position newPosition = new Position(x, y);
        if (!mowersPositions.containsKey(newPosition)) {
            mowersPositions.remove(oldPosition);
            mowersPositions.put(newPosition, id);
        }
    }

    public void rotateMower(Integer id, Commands.Command rotSens) {
        Orientation oldOrientation = mowersOrientations.get(id);
        Orientation newOrientation = rotate(oldOrientation, rotSens);
        mowersOrientations.put(id, newOrientation);
    }

    private static Orientation rotate(Orientation orientation, Commands.Command rotSens) {
        switch (orientation.getDirection()) {
            case N:
                return new Orientation(rotSens == Command.R ? Direction.E : Direction.W);
            case E:
                return new Orientation(rotSens == Command.R ? Direction.S : Direction.N);
            case S:
                return new Orientation(rotSens == Command.R ? Direction.W : Direction.E);
            case W:
                return new Orientation(rotSens == Command.R ? Direction.N : Direction.S);
            default:
                throw new IllegalStateException("Orientation is invalid " + orientation.getDirection());
        }
    }

    public void addMowersCoordinate(int xPos, int yPos, char orientation, int id) {
        Position p = new Position(xPos, yPos);
        Orientation o = new Orientation(orientation);
        mowersPositions.put(p, id);
        mowersOrientations.put(id, o);
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "mowersCoordinates=" + mowersPositions +
                ", lawn=" + lawn +
                '}';
    }

}
