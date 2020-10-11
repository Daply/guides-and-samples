insert into user_role(role) values ("CUSTOMER");
insert into user_role(role) values ("ADMIN");
insert into user_permission(permission_value) values ("modify");
insert into user_permission(permission_value) values ("delete");
insert into user_permission(permission_value) values ("buy");
insert into user(username, name, surname, password) values ("TonStark", "Tony", "Stark", "123Tony");
insert into user_roles(user_id, user_role_id) values (1, 2);
insert into user_permissions(user_id, user_permission_id) values (1, 1);
insert into user_permissions(user_id, user_permission_id) values (1, 2);
insert into user(username, name, surname, password) values ("BlackW", "Black", "Widow", "Spidey456");
insert into user_roles(user_id, user_role_id) values (2, 1);
insert into user_permissions(user_id, user_permission_id) values (2, 3);
insert into user(username, name, surname, password) values ("SpongeB", "Sponge", "Bob", "PatrikStar");
insert into user_roles(user_id, user_role_id) values (3, 1);
insert into user_permissions(user_id, user_permission_id) values (3, 3);
insert into product(name, price, description, image) values
("Apple", 100, "Tasty fruit", "/images/apples.jpg");
insert into product(name, price, description, image) values
("Cherry", 200, "Tasty fruit", "/images/cherry.jpg");
insert into product(name, price, description, image) values
("Grapes", 300, "Tasty fruit", "/images/grapes.jpg");
insert into product(name, price, description, image) values
("Bananas", 100, "Tasty fruit", "/images/bananas.jpg");
insert into product(name, price, description, image) values
("Peaches", 200, "Tasty fruit", "/images/peaches.jpg");
insert into cart(total_price, cart_user_id) values (800, 1);
insert into product_cart_quantity(product_id, quantity, price, products_cart_id) values (1, 2, 200, 1);
insert into product_cart_quantity(product_id, quantity, price, products_cart_id) values (2, 3, 600, 1);
insert into cart(total_price, cart_user_id) values (800, 2);
insert into product_cart_quantity(product_id, quantity, price, products_cart_id) values (3, 1, 300, 2);
insert into product_cart_quantity(product_id, quantity, price, products_cart_id) values (4, 5, 500, 2);
insert into cart(total_price, cart_user_id) values (3200, 3);
insert into product_cart_quantity(product_id, quantity, price, products_cart_id) values (1, 3, 300, 3);
insert into product_cart_quantity(product_id, quantity, price, products_cart_id) values (3, 5, 1500, 3);
insert into product_cart_quantity(product_id, quantity, price, products_cart_id) values (5, 7, 1400, 3);