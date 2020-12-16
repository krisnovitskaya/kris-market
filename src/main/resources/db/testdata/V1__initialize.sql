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
  phone                 int,
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
(1, 'Bob', 'White','bob@mail.ru', 1234567, 1980, 1, 'London'),
(2, 'Elena', 'Sorokina','lenaizpolipropilena@yahoo.com', 7654321, 1985, 0, 'Moscow'),
(3, 'Robin', 'Bobin','bobinbarabek@yahoo.com', 254321, 1995, 1, 'New-York');



insert into roles (name)
values
('ROLE_USER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');



insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2),
(3, 3),
(3, 2);


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
('Ginger', 299, 1);

insert into products_categories (product_id, category_id)
values
(1, 1), (2, 2), (3, 3), (4, 4),
(5, 1), (1, 2), (2, 3), (3, 4),
(6, 1), (7, 2), (8, 3), (8, 4),
(9, 1), (10, 2), (11, 3), (12, 4),
(13, 1), (14, 2), (15, 3), (16, 4),
(17, 1), (18, 2), (19, 3), (20, 4);


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

