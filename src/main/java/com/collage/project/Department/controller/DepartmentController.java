package com.collage.project.Department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // 根据查询参数获取部门
    @GetMapping
    public List<Department> getDepartments(@RequestParam(required = false) String deptName, @RequestParam(required = false) String name){
        if(deptName != null){
            deptName = String.format("%%%s%%", deptName); // 格式化用于 like 搜索
            return departmentService.getDepartmentsByName(deptName);
        }else{
            return departmentService.getDepartments();
        }
    }

    // 根据部门id获取部门
    @GetMapping(path = "{id}")
    public Department getDepartmentById(@PathVariable("id") int deptId) {
        return departmentService.getDepartmentById(deptId);
    }

    // 添加部门
    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody Department department) {
        // 判断部门重名
        if(departmentService.getDepartmentsCountByName(department.getDeptName()) == 0){
            departmentService.addDepartment(department);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("failed", HttpStatus.CONFLICT);
    }

    // 更新部门
    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateDepartment(@RequestBody Department department, @PathVariable("id") int deptId){
        // 判断部门重名
        if(departmentService.getDepartmentsCountByName(department.getDeptName()) == 0){
        departmentService.updateDepartment(department);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("failed", HttpStatus.CONFLICT);
    }

    // 删除部门
    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") int deptId){
        if(departmentService.getRelatedEmployeesCountById(deptId) == 0){
            departmentService.deleteDepartment(deptId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("failed", HttpStatus.CONFLICT);
    }
}
