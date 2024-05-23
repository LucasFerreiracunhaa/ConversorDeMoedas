import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Menuconversor {
    public Conversor conversor  (String baseCurrency, String targetCurrency, Double value) {

        Gson gson = new Gson();

        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/68e54c2ec128b93103eda570/pair/" + baseCurrency +  "/"  + targetCurrency  + "/"  + value);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), Conversor.class);

        } catch (Exception  e) {
            throw new RuntimeException("Não consigo converter");
        }
    }
}

