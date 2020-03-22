package com.gabriel.codenation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.codenation.model.CypherRequestResult;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

@Service
public class CypherService {

    public CypherRequestResult addDecipher(CypherRequestResult cypherRequestResult) {
        String decipherText = decipherText(cypherRequestResult.getKeyNumbers(), cypherRequestResult.getCypher());
        cypherRequestResult.setDecipher(decipherText);
        cypherRequestResult.setCryptographicSummary(sha1Hex(decipherText));
        return cypherRequestResult;
    }

    public void convertToJsonFile(CypherRequestResult cypherRequestResult) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/answer.json");
        mapper.writeValue(file, cypherRequestResult);
    }

    private String decipherText(Integer key, String cypherText) {
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

    public String cypherText(int key, String text) {
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
