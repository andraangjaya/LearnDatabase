create table product (
                         id serial primary key,
                         name varchar (200) not null,
                         category varchar (30),
                         sub_category varchar (30),
                         price numeric not null,
                         status varchar (20) not null,
                         stock int not null
);

--drop table
drop table product;

    copy product (name, category, sub_category, price, status, stock) from 'D:\Belajar Program\LearnDatabase\reference\product_data_updated_stock.csv'
    DELIMITER ','
    CSV HEADER;

insert into product (name, category, sub_category, price, status, stock) values ('computer', 'hardware', 'big', 300, 'available', '200')

select count(*) from product where category = 'Electronics';
select count(*), category from product where price > 100 and stock < 50 group by category 

