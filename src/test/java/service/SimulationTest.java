package service;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.*;
import model.Commands.Command;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class SimulationTest {

    @Test
    void run() {
        // given
        Scenario scenarioMock = mock(Scenario.class);
        Lawn expectedLawn = new Lawn(5,5);
        given(scenarioMock.getLawn()).willReturn(expectedLawn);
        BiMap<Position, Integer> initialMowersPositions = ImmutableBiMap.of(
                new Position(1,1), 0,
                new Position(2,2), 1);
        given(scenarioMock.getMowersPositions()).willReturn(initialMowersPositions);
        Map<Integer, Orientation> initialOrientations = ImmutableMap.of(
                0 , new Orientation('N'),
                1 , new Orientation('S'));
        given(scenarioMock.getMowersOrientations()).willReturn(initialOrientations);

        Commands commandsMock = mock(Commands.class);
        Map<Integer, List<Command>> expectedCommands = ImmutableMap.of(
                0 , ImmutableList.of(Command.R, Command.L),
                1 , ImmutableList.of(Command.F)
                );
        given(commandsMock.getCommands()).willReturn(expectedCommands);

        // when
        Simulation simulation = new Simulation(scenarioMock, commandsMock);
        Scenario scenario = simulation.run();

        // then
        BiMap<Position, Integer> expectedMowersPositions = ImmutableBiMap.of(
                new Position(1,1), 0,
                new Position(2,1), 1);
        Map<Integer, Orientation> expectedMowersOrientations = ImmutableMap.of(
                0 , new Orientation('N'),
                1 , new Orientation('S'));

        assertEquals(expectedLawn, scenario.getLawn());
        assertNotSame(expectedLawn, scenario.getLawn());
        assertEquals(expectedMowersPositions, scenario.getMowersPositions());
        assertNotSame(expectedMowersPositions, scenario.getMowersPositions());
        assertEquals(expectedMowersOrientations, scenario.getMowersOrientations());
        assertNotSame(expectedMowersOrientations, scenario.getMowersOrientations());
    }

    @Test
    void runMowerCollisionScenario() {
        // given
        Scenario scenarioMock = mock(Scenario.class);
        given(scenarioMock.getLawn()).willReturn(new Lawn(5,5));
        BiMap<Position, Integer> initialMowersPositions = ImmutableBiMap.of(
                new Position(1,1), 0,
                new Position(1,2), 1);
        given(scenarioMock.getMowersPositions()).willReturn(initialMowersPositions);
        Map<Integer, Orientation> initialOrientations = ImmutableMap.of(
                0 , new Orientation('N'),
                1 , new Orientation('S'));
        given(scenarioMock.getMowersOrientations()).willReturn(initialOrientations);

        Commands commandsMock = mock(Commands.class);
        Map<Integer, List<Command>> expectedCommands = ImmutableMap.of(
                0 , ImmutableList.of(Command.F, Command.L),
                1 , ImmutableList.of(Command.F, Command.R)
        );
        given(commandsMock.getCommands()).willReturn(expectedCommands);

        // when
        Simulation simulation = new Simulation(scenarioMock, commandsMock);
        Scenario scenario = simulation.run();

        // then
        BiMap<Position, Integer> expectedMowersPositions = ImmutableBiMap.of(
                new Position(1,1), 0,
                new Position(1,2), 1);
        Map<Integer, Orientation> expectedMowersOrientations = ImmutableMap.of(
                0 , new Orientation('W'),
                1 , new Orientation('W'));

        assertEquals(expectedMowersPositions, scenario.getMowersPositions());
        assertEquals(expectedMowersOrientations, scenario.getMowersOrientations());
    }

}