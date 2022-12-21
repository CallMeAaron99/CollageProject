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

    public List<Employee> getEmployees() {
        return employeeMapper.findAll();
    }

    public List<Employee> getEmployeesByName(String name) {
        return employeeMapper.findByName(name);
    }

    public List<Employee> getEmployeesByDeptId(int deptId) {
        return employeeMapper.findByDeptId(deptId);
    }

    public List<Employee> getEmployeesByNameAndDeptId(String name, int deptId) {
        return employeeMapper.findByNameAndDeptId(name, deptId);
    }

    public Employee getEmployeeById(int empId) {
        return employeeMapper.findById(empId);
    }
    
    public void addEmployee(Employee employee) {
        employeeMapper.save(employee);
    }
    
    public void updateEmployee(Employee employee){
        employeeMapper.update(employee);
    }

    public void deleteEmployee(int empId){
        employeeMapper.delete(empId);
    }
}
