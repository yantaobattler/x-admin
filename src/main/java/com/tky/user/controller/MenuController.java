package com.tky.user.controller;

import com.tky.common.vo.Result;
import com.tky.user.entity.Menu;
import com.tky.user.entity.User;
import com.tky.user.service.MenuService;
import com.tky.user.service.UserService;
import com.tky.user.vo.UserQuery;
import com.tky.zhanpin.entity.Zhanpin;
import com.tky.zhanpin.vo.ZhanpinQuery;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    
    
    @GetMapping("")
    public String toMenuListUI(){
        return "user/menu_list";
    }
    
    
    @GetMapping("/list")
    @ResponseBody
    public Result<Object> getMenuList(){
        List<Menu> list = menuService.getMenuList();
        Long count = menuService.countMenuList();
        return Result.success(list,count);
    }
    
    
  
	@PutMapping("/{ids}/status")
	@ResponseBody
	public Result<Object> chgMenudisabled(@PathVariable("ids") String id,  boolean disable){
	    return menuService.chgMenudisabled(id, disable);
	}
	
	
	
	@GetMapping("/creat")
	public String toAddmenuUI(Model model, String pid){
		Menu menu = menuService.getMenubyid(pid);
		model.addAttribute("pname", menu.getTitle());
		model.addAttribute("pid", pid);
	    return "user/menu_add";
	}
	
	
		
	@PostMapping("/addmenu")
	@ResponseBody
	public Result<Object> addMenu(Menu menu){
		
	    return menuService.addMenu(menu);
	}
	
	
	
	@GetMapping("/{ids}/editpage")
	public String toEditmenuUI(@PathVariable("ids") String id, Model model){
		Menu menu = menuService.getMenubyid(id);
		model.addAttribute("menu", menu);
	    return "user/menu_edit";
	}
	
	
	
	@PostMapping("/editmenu")
	@ResponseBody
	public Result<Object> editMenu(Menu menu){
		
	    return menuService.editMenu(menu);
	}
	
	
	
	@DeleteMapping("/{ids}/delete")
	@ResponseBody
	public Result<Object> deleteMenu(@PathVariable("ids") String id){
		
	    return menuService.deleteMenu(id);
	}  
	
	
	

	
  
//    
//    
//    
//    @GetMapping("/userinfo/ui")
//    public String toUserinfoUI(HttpServletRequest request, HttpSession session){
//        return "user/user-setting";
//    }
//    
//    
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
//    
    
    
    
}
