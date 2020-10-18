package example.model.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Abstract class for model serialization and deserialization.")
public abstract class AbstractModelTest<T> {

    private ObjectMapper objectMapper = new ObjectMapper();
    private XmlMapper xmlMapper = new XmlMapper();

    public abstract <T> T getDefaultEntity();
    public abstract String getJsonValue();
    public abstract String getXmlValue();

    @Test
    @DisplayName("should serialize to json as intended")
    void should_serialize_to_json_as_intended() throws JsonProcessingException {
        assertEquals(getJsonValue(), serializeToJson(getDefaultEntity()));
    }

    @Test
    @DisplayName("should serialize to xml as intended")
    void should_serialize_to_xml_as_intended() throws JsonProcessingException {
        assertEquals(getXmlValue(), serializeToXml(getDefaultEntity()));
    }

    @Test
    @DisplayName("should deserialize from json as intended")
    void should_deserialize_from_json_as_intended() throws JsonProcessingException {
        assertThat((Object) deserialize(getDefaultEntity().getClass(), getJsonValue())).isEqualToComparingFieldByFieldRecursively(getDefaultEntity());
    }

//    private void assertJsonSerialization(String jsonValue, Object o) throws JsonProcessingException {
//        assertEquals(getJsonValue(), serialize(o));
//    }
//
//    private void assertXmlSerialization(String xmlValue, Object o) {
//
//    }
//
//
//    private void assertJsonDeserialization(Object o, String jsonValue) throws JsonProcessingException {
//        assertThat((Object) deserialize(MessageV1.class, jsonValue)).isEqualToComparingFieldByFieldRecursively(o);
//    }

    private String serializeToJson(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    private String serializeToXml(Object o) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(o);
    }

    private <T> T deserialize(Class clazz, String string) throws JsonProcessingException {
        return (T) objectMapper.readValue(string, clazz);
    }

}
