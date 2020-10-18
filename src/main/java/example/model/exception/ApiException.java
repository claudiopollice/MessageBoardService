package example.model.exception;

import lombok.Getter;
import lombok.NonNull;

import javax.ws.rs.core.Response.Status;

@Getter
public class ApiException extends RuntimeException {

    private static final String INVALID_URL_MESSAGE = "Not a valid url";

    private Status statusCode;

    public ApiException(@NonNull String errorMessage, @NonNull Status statusCode) {
        super(errorMessage);
        this.statusCode = statusCode;
    }

    public static ApiException invalidUrl() {
        return new ApiException(INVALID_URL_MESSAGE, Status.BAD_REQUEST);
    }
}
