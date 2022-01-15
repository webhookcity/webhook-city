package de.bayerl.city.service;

import com.google.gson.Gson;
import de.bayerl.city.dao.RequestDao;
import de.bayerl.city.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

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

    public void catchRequest(RequestMethod method, String body, Map<String, String> headers, String url) {
        log.info("Catch new request: " + method.name());
        // TODO validation?
        Gson gson = new Gson();
        var request = new Request(null, url, gson.toJson(headers), method.name(), body);
        requestDao.save(request);
    }

}
