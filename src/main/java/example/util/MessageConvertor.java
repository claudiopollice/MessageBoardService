package example.util;

import example.model.rest.v1.MessageV1;
import example.model.rest.v2.MessageV2;
import lombok.NonNull;

public class MessageConvertor {

    public static MessageV1 convertToV1(@NonNull MessageV2 mV2) {
        return new MessageV1(mV2.getTitle(), mV2.getContent(), mV2.getSender());
    }
}
