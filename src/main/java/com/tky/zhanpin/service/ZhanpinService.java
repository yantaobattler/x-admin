package com.tky.zhanpin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.tky.zhanpin.entity.ZhanpinDept;
import com.tky.zhanpin.entity.ZhanpinDtl;
import com.tky.zhanpin.entity.Zhanpin;
import com.tky.zhanpin.vo.ZhanpinDtlQuery;
import com.tky.zhanpin.vo.ZhanpinQuery;

public interface ZhanpinService {
    List<Zhanpin> getZhanpinList(ZhanpinQuery param);
    Long countZhanpinList(ZhanpinQuery param);
    void addZhanpin(Zhanpin zhanpin);

    List<ZhanpinDept> getAllDept();

    void deleteZhanpinByIds(String ids);

    Zhanpin getZhanpinById(Integer id);

    void updateZhanpin(Zhanpin zhanpin);
    

    List<ZhanpinDtl> getZhanpinDtlList(ZhanpinDtlQuery param);
    Long countZhanpinDtlList(ZhanpinDtlQuery param);
    void addZhanpinDtl(Zhanpin zhanpin);
    
    
    Map<String, Object> uploadImage(MultipartFile file, HttpServletRequest request);
}
