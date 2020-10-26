import model.Commands;
import model.Scenario;
import service.*;
import model.ScenarioPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException, InvalidParameterSpecException {
        // Parse arguments
        AppArguments arguments = ArgumentsParser.parseArguments(args);
        // Parse input
        Scanner scanner = new Scanner(new File(arguments.getFilename()));
        ConfigElement confElement = ConfigFileParser.parseInput(scanner);
        Scenario scenario = confElement.getScenario();
        Commands commands = confElement.getCommands();
        // Run simulation
        Simulation simulation = new Simulation(scenario, commands);
        Scenario finalScenario = simulation.run();
        // Print result
        ScenarioPrinter.print(finalScenario, System.out);
    }
}