package services;

import java.util.HashMap;
import java.util.Map;

public class AnalyzerService {
    private StringBuilder sampleText;
    private StringBuilder encryptedText;

    public AnalyzerService(StringBuilder sampleText,
                           StringBuilder encryptedText) {
        this.sampleText = sampleText;
        this.encryptedText = encryptedText;
    }

    public Map<String, Integer> analyzeSample() {
        Map<String, Integer> letterMap = new HashMap<>();
        String[] words = sampleText.toString().split(" ");

        for (String ch : words) {
            switch (ch) {
                case "в", "и", "к" -> letterMap.put(ch, letterMap.getOrDefault(ch, 0) + 1);
            }
        }

        return letterMap;
    }
}
