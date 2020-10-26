package service;

import model.Commands;
import model.Lawn;
import model.Scenario;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.spec.InvalidParameterSpecException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigFileParserTest {

    @Test
    void parseInput() throws InvalidParameterSpecException {
        // given
        String input = "5 5\n1 1 N\nR";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        // when
        ConfigElement configElement = ConfigFileParser.parseInput(scanner);

        // then
        Scenario expectedScenario = new Scenario();
        expectedScenario.addMowersCoordinate(1,1, 'N', 0);
        expectedScenario.setLawn(new Lawn(5, 5));
        Commands expectedCommands = new Commands();
        expectedCommands.addCommands(0,"R");
        ConfigElement expectedConfigElement = new ConfigElement(expectedScenario, expectedCommands);

        assertEquals(expectedConfigElement, configElement);
    }

    @Test
    void parseInputThrowInputMismatch() {
        // given
        String input = "5 s\n1 1 N";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        // when
        assertThrows(InputMismatchException.class, () -> ConfigFileParser.parseInput(scanner));

        // then
    }
}