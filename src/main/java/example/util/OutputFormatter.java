package example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.ImmutableMap;
import example.model.rest.ResponseFormat;
import example.model.rest.Version;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Slf4j
public class OutputFormatter {

    private static final ResponseFormat DEFAULT_FORMAT = ResponseFormat.JSON;

    public static ResponseFormat validateFormat(String format) {
        try {
            return ResponseFormat.valueOf(format.toUpperCase());
        } catch (IllegalArgumentException iae) {
            log.info("Invalid format provided: {}. Defaulting to JSON.", format);
            return DEFAULT_FORMAT;
        }
    }

    public static Response asFormat(Version version, ResponseFormat format, Object o) throws JsonProcessingException {
        return versionSupportsFormat(version, format) ? writeAsFormat(format, o) : writeDefaultFormat(o);
    }

    private static boolean versionSupportsFormat(Version version, ResponseFormat format) {
        return supportedFormatsPerVersion.get(version).contains(format);
    }

    private static ImmutableMap<Version, List<ResponseFormat>> supportedFormatsPerVersion = ImmutableMap.<Version, List<ResponseFormat>>builder()
            .put(Version.V1, List.of(ResponseFormat.JSON))
            .put(Version.V2, List.of(ResponseFormat.JSON, ResponseFormat.XML))
            .build();

    private static Response writeAsFormat(ResponseFormat format, Object o) throws JsonProcessingException {
        if (format.equals(ResponseFormat.XML)) {
            return asXml(o);
        }
        return asJson(o);
    }

    private static Response writeDefaultFormat(Object o) throws JsonProcessingException {
        return writeAsFormat(DEFAULT_FORMAT, o);
    }

    private static Response asXml(Object o) throws JsonProcessingException {
        return Response.ok(new XmlMapper().writeValueAsString(o)).type(APPLICATION_XML).build();
    }

    private static Response asJson(Object o) {
        return Response.ok(o).build();
    }
}
