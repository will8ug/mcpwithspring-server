package io.will.mcpwithspring.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Tool(name = "get_weather", description = "Get current weather information for a specific city")
    public WeatherData getWeather(String city) {
        System.out.println("get_weather");
        WeatherData weatherData = new WeatherData(
            city,
            "22°C",
            "Sunny",
            "65%",
            "10 km/h",
            "Clear skies with light breeze"
        );

        System.out.println("Returning: " + weatherData);
        return weatherData;
    }

    @Tool(name = "get_weather_summary", description = "Get a human-readable weather summary for a specific city")
    public String getWeatherSummary(String city) {
        System.out.println("get_weather_summary");

        WeatherData weather = getWeather(city);
        return String.format("The weather in %s is %s with a temperature of %s. %s",
                weather.city(),
                weather.condition(),
                weather.temperature(),
                weather.description());
    }
}
