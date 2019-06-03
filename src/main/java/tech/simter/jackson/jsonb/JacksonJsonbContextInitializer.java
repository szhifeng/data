package tech.simter.jackson.jsonb;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
  
/**
 * A simple bean for get {@link ObjectMapper} instance from this context
 * and set it by {@link JacksonJsonbUtils#setObjectMapper(ObjectMapper)}.
 *
 * @author RJ
 */
@Named
@Singleton
public class JacksonJsonbContextInitializer {
  @Inject
  private ObjectMapper objectMapper;

  @PostConstruct
  public void init() {
    JacksonJsonbUtils.setObjectMapper(objectMapper);
  }
}