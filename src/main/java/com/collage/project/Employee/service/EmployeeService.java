package com.collage.project.Employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collage.project.Employee.entity.Employee;
import com.collage.project.Employee.mapper.EmployeeMapper;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    // 获取所有员工
    public List<Employee> getEmployees() {
        return employeeMapper.findAll();
    }

    // 根据姓名获取员工
    public List<Employee> getEmployeesByName(String name) {
        return employeeMapper.findByName(name);
    }

    // 根据部门id获取员工
    public List<Employee> getEmployeesByDeptId(int deptId) {
        return employeeMapper.findByDeptId(deptId);
    }

    // 根据姓名和部门id获取员工
    public List<Employee> getEmployeesByNameAndDeptId(String name, int deptId) {
        return employeeMapper.findByNameAndDeptId(name, deptId);
    }

    // 根据员工id获取员工
    public Employee getEmployeeById(int empId) {
        return employeeMapper.findById(empId);
    }
    
    // 添加员工
    public void addEmployee(Employee employee) {
        employeeMapper.save(employee);
    }

    // 更新员工
    public void updateEmployee(Employee employee){
        employeeMapper.update(employee);
    }

    // 删除员工
    public void deleteEmployee(int empId){
        employeeMapper.delete(empId);
    }
}
