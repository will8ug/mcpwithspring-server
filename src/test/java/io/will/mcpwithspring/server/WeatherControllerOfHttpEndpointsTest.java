package io.will.mcpwithspring.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class WeatherControllerOfHttpEndpointsTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetWeather() {
        webTestClient.get()
            .uri("/api/weather/London")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.city").isEqualTo("London")
            .jsonPath("$.temperature").isEqualTo("22°C")
            .jsonPath("$.condition").isEqualTo("Sunny");
    }

    @Test
    void testGetWeatherSummary() {
        webTestClient.get()
            .uri("/api/weather/Paris/summary")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .isEqualTo("The weather in Paris is Sunny with a temperature of 22°C. Clear skies with light breeze");
    }

    @Test
    void testWeatherDataStructure() {
        webTestClient.get()
            .uri("/api/weather/Tokyo")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.city").exists()
            .jsonPath("$.temperature").exists()
            .jsonPath("$.condition").exists()
            .jsonPath("$.humidity").exists()
            .jsonPath("$.windSpeed").exists()
            .jsonPath("$.description").exists();
    }
} 