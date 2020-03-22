package com.gabriel.codenation.controller;

import com.gabriel.codenation.model.RequestResult;
import com.gabriel.codenation.service.CypherService;
import com.gabriel.codenation.util.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<RequestResult> getCypher() {
        String decipherText = "";
        RequestResult requestResult = restTemplate.getForObject(Url.cypherUrl, RequestResult.class);
        if (requestResult != null) {
            decipherText = cypherService.decipherText(requestResult.getKeyNumbers(), requestResult.getCypher());
            requestResult.setDecipher(decipherText);
            requestResult.setCryptographicSummary(sha1Hex(decipherText));
        } else
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResult());

        return ResponseEntity.status(HttpStatus.OK).body(requestResult);
    }

}
