package com.tky.zhanpin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tky.zhanpin.entity.ZhanpinDept;
import com.tky.zhanpin.entity.ZhanpinDtl;
import com.tky.zhanpin.entity.Zhanpin;
import com.tky.zhanpin.mapper.ZhanpinDeptMapper;
import com.tky.zhanpin.mapper.ZhanpinDtlMapper;
import com.tky.zhanpin.mapper.ZhanpinMapper;
import com.tky.zhanpin.service.ZhanpinService;
import com.tky.zhanpin.vo.ZhanpinDtlQuery;
import com.tky.zhanpin.vo.ZhanpinQuery;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class ZhanpinServiceImpl implements ZhanpinService {
    @Resource
    private ZhanpinMapper zhanpinMapper;
    
    @Resource
    private ZhanpinDtlMapper zhanpindtlMapper;

    @Resource
    private ZhanpinDeptMapper zhanpindeptMapper;

    @Override
    public List<Zhanpin> getZhanpinList(ZhanpinQuery param) {
        return zhanpinMapper.getZhanpinList(param);
    }

    @Override
    public Long countZhanpinList(ZhanpinQuery param) {
        return zhanpinMapper.countZhanpinList(param);
    }

    @Override
    public void addZhanpin(Zhanpin zhanpin) {
        zhanpinMapper.addZhanpin(zhanpin);
        zhanpin.setZhanpinId(zhanpinMapper.getZhanpinIdByName(zhanpin.getName()));
        addZhanpinDtl(zhanpin);
    }

    @Override
    public List<ZhanpinDept> getAllDept() {
        return zhanpindeptMapper.getAllDept();
    }

    @Override
    public void deleteZhanpinByIds(String ids) {
        zhanpinMapper.deleteZhanpinByIds(ids);
    }

    @Override
    public Zhanpin getZhanpinById(Integer id) {
        return zhanpinMapper.getZhanpinById(id);
    }

    @Override
    public void updateZhanpin(Zhanpin zhanpin) {
        zhanpinMapper.updateZhanpin(zhanpin);
    }
    
    
    @Override
    public List<ZhanpinDtl> getZhanpinDtlList(ZhanpinDtlQuery param) {
        return zhanpindtlMapper.getZhanpinDtlList(param);
    }

    @Override
    public Long countZhanpinDtlList(ZhanpinDtlQuery param) {
        return zhanpindtlMapper.countZhanpinDtlList(param);
    }

    @Override
    public void addZhanpinDtl(Zhanpin zhanpin) {
    	zhanpindtlMapper.addZhanpinDtl(zhanpin);
    }
    
    
    
    @Override
    public Map<String, Object> uploadImage(MultipartFile file, HttpServletRequest request) {
    	System.out.println(1);
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
                String filepath = projectRootPath+"/upload/"+uuid+"." + prefix;
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
}
