insert into cloud.roles ("name")
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into cloud.users (username, password, email)
values
('user', '$2a$10$4.sPD.GJWjBWs/9nbaO/AOy3RKGi2wk0CEqrex63zW94VbGfV1qEK', 'user@mail.ru'),
('admin', '$2a$10$/QIRBWcY/7gIXTaCKsHbTOYvTW6fVyw4AC1tBmtkp1AYI4hFuKiwa', 'admin@mail.ru');

insert into cloud.user_roles (user_id, role_id)
values
(1, 1),
(2, 2);