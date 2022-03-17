create table if not exists employee (
                          id serial primary key not null,
                          name varchar(2000),
                          lastname varchar(2000),
                          inn varchar(20),
                          hiring_date timestamp
);

create table if not exists person (
                        id serial primary key not null,
                        login varchar(2000),
                        password varchar(2000)
);

create table if not exists employee_person (
                        employee_id int references employee (id) on delete cascade,
                        person_id int not null
)
