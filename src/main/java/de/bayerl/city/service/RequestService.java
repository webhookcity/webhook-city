package de.bayerl.city.service;

import com.google.gson.Gson;
import de.bayerl.city.dao.RequestDao;
import de.bayerl.city.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RequestService {

    private final RequestDao requestDao;

    @Autowired
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
        var request = new Request(null, url, gson.toJson(headers), method, body);
        requestDao.save(request);
    }

}
