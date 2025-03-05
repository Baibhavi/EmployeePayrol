package com.EmployeePayroll.controller;

import com.EmployeePayroll.model.Employee;
import com.EmployeePayroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //curl localhost:8080/employeepayrollservice/ -w "\n"
    //http:localhost:8080/employee/findall
    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //curl localhost:8080/employeepayrollservice/get/1 -w "\n"
    //http:localhost:8080/employee/findallgetbyid/1
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //curl -X POST -H "Content-Type: application/json" -d '{"name": "Lisa","salary":
    //2000}' "http://localhost:8080/employeepayrollservice/create" -w "\n"
    //http:localhost:8080/employee/create/post
    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //curl -X PUT -H "Content-Type: application/json" -d '{"name": "Lisa","salary": 2000}'
    //"http://localhost:8080/employeepayrollservice/update" -w "\n"
    //http:localhost:8080/employee/update/1
    @PutMapping("update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            return ResponseEntity.ok(employeeRepository.save(employee));
        }
        return ResponseEntity.notFound().build();
    }

    //http:localhost:8080/employee/delete/1
    // curl -X DELETE -H "Content-Type: application/json"
    //localhost:8080/employeepayrollservice/delete/1 -w "\n"
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}