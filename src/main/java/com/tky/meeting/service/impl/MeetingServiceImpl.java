package com.tky.meeting.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tky.common.vo.Result;
import com.tky.meeting.entity.MeetingRoom;
import com.tky.meeting.mapper.MeetingRoomMapper;
import com.tky.meeting.service.MeetingService;
import com.tky.meeting.vo.MeetingRoomQuery;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Resource
    private MeetingRoomMapper meetingroomMapper;
    
    
    @Override
	public List<MeetingRoom> getMeetingRoomList(MeetingRoomQuery param) {
    	return meetingroomMapper.getMeetingRoomList(param);
	}

	@Override
	public Long countMeetingRoomList(MeetingRoomQuery param) {
		return meetingroomMapper.countMeetingRoomList(param);
	}

	
	@Override
	public Result<Object> chgdisabled(String id, boolean disable) {
		MeetingRoom meetingroom = new MeetingRoom();
		meetingroom.setRoomid(Integer.parseInt(id));
		if	(disable) {
			meetingroom.setDisabled("0");
		} else {
			meetingroom.setDisabled("1");
		}

		meetingroomMapper.chgdisabled(meetingroom);
		
		return Result.success();
	}

	
	@Override
	public Result<Object> addMeetingRoom(MeetingRoom meetingroom) {
		meetingroomMapper.addMeetingRoom(meetingroom);
		return Result.success();
	}

	@Override
	public Map<String, Object> uploadImage(String id, MultipartFile file, HttpServletRequest request) {
	
    	String prefix="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        System.out.println(2);
        try{
        	System.out.println(3);
            if(file!=null){
            	System.out.println(4);
                String originalName = file.getOriginalFilename();
                System.out.println(5);
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                System.out.println(6);
                String uuid = UUID.randomUUID()+"";
                System.out.println(7);
                String projectRootPath = System.getProperty("user.dir");
                System.out.println(8);
                String filepath = projectRootPath+"/upload/meetingroom/"+uuid+"." + prefix;
                //打印查看上传路径
                System.out.println(filepath);

                File files=new File(filepath);

                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }

                file.transferTo(files);

                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src",filepath);
                return map;
            }

        }catch (Exception e){
        	System.out.println(e);
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

	}

	
	@Override
	public MeetingRoom getMeetingRoomByid(String id) {
		
		return meetingroomMapper.getMeetingRoomById(id);
		
	}

	
	@Override
	public Result<Object> deleteMeetingRoom(String id) {
		
		// TODO 检查有没有预约会议，有预约的不能删除
		
		
		meetingroomMapper.deleteMeetingRoomByid(id);
		
		return Result.success("删除会议室成功！");
	}

	
	@Override
	public Result<Object> editMeetingRoom(MeetingRoom meetingroom) {
		
		meetingroomMapper.updateMeetingRoom(meetingroom);
		return Result.success("修改会议室成功！");
		
	}



	
//	@Override
//	public Result<Object> chgMenudisabled(String id, boolean disable) {
//		
//		Menu menu = new Menu();
//		menu.setSys_menu_id(Integer.parseInt(id));
//		if	(disable) {
//			menu.setDisabled("0");
//		} else {
//			menu.setDisabled("1");
//		}
//
//		menuMapper.chgMenudisabled(menu);
//		
//		return Result.success();
//	}
//	
//	
//	
//	@Override
//	public Menu getMenubyid(String id) {
//		Menu menu = menuMapper.getMenubyid(id);
//		menu.setSys_menu_id(Integer.parseInt(id));
//		return menu;
//	}
//	
//	
//	
//	@Override
//	public Result<Object> addMenu(Menu menu){
//		
//		String icon = menu.getIcon();
//		if (icon.startsWith("fa")) {
//			icon = "fa " + icon;			
//		} else if (icon.startsWith("layui-icon")) {
//			icon = "layui-icon " + icon;			
//		}
//		menu.setIcon(icon);
//		
//		menu.setDisabled("0");
//
//        menuMapper.addMenu(menu);
//		
//		return Result.success("新增菜单成功！");
//	}
//	
//	
//	
//	@Override
//	public Result<Object> editMenu(Menu menu){
//		
//		menu.setDisabled("0");
//
//        menuMapper.editMenu(menu);
//		
//		return Result.success("修改菜单成功！");
//	}
//	
//	
//	
//	@Override
//	public Result<Object> deleteMenu(String id) {
//		
//		Long i = menuMapper.countMenubypid(id);
//		
//		if (i>0) {
//			return Result.fail("请先删除子菜单！");
//		}
//		
//		menuMapper.deleteRoleMenubyMenu(id);
//		menuMapper.deleteMenu(id);
//		
//		return Result.success("删除菜单成功！");
//	}

	


//	
//	
//	@Override
//	public Result<Object> addRole(Role role){
//
////        System.out.println(role);
//        role.setDisabled("0");
//		roleMapper.addRole(role);
//		
//		return Result.success("新增角色成功！");
//	}
	
	
	
	
//	
//	
//	
//	@Override
//    public Result<Object> addUser(User user) {
//		
//		UserQuery param = new UserQuery();
//		param.setUsername(user.getUsername());
//		if (countUserList(param)>0) {
//			return Result.fail("该用户已存在！");
//		}
//		
//		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		user.setPassword(passwordEncoder.encode("123456"));
//     
//        user.setCreate_time(new Date());
//        
//        user.setDisabled("0");
//        
//		userMapper.addUser(user);
//		
//		return Result.success("新增用户成功！");
//    }
//	
//	
//	@Override
//    public Result<Object> chgUserdisabled(String user_id, boolean disable) {
//		
//		User user = new User();
//		user.setUser_id(Integer.parseInt(user_id));
//		if	(disable) {
//			user.setDisabled("0");
//		} else {
//			user.setDisabled("1");
//		}
//
//		userMapper.chgUserdisabled(user);
//		
//		return Result.success();
//    }
//	
//	
//	@Override
//    public Result<Object> rstpwd(String user_id) {
//		User user = new User();
//		user.setUser_id(Integer.parseInt(user_id));
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		user.setPassword(passwordEncoder.encode("123456"));
//		userMapper.rstpwd(user);
//    	return Result.success();
//    }

}
