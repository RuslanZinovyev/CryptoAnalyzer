package app;

import commands.AnalyzeCommand;
import commands.BruteForceCommand;
import commands.DecryptCommand;
import commands.EncryptCommand;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Parameter;
import model.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainApplication extends Application {

    public static final String CRYPTO_ANALYZER = "Crypto Analyzer";
    private static final String DOWNLOAD_FILE = "Download File";
    private static final String UPLOAD_FILE = "Upload File";
    private static final String ENCRYPT = "Encrypt";
    private static final String DECRYPT = "Decrypt";
    private static final String BRUTE_FORCE_ANALYZER = "Brute Force key analyzer";
    private static final String ANALYZE = "Analyze";
    private static final String APPLY = "Apply";
    private static final String CLEAR = "Clear";
    private static final String OPERATIONS = "Operations";
    private int secureKey = 0;
    private File inputFile;
    private File outputFile;

    @Override
    public void start(Stage stage) {
        stage.setTitle(CRYPTO_ANALYZER);

        MenuItem download = new MenuItem(DOWNLOAD_FILE);
        MenuItem upload = new MenuItem(UPLOAD_FILE);

        Button encrypt = new Button(ENCRYPT);
        Button decrypt = new Button(DECRYPT);
        Button bruteForce = new Button(BRUTE_FORCE_ANALYZER);
        Button analyze = new Button(ANALYZE);
        Button key = new Button(APPLY);
        Button clear = new Button(CLEAR);

        FileChooser fileChooser = new FileChooser();

        MenuButton menuButton = new MenuButton(OPERATIONS, null, download, upload);
        TextArea textArea = new TextArea();
        TextField textField = new TextField();

        VBox vBox = new VBox(20, menuButton, encrypt, decrypt, bruteForce, analyze, textField, key, clear);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox, textArea);
        HBox.setMargin(vBox, new Insets(10, 10, 10, 10));

        Scene scene = new Scene(hBox, 600, 800);
        stage.setScene(scene);
        stage.show();

        /*
         Button actions
         */
        key.setOnAction(e -> secureKey = Integer.parseInt(textField.getText()));

        clear.setOnAction(e -> {
            textArea.clear();
            textField.clear();
            secureKey = 0;
        });

        download.setOnAction(event -> {
            textArea.clear();
            inputFile = fileChooser.showOpenDialog(stage);
            try (Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNext()) {
                    textArea.appendText(scanner.nextLine());
                    textArea.appendText("\n");
                    textArea.setWrapText(true);
                }
            } catch (FileNotFoundException exc) {
                exc.printStackTrace();
            }
        });

        upload.setOnAction( event -> {
            // Set extension filter for text files
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extensionFilter);
            // Show save file dialog
            outputFile = fileChooser.showSaveDialog(stage);
        });

        encrypt.setOnAction(event -> {
            Parameter parameter = new Parameter(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), secureKey);
            EncryptCommand encryptCommand = new EncryptCommand(parameter);
            encryptCommand.execute();
            textArea.clear();
            if (outputFile.exists()) {
                try (Scanner scanner = new Scanner(outputFile)) {
                    while (scanner.hasNext()) {
                        textArea.appendText(scanner.nextLine());
                        textArea.appendText("\n");
                        textArea.setWrapText(true);
                    }
                } catch (FileNotFoundException exc) {
                    exc.printStackTrace();
                }
            }
        });

        decrypt.setOnAction(event -> {
            Parameter parameter = new Parameter(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), secureKey);
            DecryptCommand decryptCommand = new DecryptCommand(parameter);
            decryptCommand.execute();
            textArea.clear();
            if (outputFile.exists()) {
                try (Scanner scanner = new Scanner(outputFile)) {
                    while (scanner.hasNext()) {
                        textArea.appendText(scanner.nextLine());
                        textArea.appendText("\n");
                        textArea.setWrapText(true);
                    }
                } catch (FileNotFoundException exc) {
                    exc.printStackTrace();
                }
            }
        });

        bruteForce.setOnAction(event -> {
            Parameter parameter = new Parameter(inputFile.getAbsolutePath());
            BruteForceCommand bruteForceCommand = new BruteForceCommand(parameter);
            Result result = bruteForceCommand.execute();
            if (result != null) {
                textField.appendText(String.valueOf(result.getSecurityKey()));
            }
        });

        analyze.setOnAction(event -> {
            Parameter parameter = new Parameter(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
            AnalyzeCommand analyzeCommand = new AnalyzeCommand(parameter);
            Result result = analyzeCommand.execute();
            textArea.clear();
            textArea.appendText(result.getMessage());
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
