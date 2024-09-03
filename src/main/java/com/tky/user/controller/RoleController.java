package com.tky.user.controller;

import com.tky.common.vo.Result;
import com.tky.user.entity.Role;
import com.tky.user.service.RoleService;
import com.tky.user.vo.RoleQuery;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    
    @GetMapping("")
    public String toRoleListUI(){
        return "user/role-list";
    }
    
    
    @GetMapping("/rolelist")
    @ResponseBody
    public Result<Object> getRoleList(RoleQuery param){
    	
    	System.out.println(param);
        
    	List<Role> list = roleService.getRoleList(param);
        Long count = roleService.countRoleList(param);
        return Result.success(list,count);
    }
    
    
    
    @GetMapping("/addrole/ui")
    public String toUserinfoUI(HttpServletRequest request, HttpSession session){
        return "user/add_role";
    }
    
    
	@PostMapping("/addrole")
	@ResponseBody
	public Result<Object> addUser(Role role){	  	
	    return roleService.addRole(role);
	}
    
    
//    @PutMapping("/userinfo")
//    @ResponseBody
//    public Result<Object> chgUserinfo(User user,  HttpSession session){
//    	user = userService.chgUserinfo(user);
//        user.setPassword(null);
//        session.setAttribute("userInfo", user);
//        return Result.success("修改成功！");
//    }
//    
//    
//    @GetMapping("/chgpwd/ui")
//    public String toChgpwdUI(Model model){
//        return "user/user-password";
//    }
//    
//    @PostMapping("/chgpwd")
//    @ResponseBody
//    public Result<Object> chgpwd(String new_password, String old_password, HttpSession session){
//
//    	User user = (User) session.getAttribute("userInfo");
//    	user = userService.login(user);
//    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        // 参数1是请求密码，参数2是数据库中加密的值
////        boolean matches = user.getPassword().equals(old_password);
//      boolean matches = passwordEncoder.matches(old_password, user.getPassword());
//        if (matches) {
//        	user.setPassword(passwordEncoder.encode(new_password));
//        	userService.chgpwd(user);
//        	session.invalidate();
//            return Result.success("修改成功,请重新登录！");
//        } else {
//        	return Result.fail("旧密码不正确！");
//        }
//    }
//    
//    
//    @GetMapping("/adduser/ui")
//    public String toAdduserUI(Model model){
//        return "user/add_form";
//    }
//    
//    
//    @PostMapping("/adduser")
//    @ResponseBody
//    public Result<Object> addUser(User user){
//    	
//        return userService.addUser(user);
//    }
//    
//    
//    @PutMapping("/{ids}/status")
//    @ResponseBody
//    public Result<Object> chgUserdisabled(@PathVariable("ids") String user_id,  boolean disable){
//        return userService.chgUserdisabled(user_id, disable);
//    }
//    
//    
//    
//    @PutMapping("/{ids}/rstpwd")
//    @ResponseBody
//    public Result<Object> rstpwd(@PathVariable("ids") String user_id){
//        return userService.rstpwd(user_id);
//    }
//    
//    
//    
//    
//    @PostMapping("/logout")
//    @ResponseBody
//    public Result Logout(HttpServletRequest request, HttpSession session){
//    	session.invalidate();
//        return Result.success();
//    }
//    
    
    
    
    
}
