package com.tky.zhanpin.mapper;

import java.util.List;

import com.tky.zhanpin.entity.Zhanpin;
import com.tky.zhanpin.vo.ZhanpinQuery;

public interface ZhanpinMapper {
    List<Zhanpin> getZhanpinList(ZhanpinQuery param);
    Long countZhanpinList(ZhanpinQuery param);
    void addZhanpin(Zhanpin zhanpin);
    void deleteZhanpinByIds(String ids);
    Zhanpin getZhanpinById(Integer id);
    void updateZhanpin(Zhanpin zhanpin);
    
    int getZhanpinIdByName(String name);
}
