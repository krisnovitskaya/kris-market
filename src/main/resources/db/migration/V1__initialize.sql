create table customers (
    id                      bigserial,
    name                    varchar(255) not null,
    primary key (id)
);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int
);

create table orders (
             id                      bigserial primary key,
             customer_id             bigint references customers(id),
             price                   int
         );

         create table order_items (
             id                      bigserial primary key,
             product_id              bigint references products(id),
             order_id                bigint references orders(id),
             price                   int,
             price_per_product       int,
             quantity                int
         );

         insert into customers (name)
         values
         ('Bob'),
         ('John'),
         ('Jack');

insert into products (title, price)
values
('Potato', 30),
('Tomato', 160),
('Carrot', 40),
('Cabbage', 35),
('Pepper', 185),
('Chili pepper', 190),
('Eggplant', 200),
('Cauliflower', 170),
('Onion', 25),
('Garlic', 150),
('Parsley', 125),
('Celery', 125),
('Radish', 45),
('Cucumber', 155),
('Pumpkin', 190),
('Courgette', 150),
('Asparagus', 280),
('Beets', 198),
('Turnip', 141),
('Ginger', 299),
('Potato 2', 40),
('Tomato 2', 170),
('Carrot 2', 50),
('Cabbage 2', 45),
('Pepper 2', 195),
('Chili pepper 2', 200),
('Eggplant 2', 210),
('Cauliflower 2', 180),
('Onion 2', 35),
('Garlic 2', 160),
('Parsley 2', 135),
('Celery 2', 135),
('Radish 2', 55),
('Cucumber 2', 160),
('Pumpkin 2', 195),
('Courgette 2', 160),
('Asparagus 2', 290),
('Beets 2', 199),
('Turnip 2', 145),
('Ginger 2', 300);
