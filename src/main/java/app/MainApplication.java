package app;

import commands.BruteForceCommand;
import commands.DecryptorCommand;
import commands.EncryptorCommand;
import controllers.CaesarController;
import model.Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApplication {
    private final static String HEADER = "Hello, this is the Caesar Cipher application \nPlease choose the operation: \n\n";
    private final static String TEXT_ENCRYPTION_WITH_KEY = "1: Encrypt file with the key \n";
    private final static String TEXT_DECRYPTION_WITH_KEY = "2: Decrypt file with the key \n";
    private final static String TEXT_DECRYPTION_BRUTE_FORCE = "3: Brute force analyzer \n";
    private final static String TEXT_DECRYPTION_STATIC_CODE_ANALYZER = "4: Static key analyzer \n";
    private final static String EXIT = "5: Exit application";

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CaesarController controller = new CaesarController();
        System.out.println(
                HEADER +
                        TEXT_ENCRYPTION_WITH_KEY +
                        TEXT_DECRYPTION_WITH_KEY +
                        TEXT_DECRYPTION_BRUTE_FORCE +
                        TEXT_DECRYPTION_STATIC_CODE_ANALYZER +
                        EXIT
        );

        try {
            String entry = reader.readLine();
            int key;
            switch (entry) {
                case "1", "2" -> {
                    System.out.println("Please specify the path and name for the source file");
                    String inputPath = reader.readLine();
                    System.out.println("Please specify where to save the result file");
                    String outputPath = reader.readLine();
                    System.out.println("Please specify the security key");
                    key = Integer.parseInt(reader.readLine());
                    Parameter parameter = new Parameter(inputPath, outputPath, key);

                    switch (entry) {
                        case "1" -> {
                            EncryptorCommand encryptorCommand = new EncryptorCommand(parameter);
                            controller.runCommand(encryptorCommand);
                        }
                        case "2" -> {
                            DecryptorCommand decryptorCommand = new DecryptorCommand(parameter);
                            controller.runCommand(decryptorCommand);
                        }
                    }
                }
                case "3" -> {
                    System.out.println("Please specify the path to decrypted text");
                    String inputPath = reader.readLine();
                    Parameter parameter = new Parameter(inputPath);
                    BruteForceCommand bruteForceCommand = new BruteForceCommand(parameter);
                    int securityKey = controller.runCommand(bruteForceCommand).getSecurityKey();
                    System.out.println(securityKey);
                }
                case "4" -> {
                }
                case "5" -> System.exit(0);
                default -> System.out.println("Please specify correct number");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
