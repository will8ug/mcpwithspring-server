package io.will.mcpwithspring.server;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public Mono<Map<String, Object>> getWeather(@PathVariable String city) {
        return Mono.just(weatherService.getWeather(city));
    }

    @GetMapping("/{city}/summary")
    public Mono<String> getWeatherSummary(@PathVariable String city) {
        return Mono.just(weatherService.getWeatherSummary(city));
    }
} 