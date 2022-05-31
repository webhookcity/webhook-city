package com.webhookcity.api;

import com.intuit.karate.junit5.Karate;
import com.webhookcity.WebhookApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WebhookApplication.class)
@Slf4j
@TestPropertySource("/application-test.properties")
public class ApiTest implements InitializingBean {

    @LocalServerPort
    protected int port;

    @Karate.Test
    Karate testApi() {
        return Karate.run().relativeTo(getClass());
    }

    @Override
    public void afterPropertiesSet() {
        log.info("set local.server.port to {}", port);
        System.setProperty("local.server.port", String.valueOf(port));
    }
}
