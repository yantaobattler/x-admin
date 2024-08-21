package com.tky.emp.mapper;

import java.util.List;

import com.tky.emp.entity.Emp;
import com.tky.emp.vo.EmpQuery;

public interface EmpMapper {
    List<Emp> getEmpList(EmpQuery param);
    Long countEmpList(EmpQuery param);
    void addEmp(Emp emp);
    void deleteEmpByIds(String ids);
    Emp getEmpById(Integer id);
    void updateEmp(Emp emp);
}
