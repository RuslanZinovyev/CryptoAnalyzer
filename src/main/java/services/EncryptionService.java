package services;

import static utils.AlphabetUtil.decryptMap;
import static utils.AlphabetUtil.encryptMap;

public class EncryptionService {
    private static EncryptionService INSTANCE;
    private final int securityKey;
    private final StringBuilder textBuilder;

    private EncryptionService(int securityKey, StringBuilder textBuilder) {
        this.securityKey = securityKey;
        this.textBuilder = textBuilder;
    }

    public static EncryptionService getInstance(int securityKey, StringBuilder builder) {
        if (INSTANCE == null) {
            INSTANCE = new EncryptionService(securityKey, builder);
        }
        return INSTANCE;
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
