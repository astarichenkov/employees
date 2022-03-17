create table if not exists employee
(
    id          serial primary key not null,
    name        varchar(2000),
    lastname    varchar(2000),
    inn         varchar(20),
    hiring_date timestamp
);

create table if not exists employee_accounts
(
    id          serial primary key not null,
    employee_id int,
    account_id int
);
