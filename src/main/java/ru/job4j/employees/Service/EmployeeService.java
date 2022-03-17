package ru.job4j.employees.Service;

import org.springframework.stereotype.Service;
import ru.job4j.employees.domain.Employee;
import ru.job4j.employees.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employees;

    public EmployeeService(EmployeeRepository employees) {
        this.employees = employees;
    }

    public List<Employee> findAll() {
        return employees.findAll();
    }

}
