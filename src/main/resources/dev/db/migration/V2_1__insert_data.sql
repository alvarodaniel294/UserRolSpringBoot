insert into rol_db (id, name) values
(1, 'rol1'),
(2, 'rol2'),
(3, 'rol3'),
(4, 'rol4'),
(5, 'rol5'),
(6, 'rol6'),
(7, 'rol7');


insert into user_db (id, username, password, email, created_at) values
(1, 'user1', 'password1', 'user1@mail.com',CURRENT_TIMESTAMP),
(2, 'user2', 'password2', 'user2@mail.com', CURRENT_TIMESTAMP),
(3, 'user3', 'password3', 'user3@mail.com', CURRENT_TIMESTAMP),
(4, 'user4', 'password4', 'user4@mail.com', CURRENT_TIMESTAMP),
(5, 'user5', 'password5', 'user5@mail.com', CURRENT_TIMESTAMP);


insert into user_detail (id, first_name, last_name, age, birth_day, user_id) values
(1, 'fn1', 'ln1', 1, null, 1),
(2, 'fn2', 'ln2', 2, null, 2),
(3, 'fn3', 'ln3', 3, null, 3),
(4, 'fn4', 'ln4', 4, null, 4),
(5, 'fn5', 'ln5', 5, null, 5);




insert into user_rol(id, active, created_at, user_id, rol_id) values
(1, true, CURRENT_TIMESTAMP, 1, 1),
(2, true, CURRENT_TIMESTAMP, 1, 2),
(3, true, CURRENT_TIMESTAMP, 1, 3),
(4, true, CURRENT_TIMESTAMP, 2, 1),
(5, true, CURRENT_TIMESTAMP, 2, 2);
