create table cloud.user_roles (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references cloud.users (id),
    foreign key (role_id) references cloud.roles (id)
);