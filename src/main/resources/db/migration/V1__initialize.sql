create table users (
  id                    bigserial primary key,
  username              varchar(30) not null,
  password              varchar(80) not null
);

create table profiles(
  id                    bigserial primary key,
  user_id               bigint not null,
  firstname             varchar(50),
  lastname              varchar(50),
  email                 varchar(255),
  phone                 bigint,
  birth_year            int,
  sex                   bit,
  town                  varchar(50),
  foreign key (user_id) references users (id)
);


create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);


insert into users (username, password)
values
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into profiles (user_id, firstname, lastname, email, phone, birth_year, sex, town)
values
(1, 'Bob', 'White','bob@mail.ru', 3216547878, 1980, 1, 'London'),
(2, 'Elena', 'Sorokina','lenaizpolipropilena@yahoo.com', 4951212345, 1985, 0, 'Moscow'),
(3, 'Robin', 'Bobin','bobinbarabek@yahoo.com', 7896521313, 1995, 1, 'New-York');



insert into roles (name)
values
('ROLE_USER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');



insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2),
(3, 3),
(3, 2);

create table authorities (
    id      serial primary key,
    title   varchar(100)
);

insert into authorities (title)
values
('READABLE'),
('EDITABLE'),
('WRITABLE'),
('CREATABLE'),
('MOVABLE'),
('ERASABLE');



create table roles_authorities(
      role_id               int not null,
      authority_id        int not null,
      primary key (role_id, authority_id),
      foreign key (authority_id) references authorities (id),
      foreign key (role_id) references roles (id)
);

insert into roles_authorities (role_id, authority_id)
values
(1, 1),
(1, 4),
(2, 1),
(2, 2),
(2, 4),
(2, 5),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int,
    active                  bit
);

create table categories (
    id      bigserial,
    name    varchar(150) not null,
    primary key (id)
);

CREATE TABLE products_categories (
  product_id               bigint not null,
  category_id              bigint not null,
  primary key (product_id, category_id),
  foreign key (product_id) references products (id),
  foreign key (category_id) references categories (id)
);

insert into categories (name)
values
('category 1'), ('category 2'), ('category 3'), ('category 4'), ('test category');

insert into products (title, price, active)
values
('Potato', 30, 1),
('Tomato', 160, 1),
('Carrot', 40, 1),
('Cabbage', 35, 1),
('Pepper', 185, 1),
('Chili pepper', 190, 1),
('Eggplant', 200, 1),
('Cauliflower', 170, 1),
('Onion', 25, 1),
('Garlic', 150, 1),
('Parsley', 125, 1),
('Celery', 125, 1),
('Radish', 45, 1),
('Cucumber', 155, 1),
('Pumpkin', 190, 1),
('Courgette', 150, 1),
('Asparagus', 280, 1),
('Beets', 198, 1),
('Turnip', 141, 1),
('Ginger', 299, 1),
('Potato 2', 40, 1),
('Tomato 2', 170, 1),
('Carrot 2', 50, 1),
('Cabbage 2', 45, 1),
('Pepper 2', 195, 1),
('Chili pepper 2', 200, 1),
('Eggplant 2', 210, 1),
('Cauliflower 2', 180, 1),
('Onion 2', 35, 1),
('Garlic 2', 160, 1),
('Parsley 2', 135, 1),
('Celery 2', 135, 1),
('Radish 2', 55, 1),
('Cucumber 2', 160, 1),
('Pumpkin 2', 195, 1),
('Courgette 2', 160, 1),
('Asparagus 2', 290, 1),
('Beets 2', 199, 1),
('Turnip 2', 145, 1),
('Ginger 2', 300, 1);

insert into products_categories (product_id, category_id)
values
(1, 1), (2, 2), (3, 3), (4, 4),
(5, 1), (1, 2), (2, 3), (3, 4),
(6, 1), (7, 2), (8, 3), (8, 4),
(9, 1), (10, 2), (11, 3), (12, 4),
(13, 1), (14, 2), (15, 3), (16, 4),
(17, 1), (18, 2), (19, 3), (20, 4),
(21, 1), (22, 2), (23, 3), (24, 4),
(25, 1), (26, 2), (27, 3), (28, 4),
(29, 1), (30, 2), (31, 3), (32, 4),
(33, 1), (34, 2), (35, 3), (36, 4),
(37, 1), (38, 2), (39, 3), (40, 4);


CREATE TYPE order_status AS ENUM('NEW', 'IN_PROGRESS', 'DONE');

create table orders (
    id                      bigserial primary key,
    user_id                 bigint references users(id),
    price                   int,
    address                 varchar(1000),
    phone                   bigint,
    status                  order_status
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   int,
    price_per_product       int,
    quantity                int
);

insert into orders (user_id, price, address, phone, status)
values
(1, 200, 'address 1', 123456, 'DONE'),
(1, 190, 'address 1', 123456, 'IN_PROGRESS');


insert into order_items (product_id, order_id, price, price_per_product, quantity)
values
(7, 1, 200, 200, 1),
(1, 2, 30, 30, 1),
(2, 2, 160, 160, 1);