package ru.job4j.employees.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.employees.service.EmployeeAccountLinkService;
import ru.job4j.employees.service.EmployeeService;
import ru.job4j.employees.service.AccountService;
import ru.job4j.employees.domain.Employee;
import ru.job4j.employees.domain.Account;
import ru.job4j.employees.domain.EmployeeAccountLink;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class EmployeeController {

    private final EmployeeService employees;
    private final AccountService accounts;
    private final EmployeeAccountLinkService links;

    public EmployeeController(EmployeeService employees, AccountService accounts, EmployeeAccountLinkService links) {
        this.employees = employees;
        this.accounts = accounts;
        this.links = links;
    }

    @GetMapping("/employee")
    public List<Employee> findAll() {
        List<Employee> employeeList = employees.findAll();
        employeeList.forEach(this::addAccountsToEmployee);
        return employeeList;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        var opt = this.employees.findById(id);
        Employee employee = new Employee();
        if (opt.isPresent()) {
            employee = opt.get();
            addAccountsToEmployee(employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employee/create")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(
                this.employees.save(employee),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/employee/{id}/account/create")
    public ResponseEntity<Account> accountCreate(@RequestBody Account account, @PathVariable Long id) {
        Optional<Employee> opt = employees.findById(id);
        if (opt.isPresent()) {
            Employee employee = opt.get();
            Account createdAccount = this.accounts.save(account);
            links.save(new EmployeeAccountLink(createdAccount.getId(), employee.getId()));
            return new ResponseEntity<>(
                    createdAccount,
                    HttpStatus.CREATED
            );
        } else {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/account")
    public List<Account> findAllAccounts() {
        return accounts.findAll();
    }

    @DeleteMapping("/account/delete/{id}")
    public ResponseEntity<Void> accountDelete(@PathVariable Long id) {
        try {
            accounts.delete(id);
            links.deleteByAccountId(id);
            return new ResponseEntity<>(
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/account/update/{id}")
    public ResponseEntity<Void> update(@RequestBody Account account) {
        this.accounts.update(account);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> findByAccountById(@PathVariable Long id) {
        var account = this.accounts.findById(id);
        return new ResponseEntity<Account>(
                account,
                account != null ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    public void addAccountsToEmployee(Employee employee) {
        List<EmployeeAccountLink> accountLinks = links.findAllByEmployeeId(employee.getId());
        List<Account> accountList = new ArrayList<>();
        accountLinks.forEach(e -> accountList.add(accounts.findById(e.getAccountId())));
        employee.setAccounts(accountList);
    }
}
