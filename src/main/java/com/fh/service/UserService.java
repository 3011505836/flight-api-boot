package com.fh.service;

import com.fh.model.ServerResponse;
import com.fh.model.User;


public interface UserService {

    ServerResponse login(User user);

    User getUserById(Integer id);
}
