package example.config.jersey;

import example.config.jersey.exception.ConstraintViolationMapper;
import example.config.jersey.exception.GenericExceptionMapper;
import example.config.jersey.exception.JsonMappingExceptionResponse;
import example.controller.v1.ListMessageControllerV1;
import example.controller.v2.CreateMessageController;
import example.controller.v2.ListMessageControllerV2;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
        register(ConstraintViolationMapper.class);
        register(JsonMappingExceptionResponse.class);
        register(GenericExceptionMapper.class);
        register(CreateMessageController.class);
        register(ListMessageControllerV1.class);
        register(ListMessageControllerV2.class);
    }
}