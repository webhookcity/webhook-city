package com.webhookcity.controller;

import com.webhookcity.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class CatchController {

    private final RequestService requestService;

    public CatchController(RequestService requestService) {
        this.requestService = requestService;
    }

    @RequestMapping(value = "/catch/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE})
    public String getAllMethods(@RequestBody(required = false) String requestBody, @RequestHeader Map<String, String> headers, HttpServletRequest request) {
        requestService.catchRequest(request.getMethod(), requestBody, headers, getUrl(request));
        return "{}";
    }

    @RequestMapping(method = {RequestMethod.HEAD}, value = "/catch/**")
    public void getAllHead(@RequestHeader Map<String, String> headers, HttpServletRequest request) {
        requestService.catchRequest(request.getMethod(), null, headers, getUrl(request));
    }

    private String getUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            url = url + "?" + request.getQueryString();
        }

        return url;
    }

}
