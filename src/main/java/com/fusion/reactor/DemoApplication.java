package com.fusion.reactor;

import java.time.Instant;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(proxyBeanMethods = false)
public class DemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RestController
    static class TestController {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @GetMapping("/hello")
        public Map<String, String> helloWorld(final HttpServletRequest request) {
            this.jdbcTemplate.update("INSERT INTO requests (ip,time) values (?,?)", request.getRemoteAddr(), Instant.now().toString());
            return Map.of(
                "message", "World"
            );
        }
    }

}
