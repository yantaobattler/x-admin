package com.tky.user.controller;

import com.tky.common.vo.Result;
import com.tky.user.entity.User;
import com.tky.user.service.UserService;
import com.tky.zhanpin.entity.Zhanpin;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

//    带验证码
//    @PostMapping("/login1")
//    public Result login1(User param, @RequestParam("captcha") String captcha, HttpServletRequest request, HttpSession session){
//        // 验证码验证
//        if (!CaptchaUtil.ver(captcha, request)) {
//            return Result.fail("验证码错误！");
//        }
//
//        User user = userService.login(param);
//        if(user != null){
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            // 参数1是请求密码，参数2是数据库中加密的值
//            boolean matches = param.getPassword().equals(user.getPassword());
////          boolean matches = passwordEncoder.matches(param.getPassword(), user.getPassword());
//
//            if(matches) {
//                // 登录成功
//                user.setPassword(null);
//                session.setAttribute("userInfo", user);
//                return Result.success();
//            }
//        }
//        // 登录失败
//        return Result.fail("用户名或密码错误！");
//    }
    
    
//    不带验证码
    @PostMapping("/login")
    @ResponseBody
    public Result login(User param,  HttpServletRequest request, HttpSession session){

        User user = userService.login(param);
        if(user != null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            // 参数1是请求密码，参数2是数据库中加密的值
//            boolean matches = param.getPassword().equals(user.getPassword());
          boolean matches = passwordEncoder.matches(param.getPassword(), user.getPassword());

            if(matches) {
                // 登录成功
                user.setPassword(null);
                session.setAttribute("userInfo", user);
                return Result.success();
            }
        }
        // 登录失败
        return Result.fail("用户名或密码错误！");
    }
    
    
    @GetMapping("/userinfo/ui")
    public String toUserinfoUI(HttpServletRequest request, HttpSession session){
    	
    	// 获取session中所有的键值
        Enumeration<?> enumeration = session.getAttributeNames();
        // 遍历enumeration
        while (enumeration.hasMoreElements()) {
            // 获取session键值
            String name = enumeration.nextElement().toString();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            log.debug(name + " : " + value);
        }

        return "user/user-setting";
    }
    
    
    @GetMapping("/chgpwd/ui")
    public String toChgpwdUI(Model model){
        return "user/user-password";
    }
    
    
    @PostMapping("/chgpwd")
    @ResponseBody
    public Result chgpwd(String new_password, String old_password, HttpSession session){

    	User user = (User) session.getAttribute("userInfo");
    	user = userService.login(user);
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 参数1是请求密码，参数2是数据库中加密的值
//        boolean matches = user.getPassword().equals(old_password);
      boolean matches = passwordEncoder.matches(old_password, user.getPassword());
        if (matches) {
        	user.setPassword(passwordEncoder.encode(new_password));
        	userService.chgpwd(user);
        	session.invalidate();
            return Result.success("修改成功,请重新登录！");
        } else {
        	return Result.fail("旧密码不正确！");
        }
    }
    
    
    @PutMapping("/userinfo")
    @ResponseBody
    public Result<Object> chgUserinfo(User user,  HttpSession session){
    	user = userService.chgUserinfo(user);
        user.setPassword(null);
        session.setAttribute("userInfo", user);
        return Result.success("修改成功！");
    }
    
    
    @PostMapping("/logout")
    @ResponseBody
    public Result Logout(HttpServletRequest request, HttpSession session){
    	session.invalidate();
        return Result.success();
    }
    
    
}
