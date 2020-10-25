package service;

public class AppArguments {
    private final String filename;

    public AppArguments(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return "AppArguments{" +
                "filename='" + filename + '\'' +
                '}';
    }
}
