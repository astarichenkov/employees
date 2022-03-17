package ru.job4j.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.employees.domain.Account;
import ru.job4j.employees.domain.Report;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportsController {
    @Autowired
    private RestTemplate rest;

    private static final String API = "http://localhost:8080/person/";

    private static final String API_ID = "http://localhost:8080/person/{id}";

    @GetMapping("/")
    public List<Report> findAll() {
        List<Report> rsl = new ArrayList<>();
        List<Account> accounts = rest.exchange(
                API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() { }
        ).getBody();
        for (Account account : accounts) {
            Report report = Report.of(1, "First", account);
            rsl.add(report);
        }
        return rsl;
    }

    @PostMapping("/")
    public ResponseEntity<Account> create(@RequestBody Account account) {
        Account rsl = rest.postForObject(API, account, Account.class);
        return new ResponseEntity<>(
                rsl,
                HttpStatus.CREATED
        );
    }
}