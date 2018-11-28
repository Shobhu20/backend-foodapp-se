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


create table authtoken(
id serial primary key,
email varchar not null unique,
token varchar not null,
created_at timestamp
);

create table restaurant(
id serial primary key,
 name varchar not null,
 fc_id int references foodcourt(id),
 URL varchar)
 );

create table FoodCourt(
id serial primary key,
name varchar unique,
address varchar,
city varchar,
open_time varchar,
close_time varchar
);

create index index_fc on foodcourt (city);


create index index_fc on foodcourt (city);

create table customer_order(
id serial primary key,
user_id int references AppUser(id),
user_name varchar,
total_cost numeric(7,2),
order_time timestamp,
foodcourt_id int references FoodCourt(id),
foodcourt_name varchar,
order_status int,
prep_time int
);

create index index_uname on customer_order(user_name);


create table ordered_items(
id serial primary key,
name varchar,
order_id int references customer_order(id),
restaurant_id int references restaurant(id),
restaurant_name varchar,
item_cost numeric(7,2),
quantity int
);


create table vendor_restaurant_mapping (
user_id int references AppUser(id),
restuarant_id int references restaurant(id)
)

create table restaurant_order_status (
restaurant_id int references restaurant(id),
order_id int references customer_order(id),
order_status int,
primary(order_id, restaurant_id)
)