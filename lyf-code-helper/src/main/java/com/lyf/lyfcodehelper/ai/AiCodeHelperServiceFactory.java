package com.lyf.lyfcodehelper.ai;

import com.lyf.lyfcodehelper.ai.tools.InterviewQuestionTools;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeHelperServiceFactory {
  @Resource
  private ChatModel qwenChatModel;
  @Resource
  private ContentRetriever contentRetriever;
  @Resource
  private McpToolProvider mcpToolProvider;
  @Resource
  private StreamingChatModel streamingChatModel;

  @Bean
  public AiCodeHelperService aiCodeHelperService() {
    // 会话记忆，默认存储在cache，断电消失，也支持持久化存储
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
    // 基于反射创建服务
    return AiServices.builder(AiCodeHelperService.class)
      .chatModel(qwenChatModel)
      .chatMemory(chatMemory)
      .contentRetriever(contentRetriever) // RAG
      .tools(new InterviewQuestionTools())
      .toolProvider(mcpToolProvider)
      .streamingChatModel(streamingChatModel) // 流式输出
      .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
      .build();
  }
}
