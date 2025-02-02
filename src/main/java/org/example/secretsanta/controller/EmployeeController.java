package org.example.secretsanta.controller;

import org.example.secretsanta.model.Employee;
import org.example.secretsanta.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee newEmployee) {
        return employeeService.createEmployee(newEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }
}
