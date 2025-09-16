package com.lyf.lyfcodehelper.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RagConfig {
  @Resource
  private EmbeddingModel embeddingModel;  // qwen-max
  @Resource
  private EmbeddingStore<TextSegment> embeddingStore;

  @Bean
  public ContentRetriever contentRetriever() {
    // 1. load documents
    List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");
    // 2. document splitter
    DocumentByParagraphSplitter splitter = new DocumentByParagraphSplitter(1000, 200);
    // 3. 自定义文档加载器，把文档转换成向量并加载到数据库
    EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
      .documentSplitter(splitter)
      .textSegmentTransformer(textSegment -> TextSegment.from(textSegment.metadata().getString("file_name") +
        "\n" + textSegment.text(), textSegment.metadata()))
      .embeddingModel(embeddingModel)
      .embeddingStore(embeddingStore)
      .build();
    // 加载文档
    ingestor.ingest(documents);
    // 自定义内容加载器
    return EmbeddingStoreContentRetriever.builder()
      .embeddingStore(embeddingStore)
      .embeddingModel(embeddingModel)
      .maxResults(5)
      .minScore(0.75)
      .build();
  }
}
