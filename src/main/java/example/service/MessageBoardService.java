package example.service;

import example.model.rest.Message;
import example.model.rest.Version;
import example.model.rest.v1.MessageV1;
import example.util.MessageConvertor;
import example.model.rest.v2.MessageV2;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageBoardService {

    private List<MessageV2> messageList = new ArrayList<>();

    public boolean addMessage(@NonNull MessageV2 messageV2) {
        return messageList.add(messageV2);
    }

    public List<? extends Message> getListWithCorrectVersion(@NonNull Version version) {
        return version.equals(Version.V1) ? getListV1() : getListV2();
    }

    private List<MessageV2> getListV2() {
        return messageList;
    }

    private List<MessageV1> getListV1() {
        return messageList
                .stream()
                .map(MessageConvertor::convertToV1)
                .collect(Collectors.toList());
    }
}



