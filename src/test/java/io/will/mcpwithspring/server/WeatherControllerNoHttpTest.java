package io.will.mcpwithspring.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class WeatherControllerNoHttpTest {

    @Autowired
    private WeatherController weatherController;

    @Test
    void testGetWeather() {
        StepVerifier.create(weatherController.getWeather("London"))
            .expectNextMatches(weatherData -> 
                weatherData.city().equals("London") &&
                weatherData.temperature().equals("22°C") &&
                weatherData.condition().equals("Sunny")
            )
            .verifyComplete();
    }

    @Test
    void testGetWeatherSummary() {
        StepVerifier.create(weatherController.getWeatherSummary("Paris"))
            .expectNextMatches(summary -> 
                summary.contains("Paris") && 
                summary.contains("Sunny") &&
                summary.contains("22°C")
            )
            .verifyComplete();
    }

    @Test
    void testWeatherDataStructure() {
        StepVerifier.create(weatherController.getWeather("Tokyo"))
            .expectNextMatches(weatherData -> 
                weatherData.city() != null &&
                weatherData.temperature() != null &&
                weatherData.condition() != null &&
                weatherData.humidity() != null &&
                weatherData.windSpeed() != null &&
                weatherData.description() != null
            )
            .verifyComplete();
    }
}