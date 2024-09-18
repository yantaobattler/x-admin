package com.tky.zhanpin.mapper;

import java.util.List;

import com.tky.zhanpin.entity.Zhanpin;
import com.tky.zhanpin.entity.ZhanpinDtl;
import com.tky.zhanpin.vo.ZhanpinDtlQuery;

public interface ZhanpinDtlMapper {
    List<ZhanpinDtl> getZhanpinDtlList(ZhanpinDtlQuery param);
    Long countZhanpinDtlList(ZhanpinDtlQuery param);
    void addZhanpinDtl(Zhanpin zhanpin);
    
    void deleteZhanpinDtlByIds(String ids);
    ZhanpinDtl getZhanpinDtlById(Integer id);
    void updateZhanpinDtl(ZhanpinDtl zhanpindtl);
	ZhanpinDtl getlastDtl(Integer zhanpinId);
	Long countZhanpinDtl(Integer id);
	void deleteZhanpinDtlByid(Integer id);
	Long countZhanpinDtlbyzhanpin(Integer zhanpinId);

}
