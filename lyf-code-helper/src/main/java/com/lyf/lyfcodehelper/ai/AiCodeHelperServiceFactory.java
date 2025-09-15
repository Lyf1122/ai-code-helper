package com.lyf.lyfcodehelper.ai;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeHelperServiceFactory {
  @Resource
  private ChatModel qwenChatModel;

  @Bean
  public AiCodeHelperService aiCodeHelperService() {
    // 会话记忆，默认存储在cache，断电消失，也支持持久化存储
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
    // 基于反射创建服务
    return AiServices.builder(AiCodeHelperService.class)
      .chatModel(qwenChatModel)
      .chatMemory(chatMemory)
      .build();
  }
}
