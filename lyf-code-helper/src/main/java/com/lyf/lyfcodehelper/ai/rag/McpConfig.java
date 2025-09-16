package com.lyf.lyfcodehelper.ai.rag;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {

  @Value("${bigmodel.api-key}")
  private String apiKey;

  @Bean
  public McpToolProvider mcpToolProvider() {
    // 和MCP通讯
    McpTransport mcpTransport = new HttpMcpTransport.Builder()
      .sseUrl("https://open.bigmodel.cn/api/mcp/web_search/sse?Authorization=" + apiKey)
      .logRequests(true)
      .logResponses(true)
      .build();
    // Create MCP Client
    McpClient mcpClient = new DefaultMcpClient.Builder()
      .key("lyfMcpClient")
      .transport(mcpTransport)
      .build();
    // Get tools from client
    return new McpToolProvider.Builder()
      .mcpClients(mcpClient)
      .build();
  }

}
