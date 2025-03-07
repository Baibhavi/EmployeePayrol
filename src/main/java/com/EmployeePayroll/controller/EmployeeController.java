package com.EmployeePayroll.controller;

import com.EmployeePayroll.dto.EmployeeDTO;
import com.EmployeePayroll.dto.ResponseDTO;
import com.EmployeePayroll.model.Employee;
import com.EmployeePayroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getEmployeeData() {
        // Fetch all employees from the repository
        List<Employee> empData = employeeRepository.findAll();
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", empData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getEmployeeData(@PathVariable Long id) {
        Optional<Employee> empData = employeeRepository.findById(id);
        if (empData.isPresent()) {
            ResponseDTO respDTO = new ResponseDTO("Get Call Successful", empData.get());
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO("Employee Not Found", null), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addEmployee(@RequestBody EmployeeDTO empDTO) {
        Employee empData = new Employee(empDTO);
        // Save the new employee to the database (this will auto-generate the ID)
        employeeRepository.save(empData);
        ResponseDTO respDTO = new ResponseDTO("Create Employee Payroll Data Successfully", empData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO empDTO) {
        Optional<Employee> empData = employeeRepository.findById(id);
        if (empData.isPresent()) {
            Employee employee = empData.get();
            employee.setName(empDTO.getName());
            employee.setSalary(empDTO.getSalary());
            // Update the employee record in the database
            employeeRepository.save(employee);
            ResponseDTO respDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", employee);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO("Employee Not Found", null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            ResponseDTO respDTO = new ResponseDTO("Deleted Successfully", "Delete id: " + id);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO("Employee Not Found", null), HttpStatus.NOT_FOUND);
        }
    }
}
