


create sequence rol_sequence as integer increment 1;
create sequence user_rol_sequence as integer increment 1;


create table rol_db(
id INTEGER PRIMARY KEY NOT NULL,
name VARCHAR(100) UNIQUE NOT NULL
);

create table user_rol(
id INTEGER PRIMARY KEY NOT NULL,
active BOOLEAN NOT NULL,
created_at TIMESTAMP NOT NULL,
user_id INTEGER,
FOREIGN KEY(user_id) REFERENCES user_db(id),
rol_id INTEGER,
FOREIGN KEY(rol_id) REFERENCES rol_db(id)

);


