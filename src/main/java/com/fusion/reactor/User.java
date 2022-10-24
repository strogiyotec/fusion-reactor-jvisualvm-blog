package com.fusion.reactor;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {

    private String name;

    private long id;

    private List<Order> orders;

}
