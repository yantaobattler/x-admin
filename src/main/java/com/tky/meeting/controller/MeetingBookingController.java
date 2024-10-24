package com.tky.meeting.controller;

import com.tky.common.vo.Result;
import com.tky.meeting.entity.MeetingRoom;
import com.tky.meeting.entity.RoomStatus;
import com.tky.meeting.service.MeetingService;
import com.tky.meeting.vo.MeetingRoomQuery;
import com.tky.meeting.vo.RoomStatusQuery;
import com.tky.user.entity.User;
import com.tky.user.service.RoleService;
import com.tky.user.service.UserService;
import com.tky.user.vo.UserQuery;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/meetingbooking")
@Slf4j
public class MeetingBookingController {

    @Autowired
    private MeetingService meetingService;

    
    
    @GetMapping("")
    public String tomeetingbookingListUI(){
        return "meeting/meetingbookinglist";
    }
    
    
    @GetMapping("/list")
    @ResponseBody
    public Result<Object> getroomstatusList(RoomStatusQuery param){
    	System.out.println(param);
    	List<RoomStatus> list = meetingService.getRoomStatusList(param);
        Long count = meetingService.countRoomStatusList(param);
        return Result.success(list,count);
    }
    
    
	@GetMapping("/add/UI")
	public String toaddUI(Model model){
	    return "meeting/room_add";
	}
	
	
	@GetMapping("/editroom/UI/{id}")
	public String toeditUI(@PathVariable("id") String id, Model model){
		MeetingRoom meetingroom = meetingService.getMeetingRoomByid(id);
		model.addAttribute("meetingroom", meetingroom);
	    return "meeting/room_edit";
	}
	
	
	@GetMapping("/checkroom/UI/{id}")
	public String tocheckUI(@PathVariable("id") String id, Model model){
		MeetingRoom meetingroom = meetingService.getMeetingRoomByid(id);
		model.addAttribute("meetingroom", meetingroom);
	    return "meeting/room_check";
	}
	
	
	@PutMapping("/{id}/status")
	@ResponseBody
	public Result<Object> chgdisabled(@PathVariable("id") String id,  boolean disable){
	    return meetingService.chgdisabled(id, disable);
	}
  

	@PostMapping("/addroom")
	@ResponseBody
	public Result<Object> add(MeetingRoom meetingroom){
	  	
	    return meetingService.addMeetingRoom(meetingroom);
	}
  
	@PostMapping("/uploadImage/{id}")
	@ResponseBody
	public Map<String, Object> uploadImage(@PathVariable("id") String id, MultipartFile file,HttpServletRequest request){
        Map<String, Object> map = meetingService.uploadImage(id, file, request);
        return map;
    }
    
    
	@DeleteMapping("/deleteroom//{id}")
	@ResponseBody
	public Result<Object> delete(@PathVariable("id") String id){
	  	
	    return meetingService.deleteMeetingRoom(id);
	}
	
	
	@PostMapping("/editroom")
	@ResponseBody
	public Result<Object> edit(MeetingRoom meetingroom){
	  	
	    return meetingService.editMeetingRoom(meetingroom);

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
//    
//    @GetMapping("/{id}/editroleuserUI")
//    public String editroleuserUI(@PathVariable("id") int user_id, Model model){
//    	model.addAttribute("user_id", user_id);
//        return "user/edit-role-user";
//    }
//    
//    
//    @GetMapping("/{id}/getusertreeByuser")
//    @ResponseBody
//    public List<?> getusertreeByuser(@PathVariable("id") int user_id){
//    	List<?> usertree = userService.getusertreeByuser(user_id);
//        return usertree;
//    }
//    
//    
//    @GetMapping("/{id}/getroletreeByuser")
//    @ResponseBody
//    public List<?> getroletreeByuser(@PathVariable("id") int user_id){
//    	List<?> roletree = userService.getroletreeByuser(user_id);
//        return roletree;
//    }
//    
//    
//    @PostMapping("/editroleuserByuser")
//    @ResponseBody
//    public Result<Object> editroleuserByuser(String roletree, String usertree){
//
//        return roleService.editroleuserByuser(roletree, usertree);
//    }
    
    
    
    
}
