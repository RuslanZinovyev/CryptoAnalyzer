package services;

import enumerators.ErrorCode;
import exceptions.ApplicationException;

import static utils.AlphabetUtil.decryptMap;
import static utils.AlphabetUtil.encryptMap;

public class DecryptionService {
    private static DecryptionService INSTANCE;
    private int securityKey;
    private final StringBuilder textBuilder;

    private DecryptionService(StringBuilder textBuilder) {
        this.textBuilder = textBuilder;
    }

    private DecryptionService(int securityKey, StringBuilder textBuilder) {
        this.securityKey = securityKey;
        this.textBuilder = textBuilder;
    }

    public static DecryptionService getInstance(StringBuilder builder) {
        if (INSTANCE == null) {
            INSTANCE = new DecryptionService(builder);
        }
        return INSTANCE;
    }

    public static DecryptionService getInstance(int securityKey, StringBuilder builder) {
        if (INSTANCE == null) {
            INSTANCE = new DecryptionService(securityKey, builder);
        }
        return INSTANCE;
    }

    public void setSecurityKey(int securityKey) {
        this.securityKey = securityKey;
    }

    public StringBuilder decryptText() {
        StringBuilder result = new StringBuilder();
        char[] chars = textBuilder.toString().toCharArray();
        for (char ch : chars) {
            result.append(decryptChar(ch));
        }
        return result;
    }

    private char decryptChar(char symbol) {
        if (symbol == '\n') {
            return '\n';
        }
        if (encryptMap.get(Character.toLowerCase(symbol)) != null) {
            if (Character.isUpperCase(symbol)) {
                int index = encryptMap.get(Character.toLowerCase(symbol));
                int shift = (index + (encryptMap.size() - securityKey)) % encryptMap.size();
                return Character.toUpperCase(decryptMap.get(shift));
            }
            int index = encryptMap.get(symbol);
            int shift = (index + (encryptMap.size() - securityKey)) % encryptMap.size();
            return decryptMap.get(shift);
        }
        throw new ApplicationException("The symbol for decryption is inappropriate", ErrorCode.DECRYPTION_ERROR);
    }
}
