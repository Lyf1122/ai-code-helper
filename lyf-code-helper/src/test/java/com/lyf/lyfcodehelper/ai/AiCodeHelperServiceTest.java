package com.lyf.lyfcodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeHelperServiceTest {
  @Resource
  private AiCodeHelperService aiCodeHelperService;

  @Test
  void testChat() {
    String result = aiCodeHelperService.chat("Hi, I am Evan, a Java developer");
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  @Disabled
  void testChatForReport() {
    String userMessage = "Hi, I am Evan, a Java developer with 2 years experience.";
    AiCodeHelperService.Report report = aiCodeHelperService.chatForReport(userMessage);
    System.out.println(report);
  }

  @Test
  @Disabled
  void testChatWithRag() {
    String userMessage = "回溯法通常适用于解决哪些问题？";
    String result = aiCodeHelperService.chat(userMessage);
    System.out.println(result);
  }

  @Test
  @Disabled
  void testChatWithTools() {
    String userMessage = "请使用面试题搜索工具，搜索关于 Java 的面试题";
    String result = aiCodeHelperService.chat(userMessage);
    System.out.println(result);
  }

  @Test
  @Disabled
  void testChatWithMcp() {
    String userMessage = "请使用MCP工具，搜索什么是langchain4j";
    String result = aiCodeHelperService.chat(userMessage);
    System.out.println(result);
  }
}