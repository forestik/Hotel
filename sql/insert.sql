INSERT INTO public.users (id, date_of_registration, email, email_notification, first_name, last_name, phone_number, refresh_token_key, role, user_status, hotel_id) VALUES (28, '2021-05-16 21:26:49.650783', 'orest.mamchuk.kn.2017@lpnu.ua', 0, 'Orest', 'Mamchuk', null, '64741e4e-b4be-4829-aac7-0ee3d1bfe8d7', 1, 2, null);
INSERT INTO public.users (id, date_of_registration, email, email_notification, first_name, last_name, phone_number, refresh_token_key, role, user_status, hotel_id) VALUES (30, '2021-05-23 21:02:57.372923', 'olehmamchuck73@gmail.com', 0, 'Oleh', 'Mamchuk', null, '2ab00295-6a82-45c1-a6a4-374df1dee1c1', 0, 2, null);
INSERT INTO public.users (id, date_of_registration, email, email_notification, first_name, last_name, phone_number, refresh_token_key, role, user_status, hotel_id) VALUES (31, '2021-05-29 19:58:20.913403', 'nervvikss@gmail.com', 0, 'Yura', 'Leskiv', null, '1108a6a6-ce98-44d1-88a5-eebdc075cf7b', 0, 2, null);
INSERT INTO public.users (id, date_of_registration, email, email_notification, first_name, last_name, phone_number, refresh_token_key, role, user_status, hotel_id) VALUES (32, '2021-06-13 16:27:34.393371', 'orest199912@gmail.com', 0, 'Orest', 'Mamchuk', null, '136f0635-32de-45cd-a6e4-5a85ea50c947', 0, 2, null);
INSERT INTO public.users (id, date_of_registration, email, email_notification, first_name, last_name, phone_number, refresh_token_key, role, user_status, hotel_id) VALUES (33, '2021-06-13 16:32:34.923996', 'orestmamchuk99@gmail.com', 0, 'Orest', 'Mamchuk', null, '88344520-36f5-4804-92a1-120742839ecd', 0, 2, null);
INSERT INTO public.users (id, date_of_registration, email, email_notification, first_name, last_name, phone_number, refresh_token_key, role, user_status, hotel_id) VALUES (34, '2021-06-13 16:48:01.221007', 'orestmamchuk@gmail.com', 0, 'Orest', 'Mamchuk', null, '437ffd68-09e4-4e27-8042-50084f8ee5e4', 0, 2, null);

INSERT INTO public.own_security (id, password, user_id) VALUES (15, '$2a$10$VuLXcnc4uuiTzQoDY4zam.PHEc8HuFjbVD9H9dXQY4tebn2vojQ4W', 28);
INSERT INTO public.own_security (id, password, user_id) VALUES (17, '$2a$10$YdfvwjKQBzpQOu8icp89P.ympFRzByeZ.XckZoDCTeydzDGDxZ90q', 30);
INSERT INTO public.own_security (id, password, user_id) VALUES (18, '$2a$10$5B5LUa51WwxvMRRxvV3mCuXfLB8C.tR8LsfZQ6g1Xg9X8Wakc7Qci', 31);
INSERT INTO public.own_security (id, password, user_id) VALUES (19, '$2a$10$58GZSSvbqeymZRr3UAxYjenkcVdxv8kOq1gN4x6EyjcttDJd/abSa', 32);
INSERT INTO public.own_security (id, password, user_id) VALUES (20, '$2a$10$1xpThJYtbesHPf4uG5Mh5OvAYZT./HzO7s.gZDEfkmiiakGB9tOUm', 33);
INSERT INTO public.own_security (id, password, user_id) VALUES (21, '$2a$10$Krc2c4kfiXnc7Ve2vkdGl.U6Vc0yNtr1EvrOVRDvl.NvQByDe0v4a', 34);

INSERT INTO public.hotels (id, name, address, email, phone, count) VALUES (3, 'Dub', 'Lviv 56', 'tdgdgfg@gmail.com', '+380976464635', 0);
INSERT INTO public.hotels (id, name, address, email, phone, count) VALUES (9, 'Lux', 'Lviv, Rusit 535', 'luxhotel@gmail.com', '+380976656545', 0);
INSERT INTO public.hotels (id, name, address, email, phone, count) VALUES (4, 'Grand', 'Lviv 56 ', 'Grand@gmail.com', '+380954728495', 2);
INSERT INTO public.hotels (id, name, address, email, phone, count) VALUES (2, 'Topolnia', 'Lviv, Topolnia 56', 'Mysykmasks@gmail.com', '+380978843469', 1);
INSERT INTO public.hotels (id, name, address, email, phone, count) VALUES (1, 'Lviv', 'Lviv Dovzhenka 56', 'orest199912@gmail.com', '+380970841169', 5);

INSERT INTO public.rooms (id, name, price_per_day, room_status, type, booking_id, hotel_id, user_id) VALUES (4, '104', 1500, 2, 2, null, 2, null);
INSERT INTO public.rooms (id, name, price_per_day, room_status, type, booking_id, hotel_id, user_id) VALUES (5, '101', 1500, 3, 2, null, 4, null);
INSERT INTO public.rooms (id, name, price_per_day, room_status, type, booking_id, hotel_id, user_id) VALUES (7, '204', 2000, 3, 1, null, 4, null);
INSERT INTO public.rooms (id, name, price_per_day, room_status, type, booking_id, hotel_id, user_id) VALUES (6, '102', 1500, 3, 2, null, 4, null);
INSERT INTO public.rooms (id, name, price_per_day, room_status, type, booking_id, hotel_id, user_id) VALUES (3, '103', 2000, 0, 2, null, 1, null);
INSERT INTO public.rooms (id, name, price_per_day, room_status, type, booking_id, hotel_id, user_id) VALUES (1, '101', 1500, 3, 1, null, 1, null);
INSERT INTO public.rooms (id, name, price_per_day, room_status, type, booking_id, hotel_id, user_id) VALUES (8, '204', 1500, 3, 1, null, 1, null);
INSERT INTO public.rooms (id, name, price_per_day, room_status, type, booking_id, hotel_id, user_id) VALUES (10, '206', 1500, 0, 0, null, 1, null);
INSERT INTO public.rooms (id, name, price_per_day, room_status, type, booking_id, hotel_id, user_id) VALUES (2, '102', 1000, 1, 1, null, 1, null);

INSERT INTO public.bookings (id, confirmed, date_from, date_to, admin_id, customer_id, room_id) VALUES (7, true, '2021-05-25 03:00:00.000000', '2021-05-30 03:00:00.000000', 28, 28, 2);
INSERT INTO public.bookings (id, confirmed, date_from, date_to, admin_id, customer_id, room_id) VALUES (8, false, '2021-05-25 03:00:00.000000', '2021-05-29 03:00:00.000000', null, 28, 1);
INSERT INTO public.bookings (id, confirmed, date_from, date_to, admin_id, customer_id, room_id) VALUES (9, false, '2021-05-26 03:00:00.000000', '2021-06-27 03:00:00.000000', null, 28, 1);
INSERT INTO public.bookings (id, confirmed, date_from, date_to, admin_id, customer_id, room_id) VALUES (6, false, '2021-05-24 03:00:00.000000', '2021-05-29 03:00:00.000000', 28, 28, 3);
INSERT INTO public.bookings (id, confirmed, date_from, date_to, admin_id, customer_id, room_id) VALUES (10, false, '2021-05-25 03:00:00.000000', '2021-05-30 03:00:00.000000', null, 30, 3);
INSERT INTO public.bookings (id, confirmed, date_from, date_to, admin_id, customer_id, room_id) VALUES (15, false, '2021-06-08 03:00:00.000000', '2021-06-18 03:00:00.000000', null, 30, 2);
INSERT INTO public.bookings (id, confirmed, date_from, date_to, admin_id, customer_id, room_id) VALUES (16, false, '2021-06-08 03:00:00.000000', '2021-06-12 03:00:00.000000', null, 30, 2);
INSERT INTO public.bookings (id, confirmed, date_from, date_to, admin_id, customer_id, room_id) VALUES (20, true, '2021-06-14 03:00:00.000000', '2021-06-20 03:00:00.000000', 28, 32, 2);
