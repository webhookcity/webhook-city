package de.bayerl.city.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
public class RequestDTO {

    private Long id;
    private String url;
    private Map<String, String> headers;
    private String method;
    private String body;
    private LocalDateTime createdOn;
}
