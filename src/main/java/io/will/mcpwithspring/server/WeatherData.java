package io.will.mcpwithspring.server;

/**
 * Record class representing weather data returned by MCP tools.
 * This provides a proper schema for Spring AI to serialize/deserialize.
 */
public record WeatherData(
    String city,
    String temperature,
    String condition,
    String humidity,
    String windSpeed,
    String description
) {
}
