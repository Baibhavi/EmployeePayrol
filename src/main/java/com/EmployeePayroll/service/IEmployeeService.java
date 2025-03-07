package com.EmployeePayroll.service;

import com.EmployeePayroll.dto.EmployeeDTO;
import com.EmployeePayroll.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Optional<Employee> updateEmployee(Employee employeeDetails, Long id);

    boolean deleteEmployee(Long id);
}
