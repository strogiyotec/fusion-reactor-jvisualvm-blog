package com.fusion.reactor;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(proxyBeanMethods = false)
public class DemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RestController
    @RequestMapping("/users")
    static class UserController {

        @Autowired
        private DSLContext dslContext;

        @GetMapping("/save")
        public long save() {
            final Record1<Integer> id = this.dslContext.insertInto(Tables.USERS)
                .columns(Tables.USERS.NAME)
                .values(UUID.randomUUID().toString())
                .returningResult(Tables.USERS.ID)
                .fetchOne();
            final Integer userId = id.getValue(Tables.USERS.ID);
            this.dslContext.insertInto(Tables.ORDERS)
                .columns(Tables.ORDERS.USER_ID, Tables.ORDERS.NAME, Tables.ORDERS.PRICE)
                .values(userId, "Banana", 12.5)
                .values(userId, "Apples", 13.0)
                .execute();
            return userId;
        }
    }

}
