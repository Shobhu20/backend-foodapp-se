create table AppUser(
id serial primary key,
first_name varchar(50) not NULL,
last_name varchar(50) not null,
email_id varchar not null,
password varchar(20),
phone_number varchar(10),
user_status int,
created_at timestamp,
updated_at timestamp
);


create index index_name on AppUser (email_id);
alter sequence AppUser_id_seq restart with 1;

insert into AppUser (first_name,last_name,email_id, password,phone_number,user_status)
values ('test_user1','username2','test@test.com','testpass',1234567890,0);