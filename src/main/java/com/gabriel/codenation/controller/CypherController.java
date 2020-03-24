package com.gabriel.codenation.controller;

import com.gabriel.codenation.model.CypherRequestResult;
import com.gabriel.codenation.service.CypherService;
import com.gabriel.codenation.util.Url;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

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
        CypherRequestResult cypherRequestResult = restTemplate.getForObject(Url.getCypherUrl, CypherRequestResult.class);
        if (cypherRequestResult != null) {
            cypherRequestResult = cypherService.addDecipher(cypherRequestResult);
            cypherService.convertToJsonFile(cypherRequestResult);
            HttpResponse httpResponse = postFile(cypherRequestResult);

            System.out.println("Status Code: " + httpResponse.getStatusLine().getStatusCode() + "\n"
                    + "Points archived: " + httpResponse.getEntity().getContent().read());

        } else
            return ResponseEntity.status(HttpStatus.OK).body(new CypherRequestResult());

        return ResponseEntity.status(HttpStatus.OK).body(cypherRequestResult);
    }

    private HttpResponse postFile(CypherRequestResult cypherRequestResult) throws IOException {
        //Http methods & request
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Url.postCypherUrl);
        var requestEntity = new MultipartEntity();
        //Generate Files
        File answerFile = new File(getClass().getClassLoader().getResource("answer.json").getFile());
        FileBody uploadAnswerFile = new FileBody(answerFile);

        requestEntity.addPart("answer", uploadAnswerFile);
        httpPost.setEntity(requestEntity);
        HttpResponse httpResponse = httpclient.execute(httpPost);
        return httpResponse;
    }

}
