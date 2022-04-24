package com.webhookcity.controller;

import com.webhookcity.dto.RequestDTOMapper;
import com.webhookcity.dto.RequestDTO;
import com.webhookcity.service.RequestService;
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

    public ApiController(RequestService requestService, RequestDTOMapper requestDTOMapper) {
        this.requestService = requestService;
        this.requestDTOMapper = requestDTOMapper;
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
