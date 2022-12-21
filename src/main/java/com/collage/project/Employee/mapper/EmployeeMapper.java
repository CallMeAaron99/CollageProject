package com.collage.project.Employee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.collage.project.Employee.entity.Employee;

@Mapper
public interface EmployeeMapper {

    public List<Employee> findAll();

    public Employee findById(int empId);

    public List<Employee> findByName(String name);

    public List<Employee> findByDeptId(int deptId);

    public List<Employee> findByNameAndDeptId(@Param("name") String name, @Param("deptId") int deptId);

    public void save(Employee employee);

    public void update(Employee employee);

    public void delete(int empId);
}
