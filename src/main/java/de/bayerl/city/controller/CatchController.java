package de.bayerl.city.controller;

import de.bayerl.city.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class CatchController {

    private final RequestService requestService;

    @Autowired
    public CatchController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/**")
    public String getAllGet(@RequestHeader Map<String, String> headers, HttpServletRequest request) {
        requestService.catchRequest(RequestMethod.GET, null, headers, getUrl(request));
        return "{}";
    }

    @PostMapping("/**")
    public String getAllPost(@RequestBody(required = false)  String requestBody, @RequestHeader Map<String, String> headers, HttpServletRequest request) {
        requestService.catchRequest(RequestMethod.POST, requestBody, headers, getUrl(request));
        return "{}";
    }

    @PatchMapping("/**")
    public String getAllPatch(@RequestBody(required = false) String requestBody, @RequestHeader Map<String, String> headers, HttpServletRequest request) {
        requestService.catchRequest(RequestMethod.PATCH, requestBody, headers, getUrl(request));
        return "{}";
    }

    @RequestMapping(method = {RequestMethod.HEAD}, value = "/**")
    public void getAllHead(@RequestHeader Map<String, String> headers, HttpServletRequest request) {
        requestService.catchRequest(RequestMethod.HEAD, null, headers, getUrl(request));
    }

    @PutMapping("/**")
    public String getAllPut(@RequestBody(required = false) String requestBody, @RequestHeader Map<String, String> headers, HttpServletRequest request) {
        requestService.catchRequest(RequestMethod.PUT, requestBody, headers, getUrl(request));
        return "{}";
    }

    @DeleteMapping("/**")
    public String getAllDelete(@RequestBody(required = false) String requestBody, @RequestHeader Map<String, String> headers, HttpServletRequest request) {
        requestService.catchRequest(RequestMethod.DELETE, requestBody, headers, getUrl(request));
        return "{}";
    }

    private String getUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            url = url + "?" + request.getQueryString();
        }

        return url;
    }

}
