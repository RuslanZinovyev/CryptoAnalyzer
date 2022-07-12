package services;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadService {
    private final File inputFile;
    private final StringBuilder stringBuilder;

    public ReadService(File inputFile) {
        this.inputFile = inputFile;
        stringBuilder = new StringBuilder();
    }

    public StringBuilder readFile() {
        try (FileInputStream fis = new FileInputStream(inputFile);
             InputStreamReader isw = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isw)
        ) {
            while (reader.ready()) {
                String line = reader.readLine();
                for (char ch : line.toCharArray()) {
                    stringBuilder.append(ch);
                }
                stringBuilder.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
