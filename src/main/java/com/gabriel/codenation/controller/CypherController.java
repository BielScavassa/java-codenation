package com.gabriel.codenation.controller;

import com.gabriel.codenation.model.CypherRequestResult;
import com.gabriel.codenation.service.CypherService;
import com.gabriel.codenation.util.CreateJsonFile;
import com.gabriel.codenation.util.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

@RestController("Cypher")
@RequestMapping(path = "/cypher")
public class CypherController {

    private RestTemplate restTemplate;
    private CypherService cypherService;

    @Autowired
    public CypherController(RestTemplate restTemplate, CypherService cypherService) {
        this.restTemplate = restTemplate;
        this.cypherService = cypherService;
    }

    @GetMapping("/search")
    public ResponseEntity<CypherRequestResult> getCypher() throws IOException {
        String decipherText = "";
        CypherRequestResult cypherRequestResult = restTemplate.getForObject(Url.cypherUrl, CypherRequestResult.class);
        if (cypherRequestResult != null) {
            decipherText = cypherService.decipherText(cypherRequestResult.getKeyNumbers(), cypherRequestResult.getCypher());
            cypherRequestResult.setDecipher(decipherText);
            cypherRequestResult.setCryptographicSummary(sha1Hex(decipherText));
            CreateJsonFile.convertToJsonFile(cypherRequestResult);
        } else
            return ResponseEntity.status(HttpStatus.OK).body(new CypherRequestResult());

        return ResponseEntity.status(HttpStatus.OK).body(cypherRequestResult);
    }

}
