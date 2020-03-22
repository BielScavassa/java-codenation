package com.gabriel.codenation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResult {

    @JsonProperty("numero_casas")
    private Integer keyNumbers;
    @JsonProperty("token")
    private String token;
    @JsonProperty("cifrado")
    private String cypher;
    @JsonProperty("decifrado")
    private String decipher;
    @JsonProperty("resumo_criptografico")
    private String cryptographicSummary;
}
