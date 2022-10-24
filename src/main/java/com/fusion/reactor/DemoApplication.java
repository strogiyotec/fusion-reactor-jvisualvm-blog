package com.fusion.reactor;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(proxyBeanMethods = false)
@MapperScan("com.fusion.*")
public class DemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RestController
    @RequestMapping("/users")
    static class UserController {

        private UserMapper userMapper;

        @Autowired
        public UserController(final UserMapper userMapper) {
            this.userMapper = userMapper;
        }

        private final AtomicInteger cnt = new AtomicInteger(0);

        @GetMapping("/save")
        public long save() throws Exception {
            final int condition = this.cnt.getAndIncrement();
            if (condition % 2 == 0) {
                TimeUnit.SECONDS.sleep(3);
            }
            final User user = new User();
            user.setName(UUID.randomUUID().toString());
            user.setOrders(
                List.of(
                    new Order(12.0, "Banana"),
                    new Order(12.0, "Apple")
                )
            );
            this.userMapper.saveUserWithOrders(user);
            if (condition % 5 == 0) {
                throw new RuntimeException("Business error");
            }
            return user.getId();
        }
    }

}
