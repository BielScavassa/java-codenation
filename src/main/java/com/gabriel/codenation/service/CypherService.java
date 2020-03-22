package com.gabriel.codenation.service;

import com.gabriel.codenation.util.Cypher;
import com.gabriel.codenation.util.Decipher;
import org.springframework.stereotype.Service;

@Service
public class CypherService {

    public String decipherText(Integer key, String cypherText) {
        return Decipher.decipherText(key, cypherText);
    }

    public String cypherText(Integer key, String cypherText) {
        return Cypher.cypherText(key, cypherText);
    }
}
