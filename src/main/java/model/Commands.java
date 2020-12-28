package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Commands {

    public enum Command {R,L,F}

    private Map<Integer, List<Command>> commands;

    public Commands() {
        commands = new HashMap<>();
    }

    public Commands(Commands commands) {
        this.commands = new HashMap<>(commands.getCommands());
    }

    public Map<Integer, List<Command>> getCommands() {
        return commands;
    }

    public void addCommands(int mowerId, String mowerCommandsStr) {
        List<Command> mowerCommands = new ArrayList<>();
        for (int i = 0; i < mowerCommandsStr.length(); i++) {
                switch (mowerCommandsStr.charAt(i)) {
                case 'R':
                    mowerCommands.add(Command.R);
                    break;
                case 'L':
                    mowerCommands.add(Command.L);
                    break;
                case 'F':
                    mowerCommands.add(Command.F);
                    break;
                default:
                    throw new IllegalStateException("Command is invalid " + mowerCommandsStr.charAt(i));
            }
        }
        commands.put(mowerId, mowerCommands);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commands commands1 = (Commands) o;
        return Objects.equals(commands, commands1.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commands);
    }

    @Override
    public String toString() {
        return "Commands{" +
                "commands=" + commands +
                '}';
    }
}
