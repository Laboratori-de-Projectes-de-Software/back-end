package uib.lab.api.infraestructura.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiMessage {
    private final String timestamp = LocalDateTime.now().toString();

    private HttpStatus status;
    private String message;

    private Map<String, Set<String>> errors;

    @SuppressWarnings("unused")
    @JsonProperty("status")
    public int status() {
        return status.value();
    }
}
