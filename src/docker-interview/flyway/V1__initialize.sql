Create table student
(
    id         bigserial primary key,
    name      varchar(255),
    mark      numeric(15,2),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
