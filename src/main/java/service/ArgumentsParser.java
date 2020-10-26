package service;

public abstract class ArgumentsParser {

    private static final int APP_ARGUMENTS_NUM = 1;

    private ArgumentsParser() {
    }

    public static AppArguments parseArguments(String[] args) {
        if ( args.length != APP_ARGUMENTS_NUM) {
            throw new IllegalArgumentException(String.format(
                    "Invalid number of arguments, maximum is %d and minimum is %d", APP_ARGUMENTS_NUM));
        }
        return new AppArguments(args[0]);
    }

}
