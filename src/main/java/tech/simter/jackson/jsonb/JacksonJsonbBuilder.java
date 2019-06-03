package tech.simter.jackson.jsonb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.spi.JsonProvider;

import static tech.simter.jackson.jsonb.JacksonJsonbUtils.DEFAULT_PROPERTY_INCLUSION;

/**
 * The {@link JsonbBuilder} implementation by Jackson.
 *
 * @author RJ
 */
class JacksonJsonbBuilder implements JsonbBuilder {
  private JsonbConfig config;

  @Override
  public JsonbBuilder withConfig(JsonbConfig config) {
    this.config = config;
    return this;
  }

  @Override
  public JsonbBuilder withProvider(JsonProvider jsonProvider) {
    return this;
  }

  @Override
  public Jsonb build() {
    return new JacksonJsonb(getMapper());
  }

  private ObjectMapper cacheMapper;

  ObjectMapper getMapper() {
    // get from di context bean
    cacheMapper = JacksonJsonbUtils.getObjectMapper();

    if (cacheMapper == null) {
      // create a new ObjectMapper instance
      cacheMapper = new ObjectMapper();

      // global default config
      initGlobalDefaultConfig(cacheMapper);
    }

    // config jackson ObjectMapper by JsonbConfig
    initByJsonbConfig(cacheMapper);

    return cacheMapper;
  }

  // initial jackson by JsonbConfig
  private void initByJsonbConfig(ObjectMapper mapper) {
    if (config != null) {
      // set default property inclusion
      config.getProperty(DEFAULT_PROPERTY_INCLUSION).ifPresent(value ->
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.valueOf(value.toString()))
      );
    }
  }

  // initial jackson default config
  private void initGlobalDefaultConfig(ObjectMapper mapper) {
    // not serialize null and empty value
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY);

    // disabled some features
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    // enabled some features
    mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

    try {
      // register jackson JavaTimeModule before findAndRegisterModules
      // because jackson-2.x auto-registration will only register older JSR310Module
      Class<?> clazz;
      try {
        clazz = Class.forName("tech.simter.jackson.ext.javatime.JavaTimeModule");
      } catch (ClassNotFoundException e) {
        try {
          clazz = Class.forName("com.fasterxml.jackson.datatype.jsr310.JavaTimeModule");
        } catch (ClassNotFoundException ex) {
          clazz = null;
        }
      }
      if (clazz != null) mapper.registerModule((Module) clazz.getDeclaredConstructor().newInstance());
    } catch (Exception ignored) {
    }

    // auto register jackson modules by dependencies
    // see https://github.com/FasterXML/jackson-modules-java8
    mapper.findAndRegisterModules();
  }
}