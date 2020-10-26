package model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CommandsTest {

    @Test
    void addCommands() {
        // given
        Commands commands = new Commands();

        // when
        commands.addCommands(0, "RL");
        commands.addCommands(1, "F");

        // then
        Map<Integer, List<Commands.Command>> expectedCommands = ImmutableMap.of(
                0 , ImmutableList.of(Commands.Command.R, Commands.Command.L),
                1 , ImmutableList.of(Commands.Command.F)
        );

        assertEquals(expectedCommands, commands.getCommands());
    }

    @Test
    void addCommandsThrowsIllegalState() {
        // given
        Commands commands = new Commands();

        // when
        assertThrows(IllegalStateException.class, () -> commands.addCommands(0, "0"));

        // then
    }
}