package com.collage.project.Department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collage.project.Department.entity.Department;
import com.collage.project.Department.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    
    private final DepartmentService departmentService;
    
    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getDepartments(@RequestParam(required = false) String deptName, @RequestParam(required = false) String name){
        if(deptName != null){
            deptName = String.format("%%%s%%", deptName); // 格式化用于 like 搜索
            return departmentService.getDepartmentsByName(deptName);
        }else if(name != null){
            return departmentService.getDepartmentsBySpecificName(name);
        }else{
            return departmentService.getDepartments();
        }
    }

    @GetMapping(path = "{id}")
    public Department getDepartmentById(@PathVariable("id") int deptId) {
        return departmentService.getDepartmentById(deptId);
    }

    @PostMapping
    public void addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
    }

    @PutMapping(path = "{id}")
    public void updateDepartment(@RequestBody Department department, @PathVariable("id") int deptId){
        departmentService.updateDepartment(department);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDepartment(@PathVariable("id") int deptId){
        departmentService.deleteDepartment(deptId);
    }
}
