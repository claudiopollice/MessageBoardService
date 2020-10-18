package example.config.jersey.exception;

import example.model.exception.ApiException;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        if (ex instanceof ApiException) {
            return handleApiException(ex);
        }
        if (ex instanceof NotFoundException) {
            return handleNotFoundException();
        }
        return Response.status(500).build();
    }

    private static Response handleApiException(Exception ex) {
        ApiException apiException = (ApiException) ex;
        return Response.status(apiException.getStatusCode()).entity(apiException.getMessage()).build();
    }

    private static Response handleNotFoundException() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}