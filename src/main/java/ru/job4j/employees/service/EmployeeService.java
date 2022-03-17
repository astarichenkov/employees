package ru.job4j.employees.service;

import org.springframework.stereotype.Service;
import ru.job4j.employees.domain.Employee;
import ru.job4j.employees.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employees;

    public EmployeeService(EmployeeRepository employees) {
        this.employees = employees;
    }

    public List<Employee> findAll() {
        return employees.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employees.findById(id);
    }

    public Employee save(Employee employee) {
        return employees.save(employee);
    }

}
