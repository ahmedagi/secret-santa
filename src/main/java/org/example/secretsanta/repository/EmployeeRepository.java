package org.example.secretsanta.repository;

import org.example.secretsanta.model.Employee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT * FROM employee WHERE deleted = false")
    List<Employee> findAllByDeletedFalse();
}
