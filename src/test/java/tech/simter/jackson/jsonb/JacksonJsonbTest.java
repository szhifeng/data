package tech.simter.jackson.jsonb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test {@link JacksonJsonb}.
 *
 * @author RJ
 */
class JacksonJsonbTest {
  private JacksonJsonb jsonb;
  private Example example;
  private String exampleJson;

  @BeforeEach
  void setUp() {
    this.jsonb = new JacksonJsonb(new ObjectMapper());

    this.example = new Example();
    this.example.setStr("test");
    this.example.setAnInt(1);

    this.exampleJson = "{\"anInt\":1,\"str\":\"test\",\"list\":[]}";
  }

  private void verifyExample(Example example) {
    assertNotNull(example);
    assertEquals("test", example.getStr());
    assertEquals(1, example.getAnInt());
  }

  @Test
  @DisplayName("fromJson(String, Class<T>)")
  void fromJson_String_Class() {
    Example ex = jsonb.fromJson(exampleJson, Example.class);
    verifyExample(ex);
  }

  @Test
  @DisplayName("fromJson(String, Type)")
  void fromJson_String_Type() {
    Example ex = jsonb.fromJson(exampleJson, (Type) Example.class);
    verifyExample(ex);
  }

  @Test
  @DisplayName("fromJson(reader, Class<T>)")
  void fromJson_Reader_Class() {
    Example ex = jsonb.fromJson(new StringReader(exampleJson), Example.class);
    verifyExample(ex);
  }

  @Test
  @DisplayName("fromJson(reader, Type)")
  void fromJson_Reader_Type() {
    Example ex = jsonb.fromJson(new StringReader(exampleJson), (Type) Example.class);
    verifyExample(ex);
  }

  @Test
  @DisplayName("fromJson(InputStream, Class<T>)")
  void fromJson_InputStream_Class() {
    Example ex = jsonb.fromJson(new ByteArrayInputStream(exampleJson.getBytes()), Example.class);
    verifyExample(ex);
  }

  @Test
  @DisplayName("fromJson(InputStream, Type)")
  void fromJson_InputStream_Type() {
    Example ex = jsonb.fromJson(new ByteArrayInputStream(exampleJson.getBytes()), (Type) Example.class);
    verifyExample(ex);
  }

  @Test
  @DisplayName("toJson(Object)")
  void toJson_Object() {
    String result = jsonb.toJson(this.example);
    assertEquals(exampleJson, result);
  }

  @Test
  @DisplayName("toJson(Object, Type)")
  void toJson_Object_Type() {
    String result = jsonb.toJson(this.example, Example.class);
    assertEquals(exampleJson, result);
  }

  @Test
  @DisplayName("toJson(Object, Writer)")
  void toJson_ObjectToWriter() {
    StringWriter writer = new StringWriter();
    jsonb.toJson(this.example, writer);
    String result = writer.toString();
    assertEquals(exampleJson, result);
  }

  @Test
  @DisplayName("toJson(Object, Type, Writer)")
  void toJson_Object_Type_Writer() {
    StringWriter writer = new StringWriter();
    jsonb.toJson(this.example, Example.class, writer);
    String result = writer.toString();
    assertEquals(exampleJson, result);
  }

  @Test
  @DisplayName("toJson(Object, OutputStream)")
  void toJson_Object_OutputStream() {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    jsonb.toJson(this.example, os);
    String result = new String(os.toByteArray());
    assertEquals(exampleJson, result);
  }

  @Test
  @DisplayName("toJson(Object, Type, OutputStream)")
  void toJson_Object_Type_OutputStream() {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    jsonb.toJson(this.example, Example.class, os);
    String result = new String(os.toByteArray());
    assertEquals(exampleJson, result);
  }
}