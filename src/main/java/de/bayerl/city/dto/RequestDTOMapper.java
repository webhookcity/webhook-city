package de.bayerl.city.dto;

import com.google.gson.Gson;
import de.bayerl.city.model.Request;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RequestDTOMapper {

    public List<RequestDTO> map(List<Request> requestList) {
        return requestList.stream().map(this::map).collect(Collectors.toList());
    }

    private RequestDTO map(Request request) {
        Gson gson = new Gson();
        return RequestDTO.builder()
                .id(request.getId())
                .method(request.getMethod())
                .body(request.getBody())
                .url(request.getUrl())
                .headers(gson.fromJson(request.getHeaders(), Map.class))
                .build();
    }
}
