package model;

import model.Orientation.Direction;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ScenarioTest {

    @Test
    void moveMowerForwardCollisionScenario() {
        // given
        Scenario scenario = new Scenario();
        scenario.setLawn(new Lawn(5,5));
        scenario.addMowersCoordinate(1,1, 'N', 0);
        scenario.addMowersCoordinate(1,2, 'S', 1);

        // when
        scenario.moveMowerForward(0);
        scenario.moveMowerForward(1);

        // then
        assertEquals(new Position(1,1), scenario.getMowersPositions().inverse().get(0));
        assertEquals(new Position(1,2), scenario.getMowersPositions().inverse().get(1));

    }

    @Test
    void moveMowerForwardLawnBorder() {
        // given
        Scenario scenario = new Scenario();
        scenario.setLawn(new Lawn(5,5));
        scenario.addMowersCoordinate(5,5, 'N', 0);

        // when
        scenario.moveMowerForward(0);

        // then
        assertEquals(new Position(5,5), scenario.getMowersPositions().inverse().get(0));

    }

    @Test
    void rotateMower() {
        // given
        Scenario scenario = new Scenario();
        scenario.setLawn(new Lawn(5,5));
        scenario.addMowersCoordinate(1,1, 'N', 0);
        scenario.addMowersCoordinate(2,2, 'N', 1);

        //when
        List<Direction> directions = Arrays.asList(Direction.E, Direction.S, Direction.W, Direction.N);
        for(Direction direction: directions) {
            scenario.rotateMower(0, Commands.Command.R);
            assertEquals(new Orientation(direction), scenario.getMowersOrientations().get(0));
        }
        directions = Arrays.asList(Direction.W, Direction.S, Direction.E, Direction.N);
        for(Direction direction: directions) {
            scenario.rotateMower(0, Commands.Command.L);
            assertEquals(new Orientation(direction), scenario.getMowersOrientations().get(0));
        }

        //then

    }

    @Test
    void addMowersCoordinateThrowsIllegalState() {
        // given
        Scenario scenario = new Scenario();
        scenario.setLawn(new Lawn(5,5));

        // when
        assertThrows(IllegalStateException.class, () -> scenario.addMowersCoordinate(10,4,'N', 0));
        assertThrows(IllegalStateException.class, () -> scenario.addMowersCoordinate(4,10,'N', 0));
        assertThrows(IllegalStateException.class, () -> scenario.addMowersCoordinate(10,10,'N', 0));

        // then

    }
}