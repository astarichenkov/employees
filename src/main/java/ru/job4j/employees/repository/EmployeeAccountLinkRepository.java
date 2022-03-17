package ru.job4j.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.employees.domain.EmployeeAccountLink;

import java.util.List;

@Repository
public interface EmployeeAccountLinkRepository extends JpaRepository<EmployeeAccountLink, Long> {

    List<EmployeeAccountLink> findAllByEmployeeId(Long employeeId);

    List<EmployeeAccountLink> findAllByAccountId(Long accountId);

    void deleteAllByAccountId(Long accountId);

}
