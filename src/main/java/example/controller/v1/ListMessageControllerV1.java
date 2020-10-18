package example.controller.v1;

import example.model.rest.Version;
import example.service.MessageBoardService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Slf4j
@Consumes(APPLICATION_JSON)
@Produces({APPLICATION_JSON, APPLICATION_XML})
@Component
@Path("/messages/list/v1")
public class ListMessageControllerV1 {

    private MessageBoardService messageBoardService;

    public ListMessageControllerV1(@NonNull MessageBoardService messageBoardService) {
        this.messageBoardService = messageBoardService;
    }

    @GET
    public Response listMessages() {
        return Response.ok(messageBoardService.getListWithCorrectVersion(Version.V1)).build();
    }

}
