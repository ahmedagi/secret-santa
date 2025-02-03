package org.example.secretsanta.service;

import org.example.secretsanta.model.Employee;
import org.example.secretsanta.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> getAllEmployees(Boolean includeDeleted) {
        if (includeDeleted) {
            return employeeRepository.findAll();
        }

        return employeeRepository.findAllByDeletedFalse();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }
}
