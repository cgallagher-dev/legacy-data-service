-- user table for handling registration and login
-- email will be unique key.
create table user (
    id integer identity primary key,
    email varchar(128) not null unique,
    password varchar(64) not null,
    name varchar(128)
);

-- product table for the inventory
create table product (
    id integer identity primary key,
    name varchar(128) not null,
    brand varchar(128),
    type varchar(64),
    price decimal(10, 2),
    quantityOnHand integer,
    user_id integer,
    foreign key (user_id) references user(id)
);