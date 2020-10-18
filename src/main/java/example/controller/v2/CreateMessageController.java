package example.controller.v2;

import example.service.MessageBoardService;
import example.model.rest.v2.MessageV2;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Slf4j
@Component
@Path("messages/create")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Validated
public class CreateMessageController {

    private MessageBoardService messageBoardService;

    public CreateMessageController(@NonNull MessageBoardService messageBoardService) {
        this.messageBoardService = messageBoardService;
    }

    @POST
    public Response createMessage(@RequestBody @Valid MessageV2 messageV2) {
        return messageBoardService.addMessage(messageV2)
                ? Response.ok().build()
                : Response.status(500).build();
    }

}

