package services;

import enumerators.ErrorCode;
import exceptions.ApplicationException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteService {
    private static WriteService INSTANCE;
    private static File outputFile;
    private static StringBuilder builder;

    private WriteService(File outputFile, StringBuilder builder) {
        this.outputFile = outputFile;
        this.builder = builder;
    }

    public static WriteService getInstance(File outputFile, StringBuilder builder) {
        if (INSTANCE == null) {
            INSTANCE = new WriteService(outputFile, builder);
        }
        return INSTANCE;
    }

    public File getOutputFile() {
        return this.outputFile;
    }

    public void writeFile() {
        if (!getFileExtension(outputFile).equals(".txt")) {
            throw new ApplicationException("Please specify the text file with .txt extension", ErrorCode.INVALID_FILE_EXTENSION);
        }
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)
        ) {
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        return name.substring(lastIndexOf);
    }
}
