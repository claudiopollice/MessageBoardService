package example.controller.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import example.model.rest.Version;
import example.service.MessageBoardService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static example.util.OutputFormatter.asFormat;
import static example.util.OutputFormatter.validateFormat;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Slf4j
@Consumes(APPLICATION_JSON)
@Produces({APPLICATION_JSON, APPLICATION_XML})
@Component
@Path("/messages/list/v2")
public class ListMessageControllerV2 {

    private MessageBoardService messageBoardService;

    public ListMessageControllerV2(@NonNull MessageBoardService messageBoardService) {
        this.messageBoardService = messageBoardService;
    }

    @GET
    @Path("/{format}")
    public Response listMessagesInFormat(@PathParam("format") String format) throws JsonProcessingException {
        return asFormat(Version.V2, validateFormat(format), messageBoardService.getListWithCorrectVersion(Version.V2));
    }

}
