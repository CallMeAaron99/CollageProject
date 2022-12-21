package com.collage.project.Employee.controller;

import java.util.Date;
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

import com.collage.project.Employee.entity.Employee;
import com.collage.project.Employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(@RequestParam(required = false) String name, @RequestParam(required = false, defaultValue = "0") int deptId) {
        if(name!=null || deptId != 0){ // 有查询参数
            if(name!=null && deptId != 0){ // 姓名和部门
                name = String.format("%%%s%%", name); // 格式化用于 like 搜索
                return employeeService.getEmployeesByNameAndDeptId(name, deptId);
            }else if(deptId != 0){ // 只有部门
                return employeeService.getEmployeesByDeptId(deptId);
            }else{ // 只有姓名
                name = String.format("%%%s%%", name);
                return employeeService.getEmployeesByName(name);
            }
        }else{ // 无查询参数
            return employeeService.getEmployees();
        }
    }

    @GetMapping(path = "{id}")
    public Employee getEmployeeById(@PathVariable("id") int empId) {
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employee.setHireDate(new Date());
        employeeService.addEmployee(employee);
    }

    @PutMapping(path = "{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable("id") int empId){
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmployee(@PathVariable("id") int empId){
        employeeService.deleteEmployee(empId);
    }
}
