package model;

import java.io.PrintStream;

public abstract class ScenarioPrinter {

    private ScenarioPrinter() {
    }

    public static void print(Scenario scenario, PrintStream stream) {
        for (int i = 0; i < scenario.getMowersPositions().size(); i++) {
            Position position = scenario.getMowersPositions().inverse().get(i);
            Orientation orientation = scenario.getMowersOrientations().get(i);
            String output = String.format("%d %d %s",
                    position.getX(), position.getY(), orientation.getDirection());
            stream.println(output);
        }
    }
}
