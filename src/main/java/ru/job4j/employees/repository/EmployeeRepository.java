package ru.job4j.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.employees.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}