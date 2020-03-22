package com.gabriel.codenation.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.codenation.model.CypherRequestResult;

import java.io.File;
import java.io.IOException;

public class CreateJsonFile {

    static final public void convertToJsonFile(CypherRequestResult cypherRequestResult) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/answer.json");
        mapper.writeValue(file, cypherRequestResult);
    }
}
