package service;

import model.Commands;
import model.Scenario;

import java.util.Objects;

public class ConfigElement {
    private final Scenario scenario;
    private final Commands commands;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigElement that = (ConfigElement) o;
        return Objects.equals(scenario, that.scenario) &&
                Objects.equals(commands, that.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scenario, commands);
    }

    @Override
    public String toString() {
        return "ConfigElement{" +
                "scenario=" + scenario +
                ", commands=" + commands +
                '}';
    }
}
