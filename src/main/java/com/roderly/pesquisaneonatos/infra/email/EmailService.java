package com.roderly.pesquisaneonatos.infra.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String apiKey;

    @Value("${resend.from}")
    private String from;

    private final RestTemplate restTemplate;

    public EmailService() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().stream()
                .filter(c -> c instanceof StringHttpMessageConverter)
                .forEach(c -> ((StringHttpMessageConverter) c).setDefaultCharset(StandardCharsets.UTF_8));
    }

    public void enviarHtml(String para, String assunto, String corpoHtml) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Map<String, Object> body = Map.of(
                "from", from,
                "to", List.of(para),
                "subject", assunto,
                "html", corpoHtml
        );

        try {
            restTemplate.postForEntity(
                    "https://api.resend.com/emails",
                    new HttpEntity<>(body, headers),
                    String.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail para " + para + ": " + e.getMessage(), e);
        }
    }
}
