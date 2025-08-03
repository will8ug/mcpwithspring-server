package io.will.mcpwithspring.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class WeatherMcpServerTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    void testGetWeather() {
        StepVerifier.create(weatherService.getWeather("London"))
            .expectNextMatches(data -> 
                data.get("city").equals("London") &&
                data.get("temperature").equals("22°C") &&
                data.get("condition").equals("Sunny")
            )
            .verifyComplete();
    }

    @Test
    void testGetWeatherSummary() {
        StepVerifier.create(weatherService.getWeatherSummary("Paris"))
            .expectNextMatches(summary -> 
                summary.contains("Paris") && 
                summary.contains("Sunny") &&
                summary.contains("22°C")
            )
            .verifyComplete();
    }

    @Test
    void testWeatherDataStructure() {
        StepVerifier.create(weatherService.getWeather("Tokyo"))
            .expectNextMatches(data -> 
                data.containsKey("city") &&
                data.containsKey("temperature") &&
                data.containsKey("condition") &&
                data.containsKey("humidity") &&
                data.containsKey("windSpeed") &&
                data.containsKey("description")
            )
            .verifyComplete();
    }
} 