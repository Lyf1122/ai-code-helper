package com.lyf.lyfcodehelper.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AiCodeHelper {

  private static final String SYSTEM_MESSAGE = """
    你是一位Java开发专家，帮助用户解决Java学习和求职面试遇到的问题，并给出建议，重点关注以下几个方面：
    1.提供清晰的解答思路，并针对问题给出具体的代码示例
    2.提供项目学习建议
    3.分享相关的高频面试题和技巧
    """;

  @Resource
  private ChatModel qwenChatModel;

  public String chat(String message) {
    SystemMessage systemMessage = SystemMessage.from(SYSTEM_MESSAGE);
    UserMessage userMessage = UserMessage.from(message);
    ChatResponse chatResponse = qwenChatModel.chat(systemMessage, userMessage);
    AiMessage aiMessage = chatResponse.aiMessage();
    log.info("AI Output: {}", aiMessage.toString());
    return aiMessage.text();
  }

  public String chat(UserMessage message) {
    ChatResponse chatResponse = qwenChatModel.chat(message);
    AiMessage aiMessage = chatResponse.aiMessage();
    log.info("AI Output: {}", aiMessage.toString());
    return aiMessage.text();
  }

}
