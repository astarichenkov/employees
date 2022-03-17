package ru.job4j.employees.domain;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Report {
    private int id;

    private String name;

    private Timestamp created;

    private Person person;

    public static Report of(int id, String name, Person person) {
        Report r = new Report();
        r.id = id;
        r.name = name;
        r.person = person;
        r.created = new Timestamp(System.currentTimeMillis());
        return r;
    }
}