package model;

import java.io.File;

public class Parameter {
    private File input;
    private File output;
    private int securityKey;

    public Parameter(String input) {
        this.input = new File(input);
    }

    public Parameter(String input, String output) {
        this.input = new File(input);
        this.output = new File(output);
    }

    public Parameter(String input, String output, int securityKey) {
        this.input = new File(input);
        this.output = new File(output);
        this.securityKey = securityKey;
    }

    public File getInput() {
        return input;
    }

    public File getOutput() {
        return output;
    }

    public int getSecurityKey() {
        return securityKey;
    }
}
