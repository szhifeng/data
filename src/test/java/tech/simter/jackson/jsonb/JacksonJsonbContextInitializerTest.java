package tech.simter.jackson.jsonb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertSame;
import static tech.simter.jackson.jsonb.JacksonJsonbContextInitializerTest.Cfg;

/**
 * Test {@link JacksonJsonbContextInitializer}.
 *
 * @author RJ
 */
@SpringJUnitConfig({JacksonJsonbContextInitializer.class, Cfg.class})
class JacksonJsonbContextInitializerTest {
  @Configuration
  static class Cfg {
    @Bean
    ObjectMapper objectMapper() {
      return new ObjectMapper();
    }
  }

  @Autowired
  private ObjectMapper mapper;

  @Test
  @DisplayName("should get mapper from di context")
  void test() {
    JacksonJsonbBuilder builder = (JacksonJsonbBuilder) new JacksonJsonbProvider().create();
    assertSame(mapper, builder.getMapper());
  }
}