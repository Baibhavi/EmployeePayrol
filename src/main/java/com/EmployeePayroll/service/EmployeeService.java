package com.EmployeePayroll.service;

import com.EmployeePayroll.model.Employee;
import com.EmployeePayroll.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    //Get employee by id
    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }
    //create employee
    public Employee crateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    //update employee details
    public Optional<Employee> updateEmployee(Employee employeeDetails, Long id){
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(employeeDetails.getName());
            employee.setSalary(employeeDetails.getSalary());
            return employeeRepository.save(employee);
        });
    }
    //delete user
    public boolean deleteEmployee(Long id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
