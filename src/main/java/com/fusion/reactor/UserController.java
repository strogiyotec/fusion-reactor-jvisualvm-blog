package com.fusion.reactor;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final AtomicInteger cnt = new AtomicInteger(0);

    @GetMapping("/save")
    public Long save() throws InterruptedException {
        final int condition = this.cnt.getAndIncrement();
        if (condition % 2 == 0) {
            TimeUnit.SECONDS.sleep(3);
        }
        final User user = new User();
        user.setName(UUID.randomUUID().toString());
        user.setItems(
            Set.of(
                new Order(12.0, "Banana"),
                new Order(12.0, "Apple")
            )
        );
        this.userRepository.save(user);
        if (condition % 5 == 0) {
            throw new RuntimeException("Business error");
        }
        return user.getId();
    }
}
