package pl.pieter.utils;

public enum Theme {
    LIGHT("Light"),
    MEDIUM("Medium"),
    DARK("Dark");

    private String path;

    Theme(String path) {
        this.path = path;
    }

    public String getPath() {
        return getClass().getResource("/pl/pieter/css/" + this.path.toLowerCase() + ".css").toExternalForm();
    }

    @Override
    public String toString() {
        return this.path;
    }
}
