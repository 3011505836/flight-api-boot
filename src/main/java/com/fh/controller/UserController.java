package com.fh.controller;

import com.fh.model.User;
import com.fh.model.ServerResponse;
import com.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("addLogin")
    public ServerResponse addLogin(User user, HttpSession session){
        try {
            ServerResponse serverResponse =  userService.login(user);
           /* if(serverResponse.getCode() == ResponseEnum.SUCCESS.getCode()){
                session.setAttribute("user",serverResponse.getData());
            }*/
            return serverResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }

    }


    //获取当前登录会员信息的API接口
    @RequestMapping("getCurrentLoginUser")
    public ServerResponse getCurrentLoginUser(HttpSession session, HttpServletRequest request){
        try {
           /* User user = (User) session.getAttribute("user");*/
            User loginUser = (User) request.getAttribute("loginUser");
            User user = userService.getUserById(loginUser.getId());
            return ServerResponse.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }


}
