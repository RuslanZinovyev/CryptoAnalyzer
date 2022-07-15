package services;

import utils.AlphabetUtil;

import java.io.File;
import java.util.*;

public class AnalyzerService {
    private final StringBuilder encryptedText;
    private final HashMap<Character, Integer> letterMap = new HashMap<>();
    private final File outputFile;

    public AnalyzerService(StringBuilder encryptedText, File outputFile) {
        this.encryptedText = encryptedText;
        this.outputFile = outputFile;
    }

    public void analyzeEncryptedText() {
        int shift;
        // index of space is 33
        if (getMostFrequentSymbol() != null) {
            char c = getMostFrequentSymbol();

            int index = AlphabetUtil.encryptMap.get(c);
            if (index > 33) {
                shift = index % 33;
            } else {
                shift = 33 - index;
            }
            DecryptionService decryptionService = new DecryptionService(shift, encryptedText);
            WriteService writeService = new WriteService(outputFile, decryptionService.decryptText());
            writeService.writeFile();
        }
    }

    private Character getMostFrequentSymbol() {
        int maxValue= getMaxValue();
        for (Map.Entry<Character, Integer> entry : letterMap.entrySet()) {
            if (entry.getValue() == maxValue) {
                return entry.getKey();
            }
        }
        return null;
    }

    private int getMaxValue() {
        char[] chars = encryptedText.toString().toCharArray();
        int maxValue;

        for (char ch : chars) {
            letterMap.put(ch, letterMap.getOrDefault(ch, 0) + 1);
        }

        List<Integer> values = new ArrayList<>(letterMap.values());
        Collections.sort(values);
        maxValue = values.get(values.size() - 1);

        return maxValue;
    }
}
