DELETE FROM USER_ROLES;
DELETE FROM VOTES;
DELETE FROM DISHES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO USERS (NAME, EMAIL, PASSWORD, REGISTERED)
VALUES ('User', 'user@gmail.com', '{noop}password', '2021-01-10'),
       ('User2', 'user2@gmail.com', '{noop}password', '2021-01-10'),
       ('User3', 'user3@gmail.com', '{noop}password', '2021-01-10'),
       ('User4', 'user4@gmail.com', '{noop}password', '2021-01-10'),
       ('Admin', 'admin@gmail.com', '{noop}admin', '2021-01-10');

INSERT INTO RESTAURANTS (NAME, REGISTERED)
VALUES ('Tokyo', '2015-01-01'),
       ('Frank', '2015-02-01'),
       ('Happy', '2015-03-01');

INSERT INTO DISHES (NAME, PRICE, DATE, RESTAURANT_ID)
VALUES ('Red soup', 200, '2021-01-10', 6),
       ('Greek salad', 150, '2021-01-10', 6),
       ('Lemon tea', 100, '2021-01-10', 6),
       ('Green soup', 200, '2021-01-10', 7),
       ('Fresh salad', 150, '2021-01-10', 7),
       ('Black coffee', 150, '2021-01-10', 7),
       ('Chicken soup', 200, '2021-01-10', 8),
       ('Carrot salad', 100, '2021-01-10', 8),
       ('Black tea', 100, '2021-01-10', 8),
       ('Red soup', 200, DATE_SUB(NOW(), INTERVAL 1 DAY), 6),
       ('Greek salad', 150, DATE_SUB(NOW(), INTERVAL 1 DAY), 6),
       ('Lemon tea', 100, DATE_SUB(NOW(), INTERVAL 1 DAY), 6),
       ('Green soup', 200, DATE_SUB(NOW(), INTERVAL 1 DAY), 7),
       ('Fresh salad', 150, DATE_SUB(NOW(), INTERVAL 1 DAY), 7),
       ('Black coffee', 150, DATE_SUB(NOW(), INTERVAL 1 DAY), 7),
       ('Chicken soup', 200, DATE_SUB(NOW(), INTERVAL 1 DAY), 8),
       ('Carrot salad', 100, DATE_SUB(NOW(), INTERVAL 1 DAY), 8),
       ('Black tea', 100, DATE_SUB(NOW(), INTERVAL 1 DAY), 8),
       ('Red soup', 200, now(), 6),
       ('Greek salad', 150, now(), 6),
       ('Lemon tea', 100, now(), 6),
       ('Green soup', 200, now(), 7),
       ('Fresh salad', 150, now(), 7),
       ('Black coffee', 150, now(), 7),
       ('Chicken soup', 200, now(), 8),
       ('Carrot salad', 100, now(), 8),
       ('Black tea', 100, now(), 8);

INSERT INTO VOTES (USER_ID, RESTAURANT_ID, DATE)
VALUES (1, 6, '2021-01-10'),
       (2, 6, '2021-01-10'),
       (3, 7, '2021-01-10'),
       (4, 8, '2021-01-10'),
       (2, 6, now()),
       (3, 7, now()),
       (4, 8, now());

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4),
       ('ADMIN', 5);

