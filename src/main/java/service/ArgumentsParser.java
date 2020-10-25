package service;

public abstract class ArgumentsParser {

    private ArgumentsParser() {
    }

    public static AppArguments parseArguments(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }
        return new AppArguments(args[0]);
    }

}
