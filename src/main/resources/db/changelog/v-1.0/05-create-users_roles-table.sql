create table users_roles
(
    users_id integer not null
            references users(id),
    roles_id integer not null
            references role(id),
    primary key (users_id, roles_id)
);

INSERT INTO users_roles (users_id, roles_id)
VALUES (1,1),
       (1,2),
       (2,1)