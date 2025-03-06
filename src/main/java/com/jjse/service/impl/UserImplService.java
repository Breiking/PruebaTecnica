package com.jjse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjse.model.dao.UserDao;
import com.jjse.model.dto.UserDto;
import com.jjse.model.entity.User;
import com.jjse.service.IUserService;


@Service
public class UserImplService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listAll(){
        return (List) userDao.findAll();
    }

    @Transactional
    @Override
    public User save(UserDto userDto){
        User user = User.builder()
            .nombre(userDto.getNombre())
            .email(userDto.getEmail())
            .build();
        return userDao.save(user);
    }

    @Transactional
    @Override
    public User findById(Integer id){
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(User user){
        userDao.delete(user);
    }

    @Override
    public boolean existsById(Integer id){
        return userDao.existsById(id);
    }

}