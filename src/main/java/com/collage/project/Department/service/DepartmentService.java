package com.collage.project.Department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collage.project.Department.entity.Department;
import com.collage.project.Department.mapper.DepartmentMapper;

@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper){
        this.departmentMapper = departmentMapper;
    }

    public List<Department> getDepartments(){
        return departmentMapper.findAll();
    }

    public List<Department> getDepartmentsByName(String deptName){
        return departmentMapper.findByName(deptName);
    }

    public List<Department> getDepartmentsBySpecificName(String name){
        return departmentMapper.findBySpecificName(name);
    }

    public Department getDepartmentById(int deptId){
        return departmentMapper.findById(deptId);
    }

    public void addDepartment(Department department){
        departmentMapper.save(department);
    }

    public void updateDepartment(Department department){
        departmentMapper.update(department);
    }

    public void deleteDepartment(int deptId){
        departmentMapper.delete(deptId);
    }
}
