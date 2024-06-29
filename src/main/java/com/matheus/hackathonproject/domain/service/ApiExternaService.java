package com.matheus.hackathonproject.domain.service;

import com.matheus.hackathonproject.api.assembler.transaction.TransactionExternModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiExternaService {

    private final WebClient webClient;

    public ApiExternaService(WebClient.Builder webClientBuilder) { // Injeção do WebClient
        this.webClient = webClientBuilder.baseUrl("https://hackathon.marjosports.com.br").build();
    }

    public void enviarDadosParaApiExterna(TransactionExternModel externModel) {
        webClient.post()
            .uri("/hackathon")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(externModel)
            .retrieve()
            .bodyToMono(String.class) // Ou o tipo de resposta da API
            .subscribe(response -> {
                log.info("Resposta da API externa: {}", response);
            }, error -> {
                log.error("Erro ao chamar API externa: {}", error.getMessage());
            });
    }
}
