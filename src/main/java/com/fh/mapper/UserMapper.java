package com.fh.mapper;

import com.fh.model.User;

import java.util.List;

public interface UserMapper {


    User getUserIsHas(String whatEver);

    User getUserById(Integer id);
}
