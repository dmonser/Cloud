create table cloud.users (
    id bigserial primary key,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email varchar(50) not null unique
);