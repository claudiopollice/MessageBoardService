package example.controller;

import example.model.rest.v2.MessageV2;

import java.util.List;

public abstract class TestData {
    protected List<MessageV2> messagesV2() {
        return List.of(
                new MessageV2("title1", "content1", "sender1","http://website1.com"),
                new MessageV2("title2", "content2", "sender2","http://website2.com"),
                new MessageV2("title3", "content3", "sender3","http://website3.com"),
                new MessageV2("title4", "content4", "sender4","http://website4.com")
        );
    }
}
