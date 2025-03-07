package com.EmployeePayroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class EmployeeDTO {
    public String name;
    public long salary;
    public EmployeeDTO(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }
    public String toString() {
        return "Name: " + name + ", Salary: " + salary;
    }
}
