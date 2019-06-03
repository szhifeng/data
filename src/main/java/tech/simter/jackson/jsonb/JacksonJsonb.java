package tech.simter.jackson.jsonb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbException;
import java.io.*;
import java.lang.reflect.Type;

/**
 * The {@link Jsonb} implementation by Jackson {@link ObjectMapper}.
 *
 * @author RJ
 */
class JacksonJsonb implements Jsonb {
  private ObjectMapper mapper;

  JacksonJsonb(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public <T> T fromJson(String str, Class<T> type) throws JsonbException {
    try {
      return mapper.readValue(str, type);
    } catch (IOException e) {
      throw new JsonbException(e.getMessage(), e);
    }
  }

  @Override
  public <T> T fromJson(String str, Type runtimeType) throws JsonbException {
    try {
      return mapper.readValue(str, TypeFactory.defaultInstance().constructType(runtimeType));
    } catch (IOException e) {
      throw new JsonbException(e.getMessage(), e);
    }
  }

  @Override
  public <T> T fromJson(Reader reader, Class<T> type) throws JsonbException {
    try {
      return mapper.readValue(reader, type);
    } catch (IOException e) {
      throw new JsonbException(e.getMessage(), e);
    }
  }

  @Override
  public <T> T fromJson(Reader reader, Type runtimeType) throws JsonbException {
    try {
      return mapper.readValue(reader, TypeFactory.defaultInstance().constructType(runtimeType));
    } catch (IOException e) {
      throw new JsonbException(e.getMessage(), e);
    }
  }

  @Override
  public <T> T fromJson(InputStream stream, Class<T> type) throws JsonbException {
    try {
      return mapper.readValue(stream, type);
    } catch (IOException e) {
      throw new JsonbException(e.getMessage(), e);
    }
  }

  @Override
  public <T> T fromJson(InputStream stream, Type runtimeType) throws JsonbException {
    try {
      return mapper.readValue(stream, TypeFactory.defaultInstance().constructType(runtimeType));
    } catch (IOException e) {
      throw new JsonbException(e.getMessage(), e);
    }
  }

  @Override
  public String toJson(Object object) throws JsonbException {
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new JsonbException(e.getMessage(), e);
    }
  }

  @Override
  public String toJson(Object object, Type type) throws JsonbException {
    return toJson(object);
  }

  @Override
  public void toJson(Object object, Writer writer) throws JsonbException {
    try {
      mapper.writeValue(writer, object);
    } catch (IOException e) {
      throw new JsonbException(e.getMessage(), e);
    }
  }

  @Override
  public void toJson(Object object, Type runtimeType, Writer writer) throws JsonbException {
    toJson(object, writer);
  }

  @Override
  public void toJson(Object object, OutputStream stream) throws JsonbException {
    try {
      mapper.writeValue(stream, object);
    } catch (IOException e) {
      throw new JsonbException(e.getMessage(), e);
    }
  }

  @Override
  public void toJson(Object object, Type runtimeType, OutputStream stream) throws JsonbException {
    toJson(object, stream);
  }

  @Override
  public void close() {
    // do nothing
  }
}