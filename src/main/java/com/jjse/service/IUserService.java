package com.jjse.service;

import java.util.List;

import com.jjse.model.dto.UserDto;
import com.jjse.model.entity.User;

public interface IUserService {

    List<User> listAll();

    User save(UserDto user);

    User findById(Integer id);

    void delete(User user);

    boolean existsById(Integer id);


}
