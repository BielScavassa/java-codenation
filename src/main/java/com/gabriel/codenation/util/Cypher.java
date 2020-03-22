package com.gabriel.codenation.util;

public class Cypher {

    static final public String cypherText(int key, String text) {
        String cipherText = "";
        char alphabet;
        for (int i = 0; i < text.length(); i++) {
            alphabet = text.charAt(i);
            if (alphabet >= 'a' && alphabet <= 'z') {
                alphabet = (char) (alphabet + key);
                if (alphabet > 'z') {
                    alphabet = (char) (alphabet + 'a' - 'z' - 1);
                }
                cipherText = cipherText + alphabet;
            } else if (alphabet >= 'A' && alphabet <= 'Z') {
                alphabet = (char) (alphabet + key);
                if (alphabet > 'Z') {
                    alphabet = (char) (alphabet + 'A' - 'Z' - 1);
                }
                cipherText = cipherText + alphabet;
            } else
                cipherText = cipherText + alphabet;
        }
        return cipherText;
    }
}
