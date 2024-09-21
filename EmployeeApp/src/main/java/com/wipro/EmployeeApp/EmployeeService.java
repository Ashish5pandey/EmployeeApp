package com.wipro.EmployeeApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Add Employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // View All Employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Update Employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    // Delete Employee
    public boolean deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Search Employee by ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}

