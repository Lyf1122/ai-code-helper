package com.lyf.lyfcodehelper.ai;

import com.lyf.lyfcodehelper.ai.guardrail.SafeInputGuardrail;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import reactor.core.publisher.Flux;

import java.util.List;

@InputGuardrails(SafeInputGuardrail.class)
public interface AiCodeHelperService {

  @SystemMessage("system-prompt.txt")
  String chat(String userMessage);

  @SystemMessage("system-prompt.txt")
  Report chatForReport(String userMessage);

  record Report(String name, List<String> suggestionList) {}

  @SystemMessage("system-prompt.txt")
  Flux<String> chatStream(@MemoryId int memoryId, @UserMessage String userMessage);
}
