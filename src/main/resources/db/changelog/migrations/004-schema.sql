insert into cloud.roles ("name")
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into cloud.users (username, password, email)
values
('user', '$2a$10$bOnrecBuSoR4WF0gDSXXYOz7aZLNw4FxLMvl6uYm0NCtWMLEs.h..', 'user@mail.ru'),
('admin', '$2a$10$0KqCA8qoE.jmZIZeT17xPOxOpUWS9aM0ES790aKuyNrWl42TUaxVW', 'admin@mail.ru');

insert into cloud.user_roles (user_id, role_id)
values
(1, 1),
(2, 2);