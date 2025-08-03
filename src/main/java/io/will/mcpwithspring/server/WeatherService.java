package io.will.mcpwithspring.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Map;
import java.util.HashMap;

@Service
public class WeatherService {

    @Tool(name = "get_weather", description = "Get current weather information for a specific city")
    public Mono<Map<String, Object>> getWeather(String city) {
        System.out.println("get_weather");
        // Mock weather data
        Map<String, Object> weatherData = new HashMap<>();
        weatherData.put("city", city);
        weatherData.put("temperature", "22°C");
        weatherData.put("condition", "Sunny");
        weatherData.put("humidity", "65%");
        weatherData.put("windSpeed", "10 km/h");
        weatherData.put("description", "Clear skies with light breeze");

        System.out.println(weatherData);
        return Mono.just(weatherData);
    }

    @Tool(name = "get_weather_plan_string", description = "Get a string")
    public String getWeatherPlanString(String city) {
        System.out.println("get_weather_plan_string");
        // Mock weather data
        Map<String, Object> weatherData = new HashMap<>();
        weatherData.put("city", city);
        weatherData.put("temperature", "22°C");
        weatherData.put("condition", "Sunny");
        weatherData.put("humidity", "65%");
        weatherData.put("windSpeed", "10 km/h");
        weatherData.put("description", "Clear skies with light breeze");

        System.out.println(weatherData);
        return weatherData.toString();
    }

    @Tool(name = "get_weather_summary", description = "Get a human-readable weather summary for a specific city")
    public Mono<String> getWeatherSummary(String city) {
        return getWeather(city)
            .map(data -> String.format("The weather in %s is %s with a temperature of %s. %s", 
                data.get("city"), 
                data.get("condition"), 
                data.get("temperature"), 
                data.get("description")));
    }
}
