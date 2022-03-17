package ru.job4j.employees.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    private Long id;
    private String login;
    private String password;
}