package service;

import model.Commands;
import model.Scenario;

public class Simulation {
    private Scenario scenario;
    private Commands commands;

    public Simulation(Scenario scenario, Commands commands) {
        this.scenario = new Scenario(scenario);
        this.commands = new Commands(commands);
    }

    public Scenario run() {
        boolean shouldProcessNext = true;
        int movementNum = 0;
        while (shouldProcessNext) {
            boolean allSkipped = true;
            for (Integer id: commands.getCommands().keySet()) {
                if (commands.getCommands().get(id).size() > movementNum) {
                    allSkipped = false;
                    Commands.Command command = commands.getCommands().get(id).get(movementNum);
                    switch (command) {
                        case R:
                            rotateMower(id, Commands.Command.R);
                            break;
                        case L:
                            rotateMower(id, Commands.Command.L);
                            break;
                        case F:
                            moveMowerForward(id);
                            break;
                        default:
                            throw new IllegalStateException("Command is invalid " + command);
                    }
                }
            }
            movementNum++;
            if (allSkipped)
                shouldProcessNext = false;
        }
        return scenario;
    }

    private void moveMowerForward(Integer id) {
        scenario.moveMowerForward(id);
    }

    private void rotateMower(Integer id, Commands.Command r) {
        scenario.rotateMower(id, r);
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "scenario=" + scenario +
                ", commands=" + commands +
                '}';
    }
}
