package com.jjse.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.jjse.model.entity.User;

public interface UserDao extends CrudRepository<User, Integer> {

}

