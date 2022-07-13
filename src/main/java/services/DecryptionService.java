package services;

import enumerators.ErrorCode;
import exceptions.ApplicationException;

import static utils.AlphabetUtil.decryptMap;
import static utils.AlphabetUtil.encryptMap;

public class DecryptionService {
    private int securityKey;
    private final StringBuilder textBuilder;

    public DecryptionService(StringBuilder textBuilder) {
        this.textBuilder = textBuilder;
    }

    public DecryptionService(int securityKey, StringBuilder textBuilder) {
        this.securityKey = securityKey;
        this.textBuilder = textBuilder;
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
