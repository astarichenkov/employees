package ru.job4j.employees.Service;

import org.springframework.stereotype.Service;
import ru.job4j.employees.repository.EmployeePersonRepository;

@Service
public class EmployeePersonService {

    private final EmployeePersonRepository employees;

    public EmployeePersonService(EmployeePersonRepository employees) {
        this.employees = employees;
    }
}
