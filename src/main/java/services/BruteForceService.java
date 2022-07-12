package services;

import utils.AlphabetUtil;

import java.io.File;

public class BruteForceService {
    private static BruteForceService INSTANCE;
    private final ReadService readService;

    private BruteForceService(File inputFile) {
        readService = ReadService.getInstance(inputFile);
    }

    public static BruteForceService getInstance(File inputFile) {
        if (INSTANCE == null) {
            INSTANCE = new BruteForceService(inputFile);
        }
        return INSTANCE;
    }


    public int analyzeByBruteForce() {
        StringBuilder decryptedFile = readService.readFile();
        DecryptionService decryptionService = DecryptionService.getInstance(decryptedFile);
        for (int i = 0; i < AlphabetUtil.encryptMap.size(); i++) {
            decryptionService.setSecurityKey(i);
            StringBuilder decryptedText = decryptionService.decryptText();
            if (doesNotContainLongWords(decryptedText) && !doesNotHavePreposition(decryptedText) && !doesNotHaveEnoughSpaces(decryptedText)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Validators
     */

    private boolean doesNotContainLongWords(StringBuilder decryptedText) {
        String[] array = decryptedText.toString().split(" ");
        for (String s : array) {
            if (s.length() > 30) {
                return false;
            }
        }
        return true;
    }

    private boolean doesNotHavePreposition(StringBuilder decryptedText) {
        String[] array = decryptedText.toString().split(" ");
        int count = 0;
        for (String s : array) {
            if (s.length() == 1) {
                count++;
            }
        }
        return count == 0;
    }

    private boolean doesNotHaveEnoughSpaces(StringBuilder decryptedText) {
        String[] array = decryptedText.toString().split("\n");
        int count = 0;
        for (String s : array) {
            if (s.contains(" ")) {
                count++;
            }
        }
        return count < array.length;
    }
}
