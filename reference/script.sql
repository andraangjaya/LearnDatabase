create table product (
                         id serial primary key,
                         name varchar (200) not null,
                         category varchar (30),
                         sub_category varchar (30),
                         price numeric not null,
                         status varchar (20) not null,
                         stock int not null
)

drop table product

    copy product (name, category, sub_category, price, status, stock) from 'D:\product_data.csv'
    DELIMITER ','
    CSV HEADER;

insert into product (name, category, sub_category, price, status, stock) values ('computer', 'hardware', 'big', 300, 'available', '200')

select * from product;