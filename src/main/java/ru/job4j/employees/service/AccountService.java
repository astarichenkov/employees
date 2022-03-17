package ru.job4j.employees.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.employees.domain.Account;

import java.util.List;

@Service
public class AccountService {
    private final RestTemplate rest;

    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";
    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    public AccountService(RestTemplate rest) {
        this.rest = rest;
    }

    public List<Account> findAll() {
        return rest.exchange(
                API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
                }
        ).getBody();
    }

    public Account findById(Long id) {
        Account account = null;
        try {
            account = rest.getForObject(API_ID, Account.class, id);
        } catch (Throwable e) {
            LOG.error("Не удалось получить аккаунт");
        }
        return account;
    }

    public Account save(Account account) {
        try {
            account = rest.postForObject(API, account, Account.class);
        } catch (Throwable e) {
            LOG.error("Не удалось создать аккаунт");
        }
        return account;
    }

    public void delete(Long id) {
        try {
            rest.delete(API_ID, id);
        } catch (Throwable e) {
            LOG.error("Не удалось удалить аккаунт");
        }
    }

    public void update(Account account) {
        try {
            rest.put(API, account);
        } catch (Throwable e) {
            LOG.error("Не удалось обновить аккаунт");
        }
    }
}
