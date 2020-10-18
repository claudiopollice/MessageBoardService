package example.model.rest.v2;

import example.model.rest.AbstractModelTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Message V2 Model Test")
class MessageV2Test extends AbstractModelTest<MessageV2> {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    @BeforeEach
    public void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    public void close() {
        validatorFactory.close();
    }

    @Override
    public MessageV2 getDefaultEntity() {
        return new MessageV2("title1", "content1", "sender1", "https://website1.com");
    }

    @Override
    public String getJsonValue() {
        return "{\"title\":\"title1\",\"content\":\"content1\",\"sender\":\"sender1\",\"url\":\"https://website1.com\"}";
    }

    @Override
    public String getXmlValue() {
        return "<MessageV2><title>title1</title><content>content1</content><sender>sender1</sender><url>https://website1.com</url></MessageV2>";
    }

    @Test
    @DisplayName("should not allow too large values")
    void should_not_allow_too_large_values() {
        String largeTitle = IntStream.range(0, 100).mapToObj(i -> "s").collect(Collectors.joining(""));
        MessageV2 messageV2 = new MessageV2(largeTitle, "content1", "sender1", "https://website1.com");
        Set<ConstraintViolation<MessageV2>> violations = validator.validate(messageV2);

        assertEquals(violations.size(), 1);
        ConstraintViolation<MessageV2> violation = violations.iterator().next();
        assertEquals("Title must contain between 1 and 40 characters.", violation.getMessage());
    }

    @Test
    @DisplayName("should not allow bad url formats")
    void should_not_allow_bad_url_formats() {
        String badURL = "httwebsie1com";
        Throwable thrown = catchThrowable(() ->new MessageV2("title1", "content1", "sender1", badURL));
        assertEquals("Not a valid url", thrown.getMessage());
    }
}