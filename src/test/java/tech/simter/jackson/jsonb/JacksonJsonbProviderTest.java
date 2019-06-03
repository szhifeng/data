package tech.simter.jackson.jsonb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test {@link JacksonJsonbProvider}.
 *
 * @author RJ
 */
class JacksonJsonbProviderTest {
  @Test
  @DisplayName("should create a not null JsonbBuilder")
  void test() {
    JsonbBuilder builder = new JacksonJsonbProvider().create();
    assertNotNull(builder);
  }
}