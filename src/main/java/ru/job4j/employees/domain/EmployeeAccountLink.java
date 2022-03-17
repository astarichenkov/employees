package ru.job4j.employees.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee_accounts")
public class EmployeeAccountLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long accountId;
    private Long employeeId;

    public EmployeeAccountLink(Long accountId, Long employeeId) {
        this.accountId = accountId;
        this.employeeId = employeeId;
    }
}