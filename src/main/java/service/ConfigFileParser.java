package service;

import model.Commands;
import model.Lawn;
import model.Scenario;

import java.security.spec.InvalidParameterSpecException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class ConfigFileParser {

    private ConfigFileParser() {}

    public static ConfigElement parseInput(Scanner scanner) throws InvalidParameterSpecException {
        Scenario scenario = new Scenario();
        Commands commands = new Commands();
        int lawnXMax = scanner.nextInt();
        int lawnYMax = scanner.nextInt();
        scenario.setLawn(new Lawn(lawnXMax, lawnYMax));
        int mowerId = 0;
        boolean singleMower = false;
        while (scanner.hasNextLine()) {
            if (singleMower) {
                throw new InvalidParameterSpecException();
            }
            int xPos = scanner.nextInt();
            int yPos = scanner.nextInt();
            char orientation = scanner.next().charAt(0);
            try {
                scanner.nextLine();
                commands.addCommands(mowerId, scanner.nextLine());
            } catch (NoSuchElementException e) {
                singleMower = true;
            }
            scenario.addMowersCoordinate(xPos, yPos, orientation, mowerId++);
        }
        return new ConfigElement(scenario, commands);
    }
}
