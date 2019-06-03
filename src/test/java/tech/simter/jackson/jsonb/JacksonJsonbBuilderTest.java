package tech.simter.jackson.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.spi.JsonProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static tech.simter.jackson.jsonb.JacksonJsonbUtils.DEFAULT_PROPERTY_INCLUSION;

/**
 * Test {@link JacksonJsonbBuilder}.
 *
 * @author RJ
 */
@ExtendWith(MockitoExtension.class)
class JacksonJsonbBuilderTest {
  @Mock
  private JsonProvider jsonProvider;

  @BeforeEach
  void setUp() {
    JacksonJsonbUtils.setObjectMapper(null);
  }

  @Test
  @DisplayName("with empty JsonbConfig")
  void withEmptyConfig() {
    JacksonJsonbBuilder builder = new JacksonJsonbBuilder();
    assertSame(builder, builder.withConfig(new JsonbConfig()));

    String actual = builder.build().toJson(new Example());
    assertEquals("{\"anInt\":0}", actual);
  }

  @Test
  @DisplayName("with null JsonbConfig")
  void withNullConfig() {
    JacksonJsonbBuilder builder = new JacksonJsonbBuilder();
    assertSame(builder, builder.withConfig(null));

    String actual = builder.build().toJson(new Example());
    assertEquals("{\"anInt\":0}", actual);
  }

  @Test
  @DisplayName("with not null JsonProvider")
  void withNotNullProvider() {
    JacksonJsonbBuilder builder = new JacksonJsonbBuilder();
    assertSame(builder, builder.withProvider(jsonProvider));
  }

  @Test
  @DisplayName("with null JsonProvider")
  void withNullProvider() {
    JacksonJsonbBuilder builder = new JacksonJsonbBuilder();
    assertSame(builder, builder.withProvider(null));
  }

  @Test
  @DisplayName("should build a not null Jsonb")
  void build() {
    Jsonb jsonb = new JacksonJsonbBuilder().build();
    assertNotNull(jsonb);
  }

  @Test
  @DisplayName("exclude null values")
  void excludeNullValue() {
    JacksonJsonbBuilder builder = new JacksonJsonbBuilder();
    JsonbConfig jsonbConfig = new JsonbConfig();
    jsonbConfig.setProperty(DEFAULT_PROPERTY_INCLUSION, JsonInclude.Include.NON_NULL.name());
    assertSame(builder, builder.withConfig(jsonbConfig));

    String actual = builder.build().toJson(new Example());
    assertEquals("{\"anInt\":0,\"list\":[]}", actual);
  }

  @Test
  @DisplayName("exclude empty values (null + empty collection)")
  void excludeEmptyValue() {
    JacksonJsonbBuilder builder = new JacksonJsonbBuilder();
    JsonbConfig jsonbConfig = new JsonbConfig();
    jsonbConfig.setProperty(DEFAULT_PROPERTY_INCLUSION, JsonInclude.Include.NON_EMPTY.name());
    assertSame(builder, builder.withConfig(jsonbConfig));

    String actual = builder.build().toJson(new Example());
    assertEquals("{\"anInt\":0}", actual);
  }

  @Test
  @DisplayName("exclude absent values (null + Optional.empty())")
  void excludeAbsentValue() {
    JacksonJsonbBuilder builder = new JacksonJsonbBuilder();
    JsonbConfig jsonbConfig = new JsonbConfig();
    jsonbConfig.setProperty(DEFAULT_PROPERTY_INCLUSION, JsonInclude.Include.NON_ABSENT.name());
    assertSame(builder, builder.withConfig(jsonbConfig));

    // verify null values
    String actual = builder.build().toJson(new Example());
    assertEquals("{\"anInt\":0,\"list\":[]}", actual);

    // verify Optional.empty() values
    Map<String, Object> map = new HashMap<>();
    map.put("k1", "v1");
    map.put("k2", Optional.empty());
    map.put("k3", Optional.of("v3"));
    actual = builder.build().toJson(map);
    assertEquals("{\"k1\":\"v1\",\"k2\":null,\"k3\":\"v3\"}", actual); // why still has k2=null?
  }
}