package com.fh.service.impl;

import com.fh.mapper.UserMapper;
import com.fh.model.User;
import com.fh.model.ResponseEnum;
import com.fh.model.ServerResponse;
import com.fh.service.UserService;
import com.fh.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;



    @Override
    public ServerResponse login(User user) {
        //登录名非空校验
        if(StringUtils.isBlank(user.getWhatever())){
            return ServerResponse.error(ResponseEnum.MEMBER_LOGINNAME_IS_NULL);
        }

        //密码非空校验
        if(StringUtils.isBlank(user.getPassWord())){
            return ServerResponse.error(ResponseEnum.MEMBER_PASSWORD_IS_NULL);
        }

        //通过登录名去查找用户名等于登录名的用户信息
        User getUser = userMapper.getUserIsHas(user.getWhatever());
        if(getUser == null){
            return ServerResponse.error(ResponseEnum.MEMBER_NOT_EXISTED);
        }

        //验证用户在登录页面输入的密码是否和数据库中存储的密码一致
        if(!user.getPassWord().equals(getUser.getPassWord())){
            return ServerResponse.error(ResponseEnum.MEMBER_PASSWORD_ERROR);
        }

        User loginUser = new User();
        loginUser.setUuid(UUID.randomUUID().toString());
        loginUser.setId(getUser.getId());
        loginUser.setName(getUser.getName());

        String token = JwtUtil.createToken(loginUser);

        /*String loginUserJson = JSON.toJSONString(loginUser);
        String base64LoginUserJson = Base64.getEncoder().encodeToString(loginUserJson.getBytes());
        String secretKey = "dKskJsd23#3$%!~CS32*";
        String sign = MD5Util.md5Hex(base64LoginUserJson + secretKey);
        String base64Sign = Base64.getEncoder().encodeToString(sign.getBytes());
        String token = base64LoginUserJson + "." + base64Sign;*/
        //将用户的登录状态存放到redis中
        redisTemplate.opsForValue().set("user:" + loginUser.getUuid(),232324,30, TimeUnit.MINUTES);
        return ServerResponse.success(token);
    }



    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
