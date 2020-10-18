package example.model.rest.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import example.model.exception.ApiException;
import example.model.rest.Message;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.validator.routines.UrlValidator;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import static example.model.rest.Constraints.*;

@Getter
@XmlRootElement
public class MessageV2 extends Message {

    @Size(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH, message = TITLE_LENGTH_VIOLATON_MESSAGE)
    private String title;
    @Size(min = CONTENT_MIN_LENGTH, max = CONTENT_MAX_LENGTH, message = CONTENT_LENGTH_VIOLATON_MESSAGE)
    private String content;
    @Size(min = SENDER_MIN_LENGTH, max = SENDER_MAX_LENGTH, message = SENDER_LENGTH_VIOLATON_MESSAGE)
    private String sender;
    @Size(min = URL_MIN_LENGTH, max = URL_MAX_LENGTH, message = URL_LENGTH_VIOLATON_MESSAGE)
    private String url;

    @JsonCreator
    public MessageV2(@JsonProperty("title") @NonNull String title,
                     @JsonProperty("content")@NonNull String content,
                     @JsonProperty("sender")@NonNull String sender,
                     @JsonProperty("url")@NonNull String url) throws ApiException {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.url = validatedUrl(url);
    }

    private String validatedUrl(@NonNull String url) throws ApiException {
        if (UrlValidator.getInstance().isValid(url)) {
            return url;
        }
        throw ApiException.invalidUrl();
    }
}
