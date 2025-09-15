package com.lyf.lyfcodehelper.ai;

import jakarta.annotation.Resource;
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
  void testChatForReport() {
    String userMessage = "Hi, I am Evan, a Java developer with 2 years experience.";
    AiCodeHelperService.Report report = aiCodeHelperService.chatForReport(userMessage);
    System.out.println(report);
  }
}