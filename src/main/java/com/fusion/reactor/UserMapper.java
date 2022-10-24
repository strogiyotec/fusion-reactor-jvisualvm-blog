package com.fusion.reactor;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void saveUser(User user);

    void saveOrders(@Param("orders") List<Order> orders,@Param("userId") long userId);

    default void saveUserWithOrders(User user) {
        this.saveUser(user);
        this.saveOrders(user.getOrders(),user.getId());
    }
}
