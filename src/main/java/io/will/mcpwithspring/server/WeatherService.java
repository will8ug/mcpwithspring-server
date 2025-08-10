package io.will.mcpwithspring.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    @Tool(name = "get_weather", description = "Get current weather information for a specific city")
    public Map<String, Object> getWeather(String city) {
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
        return weatherData;
    }

    @Tool(name = "get_weather_summary", description = "Get a human-readable weather summary for a specific city")
    public String getWeatherSummary(String city) {
        System.out.println("get_weather_summary");

        Map<String, Object> weather = getWeather(city);
        return String.format("The weather in %s is %s with a temperature of %s. %s",
                weather.get("city"),
                weather.get("condition"),
                weather.get("temperature"),
                weather.get("description"));
    }
}
