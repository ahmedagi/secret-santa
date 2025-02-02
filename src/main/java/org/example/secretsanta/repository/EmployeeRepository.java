package org.example.secretsanta.repository;

import org.example.secretsanta.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
