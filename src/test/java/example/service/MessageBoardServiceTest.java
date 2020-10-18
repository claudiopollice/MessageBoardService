package example.service;

import example.model.rest.Version;
import example.model.rest.v1.MessageV1;
import example.model.rest.v2.MessageV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Message Board Service Test")
class MessageBoardServiceTest {

    @Test
    @DisplayName("should add v2 message to list")
    void should_add_v2_message_to_list() {
        MessageBoardService messageBoardService = new MessageBoardService();
        assertEquals(0, messageBoardService.getListWithCorrectVersion(Version.V2).size());
        assertEquals(0, messageBoardService.getListWithCorrectVersion(Version.V1).size());

        MessageV2 testMessage = new MessageV2("title1", "content1", "sender1", "https://website1.com");
        messageBoardService.addMessage(testMessage);

        assertEquals(1, messageBoardService.getListWithCorrectVersion(Version.V2).size());
        assertEquals(1, messageBoardService.getListWithCorrectVersion(Version.V1).size());
    }

    @Test
    @DisplayName("should return list in v1 format")
    void should_return_list_in_v1_format() {
        MessageBoardService messageBoardService = new MessageBoardService();
        messageBoardService.addMessage(new MessageV2("title1", "content1", "sender1", "https://website1.com"));
        assertEquals(1, messageBoardService.getListWithCorrectVersion(Version.V1).size());
        assertEquals(MessageV1.class, messageBoardService.getListWithCorrectVersion(Version.V1).get(0).getClass());
    }

    @Test
    @DisplayName("should return list in v2 format")
    void should_return_list_in_v2_format() {
        MessageBoardService messageBoardService = new MessageBoardService();
        messageBoardService.addMessage(new MessageV2("title1", "content1", "sender1", "https://website1.com"));
        assertEquals(1, messageBoardService.getListWithCorrectVersion(Version.V2).size());
        assertEquals(MessageV2.class, messageBoardService.getListWithCorrectVersion(Version.V2).get(0).getClass());
    }
}