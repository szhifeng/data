package tech.simter.jackson.jsonb;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.spi.JsonbProvider;

/**
 * The {@link JsonbProvider} implementation by Jackson.
 *
 * @author RJ
 */
public class JacksonJsonbProvider extends JsonbProvider {
  @Override
  public JsonbBuilder create() {
    return new JacksonJsonbBuilder();
  }
}