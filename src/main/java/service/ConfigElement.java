package service;

import model.Commands;
import model.Scenario;

/**
 * ConfigElement class
 */
public class ConfigElement {
    private final Scenario scenario;
    private final Commands commands;

    /**
     * Constructor.
     *
     * @param scenario Scenario instance.
     * @param commands Commands instance.
     */
    public ConfigElement(Scenario scenario, Commands commands) {
        this.commands = commands;
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public Commands getCommands() {
        return commands;
    }

    @Override
    public String toString() {
        return "ConfigElement{" +
                "scenario=" + scenario +
                ", commands=" + commands +
                '}';
    }
}