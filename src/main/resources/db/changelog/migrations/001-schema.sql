create table cloud.users (
    id bigserial primary key,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email varchar(50) not null unique
);

create table cloud.roles (
    id serial primary key,
    "name" varchar(50) not null
);

create table user_roles (
    user_id bigint not null primary key references cloud.users (id),
    role_id int not null primary key references cloud.roles (id)
);

insert into cloud.roles ("name")
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email)
values
('user', '$2a$10$bOnrecBuSoR4WF0gDSXXYOz7aZLNw4FxLMvl6uYm0NCtWMLEs.h..', 'user@mail.ru'),
('admin', '$2a$10$0KqCA8qoE.jmZIZeT17xPOxOpUWS9aM0ES790aKuyNrWl42TUaxVW', 'admin@mail.ru');

insert into cloud.user_roles (user_id, role_id)
values
(1, 1),
(2, 2);