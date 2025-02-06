package org.example.secretsanta.controller;

import org.apache.coyote.BadRequestException;
import org.example.secretsanta.model.Employee;
import org.example.secretsanta.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAllEmployees(
            @RequestParam(required = false) boolean includeDeleted,
            Model model
    ) {
        List<Employee> employees = (List<Employee>) employeeService.getAllEmployees(includeDeleted);
        model.addAttribute("employees", employees);
        model.addAttribute("activeTab", "employees");
        return "employees.html";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Employee createEmployee(@RequestBody Employee newEmployee) throws BadRequestException {
        final String name = newEmployee.getName();
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Name cannot be empty");
        } else if (name.length() > 64) {
            throw new BadRequestException("Maximum length is 64 characters");
        }

        return employeeService.createEmployee(newEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }
}
