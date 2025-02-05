package org.example.secretsanta.service;

import org.example.secretsanta.exception.EmployeeNotFoundException;
import org.example.secretsanta.model.Employee;
import org.example.secretsanta.repository.EmployeeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("User not found."));
        try {
            // Attempt hard delete first
            employeeRepository.deleteById(employee.getId());
        } catch (DataIntegrityViolationException e) {
            // Soft delete
            employee.setDeleted(true);
            employeeRepository.save(employee);
        }
    }
}
