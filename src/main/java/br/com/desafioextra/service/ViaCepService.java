package br.com.desafioextra.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService {

    public static ViaCepResponse buscarCep(String cep){

        String cepLimpo = cep.replaceAll("\\D","");

        if(cepLimpo.length() != 8){
            throw new IllegalArgumentException("CEP deve ter 8 números!");
        }
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + cepLimpo + "/json/"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response.body(),ViaCepResponse.class);

        }catch (IOException e){
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
