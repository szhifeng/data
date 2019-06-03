# simter-jackson-jsonb changelog

## 0.4.0 2019-05-12

- Set below default jackson config:
    - Set serialization inclusion to `NON_EMPTY`
    - Disable some features:
        - DeserializationFeature.`FAIL_ON_UNKNOWN_PROPERTIES`
        - DeserializationFeature.`ADJUST_DATES_TO_CONTEXT_TIME_ZONE`
        - SerializationFeature.`WRITE_DATES_AS_TIMESTAMPS`
    - Enable feature DeserializationFeature.`ACCEPT_EMPTY_STRING_AS_NULL_OBJECT`
    - Add a custom `JavaTimeModule` by [simter-jackson-ext]. The default data-time format is like '`yyyy-MM-dd HH:mm`', accurate to minute and without zone and offset info (global use local zone and offset default)

## 0.3.0 2019-05-11

- Support get ObjectMapper instance from DI context bean
- Add `JacksonJsonbUtils.DEFAULT_PROPERTY_INCLUSION = "jsonb.jackson.default-property-inclusion"` for config `ObjectMapper#setDefaultPropertyInclusion(JsonInclude.Include)`

## 0.2.0 2019-05-10

- Auto register jackson modules by dependencies, see [jackson-modules-java8](See https://github.com/FasterXML/jackson-modules-java8)
- Support jackson `JsonInclude.Include` enum config for `ObjectMapper.setDefaultPropertyInclusion(...)`

## 0.1.0 2019-05-10

- Very basic json-b support by simple use "new ObjectMapper()"

[simter-jackson-ext]: https://github.com/simter/simter-jackson-ext