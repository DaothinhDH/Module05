CREATE DATABASE demo_crud;
USE demo_crud;
CREATE TABLE categories (
    id int primary key auto_increment,
    name varchar(40) not null,
    status bit(1)default 1
);
CREATE TABLE product(
    id int primary key auto_increment,
    name varchar(255) NOT NULL,
    price float NOT NULL ,
    category_id int not null,
    foreign key (category_id) references categories(id)
)