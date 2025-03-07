package com.EmployeePayroll.service;

import com.EmployeePayroll.model.Employee;
import com.EmployeePayroll.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //Get employee by id
    @Override
    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    //create employee
    @Override
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    //update employee details
    @Override
    public Optional<Employee> updateEmployee(Employee employeeDetails, Long id){
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(employeeDetails.getName());
            employee.setSalary(employeeDetails.getSalary());
            return employeeRepository.save(employee);
        });
    }
    //delete user
    @Override
    public boolean deleteEmployee(Long id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
