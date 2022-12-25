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

    // 获取所有部门
    public List<Department> getDepartments(){
        return departmentMapper.findAll();
    }

    // 根据名称获取部门
    public List<Department> getDepartmentsByName(String deptName){
        return departmentMapper.findByName(deptName);
    }

    // 根据部门名称获取数量
    public int getDepartmentsCountByName(String name){
        return departmentMapper.nameCount(name);
    }

    // 根据部门id获取关联员工数量
    public int getRelatedEmployeesCountById(int deptId){
        return departmentMapper.relatedEmployeeCount(deptId);
    }

    // 根据部门id获取部门
    public Department getDepartmentById(int deptId){
        return departmentMapper.findById(deptId);
    }

    // 添加部门
    public void addDepartment(Department department){
        departmentMapper.save(department);
    }

    // 更新部门
    public void updateDepartment(Department department){
        departmentMapper.update(department);
    }

    // 删除部门
    public void deleteDepartment(int deptId){
        departmentMapper.delete(deptId);
    }
}
