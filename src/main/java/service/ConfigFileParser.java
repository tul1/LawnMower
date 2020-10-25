package service;

import model.Commands;
import model.Lawn;
import model.Scenario;

import java.util.Scanner;

public abstract class ConfigFileParser {

    private ConfigFileParser() {}

    public static ConfigElement parseInput(Scanner scanner) {
        Scenario scenario = new Scenario();
        Commands commands = new Commands();
        int lawnXMax = scanner.nextInt();
        int lawnYMax = scanner.nextInt();
        scenario.setLawn(new Lawn(lawnXMax, lawnYMax));
        int mowerId = 0;
        while (scanner.hasNextLine()) {
            int xPos = scanner.nextInt();
            int yPos = scanner.nextInt();
            char orientation = scanner.next().charAt(0);
            scanner.nextLine();
            commands.addCommands(mowerId, scanner.nextLine());
            scenario.addMowersCoordinate(xPos, yPos, orientation, mowerId++);
        }
        return new ConfigElement(scenario, commands);
    }
}
