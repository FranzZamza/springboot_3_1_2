package com.shamsutdinov.springboot_3_1_2.service;

import com.shamsutdinov.springboot_3_1_2.dao.UserDao;
import com.shamsutdinov.springboot_3_1_2.model.User;
import com.shamsutdinov.springboot_3_1_2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserDao userDao;
    private final UserRepository userRepository;

    public UserServiceImp(UserDao userDao, UserRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void save(User user) {
        //userDao.save(user);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        // return userDao.getUsers();
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        //return userDao.getUserById(id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
        // userDao.update(id, user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        //userDao.delete(id);
        userRepository.deleteById(id);

    }
}
