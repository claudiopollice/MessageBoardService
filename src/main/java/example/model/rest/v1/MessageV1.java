package example.model.rest.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import example.model.rest.Message;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class MessageV1 extends Message {

    private String title;
    private String content;
    private String sender;

    @JsonCreator
    public MessageV1(@JsonProperty("title") @NonNull String title,
                     @JsonProperty("content") @NonNull String content,
                     @JsonProperty("sender") @NonNull String sender) {
        this.title = title;
        this.content = content;
        this.sender = sender;
    }
}
