create sequence user_sequence as integer increment 1;
create sequence user_detail_sequence as integer increment 1;


create table user_db (
id INTEGER PRIMARY KEY,
username VARCHAR(150) NOT NULL,
password VARCHAR(150) NOT NULL,
email VARCHAR(150) NOT NULL,
created_at TIMESTAMP
);

create table user_detail(
id INTEGER PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name  VARCHAR(100) NOT NULL,
age INTEGER,
birth_day DATE,
user_id INTEGER,
FOREIGN KEY (user_id) REFERENCES user_db(id)
);



