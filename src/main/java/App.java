import model.Position;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static int lawnXMax;
    private static int lawnYMax;
    private static BiMap<Position, Integer> mowersPositions;
    private static Map<Integer, Character> mowersOrientations;
    private static Map<Integer, String> mowersCommands;



    public static void main(String[] args) {
        // Parse input
        String filename = args[0];
        parseInput(filename);
        // Run simulation
        runSimulation();
        // Result
        for (int i = 0; i < mowersPositions.size(); i++) {
            Position position = mowersPositions.inverse().get(i);
            char orientation = mowersOrientations.get(i);
            System.out.println(String.format("%d %d %c", position.getX(), position.getY(), orientation));
        }
    }

    private static void runSimulation() {
        boolean shouldProcessNext = true;
        int movementNum = 0;
        while (shouldProcessNext) {
            boolean allSkipped = true;
            for (Integer id: mowersCommands.keySet()) {
                if (mowersCommands.get(id).length() > movementNum) {
                    allSkipped = false;
                    char command = mowersCommands.get(id).charAt(movementNum);
                    switch (command) {
                        case 'R':
                            mowersOrientations.put(id, rotate(mowersOrientations.get(id), 'R'));
                            break;
                        case 'L':
                            mowersOrientations.put(id, rotate(mowersOrientations.get(id), 'L'));
                            break;
                        case 'F':
                            moveMower(id);
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
    }

    private static void moveMower(Integer id) {
        Position position = mowersPositions.inverse().get(id);
        switch (mowersOrientations.get(id)) {
            case 'N':
                if (position.getY() + 1 <= lawnYMax) {
                    Position newPos = new Position(position.getX(), position.getY() + 1);
                    if (!mowersPositions.containsKey(newPos)) {
                        mowersPositions.remove(position);
                        mowersPositions.put(newPos, id);
                    }
                }
                break;
            case 'E':
                if (position.getX() + 1 <= lawnXMax) {
                    Position newPos = new Position(position.getX() + 1, position.getY());
                    if (!mowersPositions.containsKey(newPos)) {
                        mowersPositions.remove(position);
                        mowersPositions.put(newPos, id);
                    }
                }
                break;
            case 'S':
                if (position.getY() - 1 >= 0) {
                    Position newPos = new Position(position.getX(), position.getY() - 1);
                    if (!mowersPositions.containsKey(newPos)) {
                        mowersPositions.remove(position);
                        mowersPositions.put(newPos, id);
                    }
                }
                break;
            case 'W':
                if (position.getX() - 1 >= 0) {
                    Position newPos = new Position(position.getX() - 1, position.getY());
                    if (!mowersPositions.containsKey(newPos)) {
                        mowersPositions.remove(position);
                        mowersPositions.put(newPos, id);
                    }
                }
                break;
            default:
                throw new IllegalStateException("Orientation is invalid " + mowersOrientations.get(id));
        }
    }

    private static Character rotate(Character orientation, char rotSens) {
        switch (orientation) {
            case 'N':
                return rotSens == 'R' ? 'E' : 'W';
            case 'E':
                return rotSens == 'R' ? 'S' : 'N';
            case 'S':
                return rotSens == 'R' ? 'W' : 'E';
            case 'W':
                return rotSens == 'R' ? 'N' : 'S';
            default:
                throw new IllegalStateException("Orientation is invalid " + orientation);
        }
    }

    private static void parseInput(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            lawnXMax = scanner.nextInt();
            lawnYMax = scanner.nextInt();
            mowersPositions = HashBiMap.create();
            mowersCommands = new HashMap<>();
            mowersOrientations = new HashMap<>();
            int mowerId = 0;
            while (scanner.hasNextLine()) {
                int xPos = scanner.nextInt();
                int yPos = scanner.nextInt();
                char orientation = scanner.next().charAt(0);
                scanner.nextLine();
                mowersCommands.put(mowerId, scanner.nextLine());
                mowersOrientations.put(mowerId, orientation);
                mowersPositions.put(new Position(xPos, yPos), mowerId++);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}