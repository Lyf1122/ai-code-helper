package com.lyf.lyfcodehelper.ai.guardrail;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.guardrail.InputGuardrail;
import dev.langchain4j.guardrail.InputGuardrailResult;

import java.util.Set;

/**
 * 类似拦截器 拦截输入或输出是否存在敏感词
 */
public class SafeInputGuardrail implements InputGuardrail {

  // 敏感词集合
  private static final Set<String> sensitiveWords = Set.of("password", "credit card", "social security number");

  @Override
  public InputGuardrailResult validate(UserMessage userMessage) {
    String inputText = userMessage.singleText().toLowerCase();
    // 正则分割为单词
    String[] words = inputText.split("\\W+");
    // 遍历查询是否存在敏感词
    for (String word : words) {
      if (sensitiveWords.contains(word)) {
        return fatal("Sensitive word detected: " + word);
      }
    }
    return InputGuardrailResult.success();
  }
}
