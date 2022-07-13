package services;

import static utils.AlphabetUtil.decryptMap;
import static utils.AlphabetUtil.encryptMap;

public class EncryptionService {
    private final int securityKey;
    private final StringBuilder textBuilder;

    public EncryptionService(int securityKey, StringBuilder textBuilder) {
        this.securityKey = securityKey;
        this.textBuilder = textBuilder;
    }

    public StringBuilder encryptText() {
        StringBuilder result = new StringBuilder();
        char[] chars = textBuilder.toString().toCharArray();
        for (char ch : chars) {
            result.append(encryptChar(ch));
        }

        return result;
    }

    private char encryptChar(char symbol) {
        if (symbol == '\n') {
            return '\n';
        }
        if (encryptMap.get(Character.toLowerCase(symbol)) != null) {
            if (Character.isUpperCase(symbol)) {
                int index = encryptMap.get(Character.toLowerCase(symbol));
                int shift = (index + securityKey) % encryptMap.size();
                return Character.toUpperCase(decryptMap.get(shift));
            }
            int index = encryptMap.get(symbol);
            int shift = (index + securityKey) % encryptMap.size();
            return decryptMap.get(shift);
        }
        return symbol;
    }
}
