INSERT INTO roles(role_name) VALUES('ROLE_ADMIN');
INSERT INTO roles(role_name) VALUES('ROLE_SELLER');
INSERT INTO roles(role_name) VALUES('ROLE_BUYER');



-- 1 - ADMIN - MyPass
INSERT INTO users(user_email, password, user_nid, username, fullname, address_id, account_number)
VALUES('domoubrice@gmail.com', '$2a$10$l50an7b4TXJahNHm/rSLL.L72s6St8em1qAEYTSPXq/u6lt/K5gJC', '0123456789', 'dbryzz', 'Domou Brice', null, null);


-- 2, 3 - BUYER - johndoe
INSERT INTO users(user_email, password, user_nid, username, fullname, address_id, account_number)
VALUES('johndoe@email.com', '$2a$10$k4llqSGuZLWNtsvSdFgDf.3lqx3HfQbX5WNEzVZVAYGsrFbORnczi',	'000000000',	'johndoe',	'John Doe', null,	null);
INSERT INTO users(user_email, password, user_nid, username, fullname, address_id, account_number)
VALUES('janedoe@email.com', '$2a$10$k4llqSGuZLWNtsvSdFgDf.3lqx3HfQbX5WNEzVZVAYGsrFbORnczi',	'000000001',	'janedoe',	'Jane Doe', null,	null);

-- 4 - SELLER - bruno
INSERT INTO users(user_email, password, user_nid, username, fullname, address_id, account_number)
VALUES('bruno@email.com', '$2a$10$sSWGnmXCi42djpNO/8O5jeo1iJ4/CfWsdza42S2Qbg6bLfM0l1lMu',	'000001111',	'bruno',	'Serkwi Bruno', null,	null);

-- 5, 6 - BUYER - johndoe
INSERT INTO users(user_email, password, user_nid, username, fullname, address_id, account_number)
VALUES('jessie@email.com', '$2a$10$k4llqSGuZLWNtsvSdFgDf.3lqx3HfQbX5WNEzVZVAYGsrFbORnczi',	'000000002',	'jessie',	'Mbekou Jessie', null,	null);
INSERT INTO users(user_email, password, user_nid, username, fullname, address_id, account_number)
VALUES('harvey@email.com', '$2a$10$k4llqSGuZLWNtsvSdFgDf.3lqx3HfQbX5WNEzVZVAYGsrFbORnczi',	'000000003',	'harvey',	'Ngoe Harvey', null,	null);

-- 7 - SELLER - percy
INSERT INTO users(user_email, password, user_nid, username, fullname, address_id, account_number)
VALUES('percy@email.com', '$2a$10$V.XixVJLYQ4WJbfy2jZhhO8M0VEQYKcTmfmvumHFRl1AApU4tNX0e',	'000002222',	'percy',	'Percy Ayuk', null,	null);

-- 8 - BUYER - johndoe
INSERT INTO users(user_email, password, user_nid, username, fullname, address_id, account_number)
VALUES('lobe@email.com', '$2a$10$k4llqSGuZLWNtsvSdFgDf.3lqx3HfQbX5WNEzVZVAYGsrFbORnczi',	'000000004',	'lobe',	'Lobe Nyoh', null,	null);

-- 9 - ADMIN - MyPass
INSERT INTO users(user_email, password, user_nid, username, fullname, address_id, account_number)
VALUES('teke@email.com', '$2a$10$l50an7b4TXJahNHm/rSLL.L72s6St8em1qAEYTSPXq/u6lt/K5gJC', '0023456789', 'dudu', 'Teke Durran', null, null);

INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(3, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(4, 2);
INSERT INTO user_roles(user_id, role_id) VALUES(5, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(6, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(7, 2);
INSERT INTO user_roles(user_id, role_id) VALUES(8, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(9, 1);

INSERT INTO categories(category_id, title, description) VALUES(1, 'dresses', 'male and female clothes');
INSERT INTO categories(category_id, title, description) VALUES(2, 'education', 'academic equipments and tools');
INSERT INTO categories(category_id, title, description) VALUES(3, 'cars', 'four wheels vehicles');
INSERT INTO categories(category_id, title, description) VALUES(4, 'phones', 'All types of phones');

