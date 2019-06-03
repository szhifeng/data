package tech.simter.jackson.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson jsonb tools.
 *
 * @author RJ
 */
public final class JacksonJsonbUtils {
  private JacksonJsonbUtils() {
  }

  /**
   * Property name for config jackson through {@link ObjectMapper#setDefaultPropertyInclusion(JsonInclude.Include)}.
   * <p>
   * All valid values are the enum name of {@link JsonInclude.Include}.
   */
  public static final String DEFAULT_PROPERTY_INCLUSION = "jsonb.jackson.default-property-inclusion";

  private static ObjectMapper objectMapper;

  static void setObjectMapper(ObjectMapper objectMapper) {
    JacksonJsonbUtils.objectMapper = objectMapper;
  }

  static ObjectMapper getObjectMapper() {
    return objectMapper;
  }
}