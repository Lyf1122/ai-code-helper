package com.lyf.lyfcodehelper.ai;

import dev.langchain4j.service.SystemMessage;

import java.util.List;

public interface AiCodeHelperService {

  @SystemMessage("system-prompt.txt")
  String chat(String userMessage);

  @SystemMessage("system-prompt.txt")
  Report chatForReport(String userMessage);

  record Report(String name, List<String> suggestionList) {}
}
