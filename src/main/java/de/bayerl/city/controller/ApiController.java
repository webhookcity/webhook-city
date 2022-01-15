package de.bayerl.city.controller;

import de.bayerl.city.dto.RequestDTO;
import de.bayerl.city.dto.RequestDTOMapper;
import de.bayerl.city.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ApiController {

    private final RequestService requestService;
    private final RequestDTOMapper requestDTOMapper;

    @Autowired
    public ApiController(RequestService requestService, RequestDTOMapper requestDTOMapper) {
        this.requestService = requestService;
        this.requestDTOMapper = requestDTOMapper;
    }

    @GetMapping("/api/v1/health")
    public void getHealth() {
        log.info("health");
    }

    @GetMapping("/api/v1/requests")
    public List<RequestDTO> getAllRequests() {
        log.info("GET all");
        return requestDTOMapper.map(requestService.getAllRequests());
    }

    @DeleteMapping("/api/v1/requests")
    public void deleteAllRequests() {
        log.info("DELETE all");
        requestService.deleteAllRequests();
    }

}
