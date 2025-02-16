package org.example.secretsanta.service;

import org.example.secretsanta.exception.EmployeeNotFoundException;
import org.example.secretsanta.model.Employee;
import org.example.secretsanta.repository.EmployeeRepository;
import org.example.secretsanta.repository.SecretSantaPairRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SecretSantaPairRepository secretSantaPairRepository;

    public EmployeeService(EmployeeRepository employeeRepository, SecretSantaPairRepository secretSantaPairRepository) {
        this.employeeRepository = employeeRepository;
        this.secretSantaPairRepository = secretSantaPairRepository;
    }

    public Iterable<Employee> getAllEmployees(Boolean includeDeleted) {
        if (includeDeleted) {
            return employeeRepository.findAllByOrderByDeletedAsc();
        }

        return employeeRepository.findAllByDeletedFalse();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("User not found."));
        boolean paired = secretSantaPairRepository.existsByGiverIdOrReceiverId(id);
        if (paired) {
            // Soft delete
            employee.setDeleted(true);
            employeeRepository.save(employee);
        } else {
            // Hard delete
            employeeRepository.deleteById(employee.getId());
        }
    }
}
