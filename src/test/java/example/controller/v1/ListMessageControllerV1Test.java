package example.controller.v1;

import example.controller.TestData;
import example.model.rest.v1.MessageV1;
import example.service.MessageBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("List Messages V1 Controller Test")
class ListMessageControllerV1Test extends TestData {

    @Test
    @DisplayName("should return list in v1 format")
    void should_return_list_in_v1_format() {

        MessageBoardService messageBoardService = new MessageBoardService();
        messagesV2().forEach(messageBoardService::addMessage);
        ListMessageControllerV1 listMessageControllerV1 = new ListMessageControllerV1(messageBoardService);

        Response response = listMessageControllerV1.listMessages();
        List responseBody = (List) response.getEntity();
        assertAll("Response contains v1 messages",
                () -> assertEquals(200, response.getStatus()),
                () -> assertEquals(4, responseBody.size()),
                () -> assertEquals(MessageV1.class, responseBody.get(0).getClass()),
                () -> assertEquals("title1", ((MessageV1) responseBody.get(0)).getTitle()),
                () -> assertEquals("content1", ((MessageV1) responseBody.get(0)).getContent()),
                () -> assertEquals("sender1", ((MessageV1) responseBody.get(0)).getSender())
        );
    }
}