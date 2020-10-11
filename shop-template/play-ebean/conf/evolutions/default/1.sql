create database if not exists shop;

create table if not exists cart (
    cart_id bigint not null auto_increment,
    total_price double precision,
    cart_user_id bigint,
    primary key (cart_id)) engine=InnoDB;

create table if not exists product (
    product_id bigint not null auto_increment,
    name varchar(255) not null,
    price double precision not null,
    description varchar(255) not null,
    image varchar(255) not null,
    primary key (product_id)) engine=InnoDB;

create table if not exists product_cart_quantity (
    product_cart_quantity_id bigint not null auto_increment,
    price double precision not null,
    quantity int default 1,
    weight double default 0,
    product_id bigint,
    products_cart_id bigint,
    primary key (product_cart_quantity_id)) engine=InnoDB;

create table if not exists user (
    user_id bigint not null auto_increment,
    name varchar(255) not null,
    surname varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    primary key (user_id)) engine=InnoDB;

create table if not exists user_role (
    user_role_id bigint not null auto_increment,
    role varchar(255) not null,
    primary key (user_role_id)) engine=InnoDB;

create table if not exists user_permission (
    user_permission_id bigint not null auto_increment,
    permission_value varchar(255) not null,
    primary key (user_permission_id)) engine=InnoDB;

create table if not exists user_roles (
    user_id bigint not null,
    user_role_id bigint not null
)engine=InnoDB;

create table if not exists user_permissions (
    user_id bigint not null,
    user_permission_id bigint not null
)engine=InnoDB;

alter table user
    add constraint fk_user unique (username);
alter table cart
    add constraint fk_cart_user foreign key (cart_user_id) references user (user_id);
alter table product_cart_quantity
    add constraint fk_product_cart_quantity_product foreign key (product_id) references product (product_id);
alter table product_cart_quantity
    add constraint fk_product_cart_quantity_cart foreign key (products_cart_id) references cart (cart_id);

alter table user_roles
    add constraint fk_user_roles_user foreign key (user_id) references user (user_id);
alter table user_roles
    add constraint fk_user_roles_role foreign key (user_role_id) references user_role (user_role_id);

alter table user_permissions
    add constraint fk_user_permissions_user foreign key (user_id) references user (user_id);
alter table user_permissions
    add constraint fk_user_permissions_permission foreign key (user_permission_id) references user_permission (user_permission_id);
