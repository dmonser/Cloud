create table cloud.roles (
    id bigserial primary key,
    "name" varchar(50) not null unique
);