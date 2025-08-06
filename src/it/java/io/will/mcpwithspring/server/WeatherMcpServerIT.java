package io.will.mcpwithspring.server;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.InitializeResult;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Start this spring-boot application before running this test.
 * - ./mvnw spring-boot:run
 */
public class WeatherMcpServerIT {
    @Test
    @Timeout(30)
    public void test() {
        var httpClient = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(10))
            .option(io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(io.netty.channel.ChannelOption.SO_TIMEOUT, 10000);
        
        var builder = WebClient.builder().baseUrl("http://localhost:8080");
//            .clientConnector(new ReactorClientHttpConnector(httpClient));
        var transport = new WebFluxSseClientTransport(builder);

        try (var client = McpClient.sync(transport).requestTimeout(Duration.ofSeconds(15L)).build()) {
            InitializeResult initializeResult = client.initialize();
            assertEquals("mcp-server-poc", initializeResult.serverInfo().name());
            assertEquals("0.1.0", initializeResult.serverInfo().version());
            assertNotNull(client.ping());

            System.out.println();
            ListToolsResult toolsList = client.listTools();
            System.out.println(toolsList);
            assertEquals(3, toolsList.tools().size());

            CallToolResult result = client.callTool(new CallToolRequest("get_weather",
                    Map.of("city", "Changsha")));
            System.out.println(result);

            StringBuilder resultString = new StringBuilder();
            result.content().forEach(content -> {
                if (content instanceof TextContent) {
                    resultString.append(((TextContent) content).text());
                }
            });
            System.out.println(resultString);
            // CallToolResult[content=[TextContent[audience=null, priority=null, text={"scanAvailable":true}]], isError=false]
            // CallToolResult[content=[TextContent[audience=null, priority=null, text="{condition=Sunny, city=Changsha, temperature=22°C, humidity=65%, description=Clear skies with light breeze, windSpeed=10 km/h}"]], isError=false]
            assertFalse(resultString.isEmpty());

            client.closeGracefully();
        } catch (Exception ex) {
            fail("Exception found", ex);
        }
    }
}
