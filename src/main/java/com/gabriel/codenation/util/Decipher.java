package com.gabriel.codenation.util;

public class Decipher {

    static final public String decipherText(Integer key, String cypherText) {
        String decryptMessage = "";

        for (int i = 0; i < cypherText.length(); i++) {
            char alphabet = cypherText.charAt(i);
            if (alphabet >= 'a' && alphabet <= 'z') {
                alphabet = (char) (alphabet - key);
                if (alphabet < 'a') {
                    alphabet = (char) (alphabet - 'a' + 'z' + 1);
                }
                decryptMessage = decryptMessage + alphabet;
            } else if (alphabet >= 'A' && alphabet <= 'Z') {
                alphabet = (char) (alphabet - key);
                if (alphabet < 'A') {
                    alphabet = (char) (alphabet - 'A' + 'Z' + 1);
                }
                decryptMessage = decryptMessage + alphabet;
            } else {
                decryptMessage = decryptMessage + alphabet;
            }
        }
        return decryptMessage;
    }
}
