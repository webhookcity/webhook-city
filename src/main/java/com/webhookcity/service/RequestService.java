package com.webhookcity.service;

import com.google.gson.Gson;
import com.webhookcity.dao.RequestDao;
import com.webhookcity.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RequestService {

    private final RequestDao requestDao;

    public RequestService(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    public List<Request> getAllRequests() {
        return requestDao.findByOrderByIdDesc();
    }

    public void deleteAllRequests() {
        requestDao.deleteAll();
    }

    public void catchRequest(String method, String body, Map<String, String> headers, String url) {
        log.info("Catch new request: " + method + " - URL: " + url);
        Gson gson = new Gson();
        var request = new Request(null, url, gson.toJson(headers), method, body, null);
        requestDao.save(request);
    }

}
