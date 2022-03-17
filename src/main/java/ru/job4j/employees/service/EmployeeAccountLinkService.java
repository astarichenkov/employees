package ru.job4j.employees.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.employees.domain.EmployeeAccountLink;
import ru.job4j.employees.repository.EmployeeAccountLinkRepository;

import java.util.List;

@Service
public class EmployeeAccountLinkService {
    private final EmployeeAccountLinkRepository accountLinks;

    public EmployeeAccountLinkService(EmployeeAccountLinkRepository accountLinks) {
        this.accountLinks = accountLinks;
    }

    public List<EmployeeAccountLink> findAllByEmployeeId(Long employeeId) {
        return accountLinks.findAllByEmployeeId(employeeId);
    }

    public List<EmployeeAccountLink> findAllByAccountId(Long accountId) {
        return accountLinks.findAllByAccountId(accountId);
    }

    @Transactional
    public void deleteByAccountId(Long accountId) {
        accountLinks.deleteAllByAccountId(accountId);
    }

    public void save(EmployeeAccountLink link) {
        accountLinks.save(link);
    }

}
