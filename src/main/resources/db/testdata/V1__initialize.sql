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
  phone                 int,
  birth_year            int,
  sex                   varchar(3),
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
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into profiles (user_id, firstname, lastname, phone, birth_year, sex, town)
values
(1, 'Bob', 'White', 1234567, 1980, 'm', 'London'),
(2, 'Elena', 'Sorokina', 7654321, 1985, 'f', 'Moscow');



insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('SOMETHING');



insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(2, 1);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int
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
('category 1'), ('category 2'), ('category 3'), ('category 4');



create table orders (
    id                      bigserial primary key,
    user_id                 bigint references users(id),
    price                   int,
    address                 varchar(1000),
    phone                   int
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   int,
    price_per_product       int,
    quantity                int
);


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
('Ginger', 299);

insert into products_categories (product_id, category_id)
values
(1, 1), (2, 2), (3, 3), (4, 4),
(5, 1), (1, 2), (2, 3), (3, 4),
(9, 1), (10, 2), (11, 3), (12, 4),
(13, 1), (14, 2), (15, 3), (16, 4),
(17, 1), (18, 2), (19, 3), (20, 4);
