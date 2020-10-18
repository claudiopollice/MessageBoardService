package example.model.rest.v1;

import example.model.rest.AbstractModelTest;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Message V1 Model Test")
class MessageV1Test extends AbstractModelTest<MessageV1> {

    @Override
    public MessageV1 getDefaultEntity() {
        return new MessageV1("title1", "content1", "sender1");
    }

    @Override
    public String getJsonValue() {
        return "{\"title\":\"title1\",\"content\":\"content1\",\"sender\":\"sender1\"}";
    }

    @Override
    public String getXmlValue() {
        return "<MessageV1><title>title1</title><content>content1</content><sender>sender1</sender></MessageV1>";
    }

}