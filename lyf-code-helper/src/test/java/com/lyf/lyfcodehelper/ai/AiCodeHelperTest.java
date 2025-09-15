package com.lyf.lyfcodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeHelperTest {
  @Resource
  private AiCodeHelper aiCodeHelper;

  @Test
  void chat() {
    String message = "你好，我是Evan，一位有两年Java开发经验的求职者";
    String result = aiCodeHelper.chat(message);
    System.out.println(result);
  }

}