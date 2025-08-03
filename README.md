# MCP Weather Server with Spring WebFlux and Spring AI

A simple Model Context Protocol (MCP) server built with Spring WebFlux and Spring AI that provides weather information through a mocked weather API.

## Features

- **Weather Service**: Mock weather data service that returns temperature, condition, humidity, and wind speed
- **Spring AI MCP Tools**: Two MCP tools available using Spring AI tool annotations:
  - `get_weather`: Returns detailed weather data for a city
  - `get_weather_summary`: Returns a human-readable weather summary for a city
- **REST API**: Standard REST endpoints for direct weather queries
- **Reactive**: Built with Spring WebFlux for non-blocking, reactive programming
- **Spring AI Integration**: Uses Spring AI's MCP server WebFlux starter for seamless MCP integration

## Dependencies

- Spring Boot 3.5.4
- Spring WebFlux
- Spring AI MCP Server WebFlux Starter
- Spring AI Model (for tool annotations)
- MCP SDK 0.10.0
- Reactor Test (for testing)

## Running the Application

1. **Start the server**:
   ```bash
   ./mvnw spring-boot:run
   ```

2. **Test the REST API**:
   ```bash
   # Get weather data for a city
   curl http://localhost:8080/api/weather/London
   
   # Get weather summary for a city
   curl http://localhost:8080/api/weather/London/summary
   ```

## MCP Tools

The application uses Spring AI's `@Tool` annotation to automatically register MCP tools:

### get_weather
Returns detailed weather information for a specified city.

**Parameters:**
- `city` (string, required): The name of the city

**Example Response:**
```json
{
  "city": "London",
  "temperature": "22°C",
  "condition": "Sunny",
  "humidity": "65%",
  "windSpeed": "10 km/h",
  "description": "Clear skies with light breeze"
}
```

### get_weather_summary
Returns a human-readable weather summary for a specified city.

**Parameters:**
- `city` (string, required): The name of the city

**Example Response:**
```
The weather in London is Sunny with a temperature of 22°C. Clear skies with light breeze.
```

## Architecture

- **WeatherService**: Core service with Spring AI `@Tool` annotations for MCP integration
- **WeatherController**: REST controller for direct API access
- **Spring AI MCP Server**: Automatically configured MCP server using Spring AI's WebFlux starter

## Code Structure

```java
@Service
public class WeatherService {

    @Tool(name = "get_weather", description = "Get current weather information for a specific city")
    public Mono<Map<String, Object>> getWeather(String city) {
        // Mock weather data implementation
    }

    @Tool(name = "get_weather_summary", description = "Get a human-readable weather summary for a specific city")
    public Mono<String> getWeatherSummary(String city) {
        // Weather summary implementation
    }
}
```

## Testing

Run the tests with:
```bash
./mvnw test
```

The test suite includes:
- Weather service functionality tests
- Weather data structure validation
- Weather summary generation tests

## MCP Integration

The Spring AI MCP server WebFlux starter automatically:
- Discovers `@Tool` annotated methods
- Registers them as MCP tools
- Provides MCP protocol endpoints
- Handles tool execution and response formatting

This approach leverages Spring AI's built-in MCP capabilities for a clean, maintainable implementation. 