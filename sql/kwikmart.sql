drop database if exists kwikmart;

create database kwikmart;

use kwikmart;

create table po (
    ord_id int auto_increment not null,
    name varchar(64) not null,
    email varchar(64),
    primary key(ord_id)
);

create table line_item (
    item_id int auto_increment not null,
    description varchar(64),
    quantity int,
    unit_price decimal(14,4),
    ord_id int, 
    primary key(item_id),
    constraint fk_ord_id
        foreign key(ord_id)
        references po(ord_id)
);