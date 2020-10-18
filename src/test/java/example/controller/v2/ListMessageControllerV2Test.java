package example.controller.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import example.controller.TestData;
import example.model.rest.v2.MessageV2;
import example.service.MessageBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("List Messages V2 Controller Test")
class ListMessageControllerV2Test extends TestData {

    @Test
    @DisplayName("should return list in v2 format")
    void should_return_list_in_v2_format() throws JsonProcessingException {

        MessageBoardService messageBoardService = new MessageBoardService();
        messagesV2().forEach(messageBoardService::addMessage);
        ListMessageControllerV2 listMessageControllerV2 = new ListMessageControllerV2(messageBoardService);

        Response response = listMessageControllerV2.listMessagesInFormat("json");
        List responseBody = (List) response.getEntity();
        assertAll("Response contains v2 messages",
                () -> assertEquals(200, response.getStatus()),
                () -> assertEquals(4, responseBody.size()),
                () -> assertEquals(MessageV2.class, responseBody.get(0).getClass()),
                () -> assertEquals("title1", ((MessageV2) responseBody.get(0)).getTitle()),
                () -> assertEquals("content1", ((MessageV2) responseBody.get(0)).getContent()),
                () -> assertEquals("sender1", ((MessageV2) responseBody.get(0)).getSender()),
                () -> assertEquals("http://website1.com", ((MessageV2) responseBody.get(0)).getUrl())
        );
    }

}