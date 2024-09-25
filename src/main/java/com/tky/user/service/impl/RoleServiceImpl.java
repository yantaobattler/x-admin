package com.tky.user.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.tky.common.vo.Result;
import com.tky.user.entity.Menu;
import com.tky.user.entity.Role;
import com.tky.user.entity.User;
import com.tky.user.mapper.MenuMapper;
import com.tky.user.mapper.RoleMapper;
import com.tky.user.mapper.UserMapper;
import com.tky.user.service.RoleService;
import com.tky.user.vo.RoleQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private MenuMapper menuMapper;


	@Override
	public List<Role> getRoleList(RoleQuery param) {
		return roleMapper.getRoleList(param);
	}

	@Override
	public Long countRoleList(RoleQuery param) {
		return roleMapper.countRoleList(param);
	}
	
	
	@Override
	public Result<Object> addRole(Role role){

//        System.out.println(role);
        role.setDisabled("0");
		roleMapper.addRole(role);
		
		return Result.success("新增角色成功！");
	}

	
	@Override
	public Result<Object> editroleuserByuser(String roletree, String usertree) {
		
		JSONArray data = JSON.parseArray(roletree);
		ArrayList<Integer> role_ids = new ArrayList<Integer>();
		for (int i = 0; i < data.size(); i++) {
			JSONObject obj = JSON.parseObject(data.getString(i));
			role_ids.add(obj.getInteger("id"));
		}
				
		JSONArray data1 = JSON.parseArray(usertree);
		ArrayList<Integer> user_ids = new ArrayList<Integer>();
		for (int i = 0; i < data1.size(); i++) {
			JSONObject obj = JSON.parseObject(data1.getString(i));
			user_ids.add(obj.getInteger("id"));
		}
		
//		System.out.println(role_ids);
//		System.out.println(user_ids);
		
		for (int user_id : user_ids) {
			roleMapper.deleteroleuserByuser(user_id);
			for (int role_id : role_ids) {
				roleMapper.addroleuser(user_id, role_id);
			}
		}
		
		return Result.success("用户角色调整成功！");
	}

	@Override
	public List<?> getusertreeByrole(int role_id) {
		List<Object> usertree = new ArrayList();
		List <?> myusers = roleMapper.getmyusers(role_id);
		List <User> allusers = userMapper.getAllUser();
		for (User user : allusers) {
			Map<String,Object> tmp = new HashMap<>();
			tmp.put("title", user.getChinesename());
			tmp.put("id", user.getUser_id());
			if ( myusers.contains(user.getUser_id())) {
				tmp.put("checked", true);
			} 
			usertree.add(tmp);
		}
		
    	return usertree;
	}

	@Override
	public List<?> getroletreeByrole(int role_id) {
		List<Object> roletree = new ArrayList();
		List <Role> allroles = roleMapper.getAllRoles();
		for (Role role : allroles) {
			Map<String,Object> tmp = new HashMap<>();
			tmp.put("title", role.getRole_name());
			tmp.put("id", role.getRole_id());
			if ( role_id == role.getRole_id()) {
				tmp.put("checked", true);
			} else {
				tmp.put("disabled", true);
			}
			roletree.add(tmp);
		}
		
    	return roletree;
	}
	
	
	@Override
	public Result<Object> editroleuserByrole(String roletree, String usertree) {
		
		JSONArray data = JSON.parseArray(roletree);
		ArrayList<Integer> role_ids = new ArrayList<Integer>();
		for (int i = 0; i < data.size(); i++) {
			JSONObject obj = JSON.parseObject(data.getString(i));
			role_ids.add(obj.getInteger("id"));
		}
				
		JSONArray data1 = JSON.parseArray(usertree);
		ArrayList<Integer> user_ids = new ArrayList<Integer>();
		for (int i = 0; i < data1.size(); i++) {
			JSONObject obj = JSON.parseObject(data1.getString(i));
			user_ids.add(obj.getInteger("id"));
		}
		
//		System.out.println(role_ids);
//		System.out.println(user_ids);
		
		for (int role_id : role_ids) {
			roleMapper.deleteroleuserByrole(role_id);
			for (int user_id : user_ids) {
				roleMapper.addroleuser(user_id, role_id);
			}
		}
		
		return Result.success("用户角色调整成功！");
	}
	

	@Override
	public List<?> getmenutreeByrole(int role_id) {
		roleMapper.getmymenus(role_id);
		List <Menu> allmenus = menuMapper.getAllMenus();
		List <?> mymenus = roleMapper.getmymenus(role_id);
		List<Map<String,Object>> tmplist1 = new ArrayList();
		List<Integer> pidlist = new ArrayList();
		for (Menu menu : allmenus) {
			Map<String,Object> tmp = new HashMap<>();
			tmp.put("title", menu.getTitle());
			tmp.put("id", menu.getSys_menu_id());
			tmp.put("pid", menu.getPid());
			pidlist.add(menu.getPid());
//			if (childrenmap.containsKey(menu.getPid())) {
//				childrenmap.get(menu.getPid()).add(tmp);				
//			} else {
//				childrenmap.put(menu.getPid(), new ArrayList());
//				childrenmap.get(menu.getPid()).add(tmp);	
//			}
//			if ( mymenu.contains(menu.getSys_menu_id()) && !childrenmap.containsKey(tmp.get("id"))) {
//				tmp.put("checked", true);
//			} 
			tmplist1.add(tmp);
		}
		
		for (int i = 0; i < tmplist1.size(); i++) {
			if (mymenus.contains(tmplist1.get(i).get("id")) && !pidlist.contains(tmplist1.get(i).get("id"))) {
				tmplist1.get(i).put("checked", true);
			}					
		}
		
//		
//		System.out.println(tmplist1);

		
		List<Map<String, Object>> tmplist2 = createTree(tmplist1);
//		System.out.println(tmplist2);
		
    	return tmplist2;
	}
	

	

	@Override
	public Result<Object> editrolemenuByrole(String roletree, String menutree) {
		
		JSONArray data = JSON.parseArray(roletree);
		ArrayList<Integer> role_ids = new ArrayList<Integer>();
		for (int i = 0; i < data.size(); i++) {
			JSONObject obj = JSON.parseObject(data.getString(i));
			role_ids.add(obj.getInteger("id"));
		}
		
		List<Map<String,Object>> menulist = changeTreetoList(JSON.parseArray(menutree, Map.class));
		ArrayList<Integer> menu_ids = new ArrayList<Integer>();
		for (Map<String,Object> each : menulist) {
			menu_ids.add((Integer) each.get("id"));
		}
		
		for (int role_id : role_ids) {
			roleMapper.deleterolemenuByrole(role_id);
			for (int menu_id : menu_ids) {
				roleMapper.addrolemenu(menu_id, role_id);
			}
		}
		
		return Result.success("角色菜单调整成功！");

	}
	
	
	
	
	
	
	
	//构造树形结构
    public static List<Map<String,Object>> createTree(List<Map<String,Object>> sourceList){
        List<Map<String,Object>> list = new ArrayList<>();
        for (Map<String,Object> menu : sourceList) {
            //找到根节点进行处理，找下一级节点
            if(0 == (int) menu.get("pid")){
                //把所有的根节点放到一个list
                list.add(findChildren(menu,sourceList));                
            }
        }
        return list;
    }

    public static Map<String,Object> findChildren(Map<String,Object> rootMenu,List<Map<String,Object>> list){
        //这个方法是在找rootMenu的所有子节点,然后返回rootMenu
        for (Map<String,Object> menu : list) {
            if(rootMenu.get("id").equals(menu.get("pid"))){
                if(rootMenu.get("children") == null){
                    rootMenu.put("children", new ArrayList<>());
                }
                // 把这个节点menu作为新的根节点继续向下构造树,再把构造的结果作为rootMenu的子节点
                ((ArrayList<Object>) rootMenu.get("children")).add(findChildren(menu,list));
            }
        }
        return rootMenu;
    }
    
    
    public static List<Map<String,Object>> changeTreetoList(List<Map> list2){
        List<Map<String,Object>> list = new ArrayList<>();
        for (Map<String,Object> menu : list2) {
        	
        	list.add(menu);
            
            if(menu.containsKey("children")){
            	List<Map<String,Object>> listc = changeTreetoList((List<Map>) menu.get("children"));
            	List<Map<String,Object>> listtmp = new ArrayList<>();
            	listtmp.addAll(list);
            	listtmp.addAll(listc);
            	list = listtmp;
            }
        }
        return list;
    }
    
    
    
  //构造树形结构
    public static List<Map<String,Object>> createTreeforInit(List<Map<String,Object>> sourceList){
        List<Map<String,Object>> list = new ArrayList<>();
        for (Map<String,Object> menu : sourceList) {
            //找到根节点进行处理，找下一级节点
            if(0 == (int) menu.get("pid")){
                //把所有的根节点放到一个list
                list.add(findChildforInit(menu,sourceList));                
            }
        }
        return list;
    }

    public static Map<String,Object> findChildforInit(Map<String,Object> rootMenu,List<Map<String,Object>> list){
        //这个方法是在找rootMenu的所有子节点,然后返回rootMenu
        for (Map<String,Object> menu : list) {
            if(rootMenu.get("sys_menu_id").equals(menu.get("pid"))){
                if(rootMenu.get("child") == null){
                    rootMenu.put("child", new ArrayList<>());
                }
                // 把这个节点menu作为新的根节点继续向下构造树,再把构造的结果作为rootMenu的子节点
                ((ArrayList<Object>) rootMenu.get("child")).add(findChildforInit(menu,list));
            }
        }
        return rootMenu;
    }

    
    
	@Override
	public JSONObject getInitmenu(User user) {
		
		Map<String,Object> homeInfo = new HashMap();
		homeInfo.put("title", "首页");
		homeInfo.put("href", "/welcome");
		
		Map<String,Object> logoInfo = new HashMap();
		logoInfo.put("title", "");
		logoInfo.put("image", "images/logo.png");
		logoInfo.put("href", "");
		
		
		Map<String,Object> resultMap = new HashMap();
		List<Menu> mymenu = menuMapper.getmymenubyuser(user.getUser_id());
		
		List<Map<String,Object>> mymenulist = new ArrayList<>();
		for (Menu eachmenu : mymenu) {
			Map<String,Object> tmp = new HashMap();
			tmp.put("sys_menu_id", eachmenu.getSys_menu_id());
			tmp.put("title", eachmenu.getTitle());
			tmp.put("href", eachmenu.getHref());
			tmp.put("icon", eachmenu.getIcon());
			tmp.put("pid", eachmenu.getPid());
			tmp.put("disabled", eachmenu.getDisabled());
			tmp.put("target", eachmenu.getTarget());
			tmp.put("sort", eachmenu.getSort());
			tmp.put("remark", eachmenu.getRemark());
			mymenulist.add(tmp);			
		}
		
		resultMap.put("menuInfo", createTreeforInit(mymenulist));
		resultMap.put("homeInfo", homeInfo);
		resultMap.put("logoInfo", logoInfo);

		JSONObject json = new JSONObject(resultMap);
		
		return json;
	}
    
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
