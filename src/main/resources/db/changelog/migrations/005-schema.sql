create table cloud.files (
    id bigserial primary key not null ,
    owner bigserial not null,
    "name" varchar(30) not null,
    original_name varchar(100) not null,
    size bigserial not null,
    content_type varchar(20) not null,
    bytes bytea not null,
    foreign key (owner) references cloud.users ("id")
)