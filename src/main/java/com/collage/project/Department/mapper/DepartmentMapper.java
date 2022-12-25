package com.collage.project.Department.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.collage.project.Department.entity.Department;

@Mapper
public interface DepartmentMapper {

    public List<Department> findAll();

    public Department findById(int deptId);

    public List<Department> findByName(String deptName);

    public int nameCount(String name);

    public int relatedEmployeeCount(int deptId);

    public void save(Department department);

    public void update(Department department);

    public void delete(int deptId);
}
