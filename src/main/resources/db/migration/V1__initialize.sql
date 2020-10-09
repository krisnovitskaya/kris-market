create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
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

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('SOMETHING');

insert into users (username, password, email)
values
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@gmail.com'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int
);

create table orders (
    id                      bigserial primary key,
    user_id                 bigint references users(id),
    price                   int,
    address                 varchar(1000)
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
