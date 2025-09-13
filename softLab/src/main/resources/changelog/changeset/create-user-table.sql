-- changeset andrei:create-user-table

create table if not exists users(
    id bigint not null primary key unique,
    name varchar(255) not null unique,
    age int not null,
    rating float,
    role int not null
)