package com.tky.zhanpin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tky.common.vo.Result;
import com.tky.zhanpin.entity.ZhanpinDept;
import com.tky.zhanpin.entity.ZhanpinDtl;
import com.tky.zhanpin.entity.Zhanpin;
import com.tky.zhanpin.service.ZhanpinService;
import com.tky.zhanpin.vo.ZhanpinDtlQuery;
import com.tky.zhanpin.vo.ZhanpinQuery;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/zhanpin")
public class ZhanpinController {

    @Autowired
    private ZhanpinService zhanpinService;

    @GetMapping("")
    public String toZhanpinListUI(){
        return "zhanpin/zhanpinList";
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<Object> getZhanpinList(ZhanpinQuery param){
        List<Zhanpin> list = zhanpinService.getZhanpinList(param);
        Long count = zhanpinService.countZhanpinList(param);
        return Result.success(list,count);
    }

    @PostMapping("")
    @ResponseBody
    public Result<Object> addZhanpin(Zhanpin zhanpin){
        zhanpinService.addZhanpin(zhanpin);
        return Result.success("新增展品记录成功！");
    }

    @GetMapping("/add/ui")
    public String toAddUI(Model model){
        List<ZhanpinDept> deptList = zhanpinService.getAllDept();
        model.addAttribute("deptList",deptList);
        return "zhanpin/zhanpinAdd";
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> deleteZhanpinByIds(@PathVariable("ids") String ids){
        zhanpinService.deleteZhanpinByIds(ids);
        return Result.success("删除展品成功！");
    }

    @GetMapping("/{id}")
    public String getZhanpinById(@PathVariable("id") Integer id,Model model){
        Zhanpin zhanpin = zhanpinService.getZhanpinById(id);
        model.addAttribute("zhanpin",zhanpin);
        model.addAttribute("deptList",zhanpinService.getAllDept());
        return "zhanpin/zhanpinEdit";
    }
    
    
    @GetMapping("/dtlid/{id}")
    public String getZhanpinDtlById(@PathVariable("id") Integer id,Model model){
        ZhanpinDtl zhanpindtl = zhanpinService.getZhanpinDtlById(id);
        model.addAttribute("zhanpindtl",zhanpindtl);
        model.addAttribute("deptList",zhanpinService.getAllDept());
        return "zhanpin/zhanpinDtlEdit";
    }
    
    
    @PutMapping("/zhanpin/updatedtl")
    @ResponseBody
    public Result<Object> updateZhanpinDtl(ZhanpinDtl zhanpindtl){
        zhanpinService.updateZhanpindtl(zhanpindtl);
        zhanpinService.updateZhanpinByLastDtl(zhanpindtl);
        return Result.success("展品调整明细信息修改成功！");
    }
    
    
    @DeleteMapping("/deletedtlpre/{id}")
    @ResponseBody
    public Result<Object> deleteZhanpinDtlpre(@PathVariable("id") int id){       
        return zhanpinService.deleteZhanpinDtlpre(id);
    }
    
    
    
    @DeleteMapping("/deletedtlandinfo/{id}")
    @ResponseBody
    public Result<Object> deleteZhanpinDtlandinfo(@PathVariable("id") int id){
        zhanpinService.deleteZhanpinDtlandinfo(id);
//    	System.out.println(id);
        return Result.success("删除展品成功！");
    }
    
    
    
    
    

    @PutMapping("")
    @ResponseBody
    public Result<Object> updateZhanpin(Zhanpin zhanpin){
        zhanpinService.updateZhanpin(zhanpin);
        zhanpinService.addZhanpinDtl(zhanpin);
        return Result.success("展品信息修改成功！");
    }
    
    
    @GetMapping("/check/{id}")
    public String getZhanpinCheckById(@PathVariable("id") Integer id,Model model){
        Zhanpin zhanpin = zhanpinService.getZhanpinById(id);
        model.addAttribute("zhanpin",zhanpin);
        model.addAttribute("deptList",zhanpinService.getAllDept());
        return "zhanpin/zhanpinCheck";
    }
    
    @GetMapping("/dtl/{id}")
    @ResponseBody
    public Result<Object> getZhanpinDtl(@PathVariable("id") Integer id, ZhanpinDtlQuery param){
    	
    	param.setZhanpinId(id);
        List<ZhanpinDtl> list = zhanpinService.getZhanpinDtlList(param);
        Long count = zhanpinService.countZhanpinDtlList(param);
        return Result.success(list,count);
    }
    
    
    @PostMapping("/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(MultipartFile file,HttpServletRequest request){
        Map<String, Object> map = zhanpinService.uploadImage(file, request);
        return map;
    }
}
