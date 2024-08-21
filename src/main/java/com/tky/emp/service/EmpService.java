package com.tky.emp.service;

import java.util.List;

import com.tky.emp.entity.Dept;
import com.tky.emp.entity.Emp;
import com.tky.emp.vo.EmpQuery;

public interface EmpService {
    List<Emp> getEmpList(EmpQuery param);
    Long countEmpList(EmpQuery param);
    void addEmp(Emp emp);

    List<Dept> getAllDept();

    void deleteEmpByIds(String ids);

    Emp getEmpById(Integer id);

    void updateEmp(Emp emp);
}
