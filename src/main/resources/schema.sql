CREATE TABLE public.users
(
    ID   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255)
);

create table public.orders
(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    price double precision,
    user_id BIGINT references users(ID),
    name varchar(255)
);
