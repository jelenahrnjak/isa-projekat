INSERT INTO ROLES (name) VALUES ('CLIENT');
INSERT INTO ROLES (name) VALUES ('ADMIN');
INSERT INTO ROLES (name) VALUES ('COTTAGE_OWNER');
INSERT INTO ROLES (name) VALUES ('BOAT_OWNER');
INSERT INTO ROLES (name) VALUES ('FISHING_INSTRUCTOR');

INSERT INTO loyalty_categories(id, discount, level, name, needed_points, needed_points_to_next_level)
	VALUES (10, 0, 1, 'REGULAR', 0, 500);
INSERT INTO loyalty_categories(id, discount, level, name, needed_points, needed_points_to_next_level)
	VALUES (20, 5, 2, 'SILVER', 500, 1500);
INSERT INTO loyalty_categories(id, discount, level, name, needed_points, needed_points_to_next_level)
	VALUES (30, 15, 3, 'GOLD', 1500, 0);

INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (100, 'Bačka Topola', 'Srbija', 45.806240, 19.624690, '24300' , 'Zagrebačka' , '9');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (200, 'Zvornik', 'Bosna i Hercegovina', 44.419320, 19.090030, '75400', 'Jardan', '129');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (300, 'Kula', 'Srbija', 45.609740, 19.519380, '25230', 'Novaka Pejčića', '128');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
   	VALUES (301, 'Kula', 'Srbija', 45.609740, 19.519380, '25230', 'Novaka Pejčića', '128');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (400, 'Novi Sad', 'Srbija', 35.455, 29.519380, '32000', 'Dušana Petrovića', '16');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (401, 'Novi Sad', 'Srbija', 35.455, 29.519380, '32000', 'Dušana Petrovića', '16');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (500, 'Niš', 'Srbija', 345.45, 291.121, '23000', 'Marka Markovića', '25');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (501, 'Niš', 'Srbija', 345.45, 291.121, '23000', 'Marka Markovića', '25');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
   	VALUES (302, 'Kula', 'Srbija', 45.609740, 19.519380, '25230', 'Novaka Pejčića', '128');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
       	VALUES (303, 'Kula', 'Srbija', 45.609740, 19.519380, '25230', 'Novaka Pejčića', '128');

INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (55, false, 'jelenahrnjak99@gmail.com', true, null, 30,  'Jelena', 'jelena', '0607363683',1510, 'Hrnjak', 100, 5, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (50, false, 'stojic.kris@gmail.com', true, null, 20, 'Kristina', 'kris', '0643515864', 900, 'Stojić', 200, 3, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (35, false, 'dusansisarica@gmail.com', true, null, 10, 'Dušan', 'dusanglup', '0617294870', 0, 'Šišarica', 300, 1, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (40, false, 'urossisarica@gmail.com', true, null, 10, 'Uroš', 'urosuros', '0612345678', 0, 'Šišarica', 301, 2, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (60, false, 'dusanfarmville@gmail.com', false, null, 10, 'Duki', 'dusandusan', '0617890213', 0, 'Suzuki', 302, 2, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (75, false, 'markomarko@gmail.com', true, null, 20, 'Marko', 'marko', '0643515864', 900, 'Markovic', 200, 4, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (95, false, 'jovana@gmail.com', true, null, 20, 'Jovana', 'jovana', '0623526598', 900, 'Petrovic', 200, 3, null);

INSERT INTO CLIENT(id)
    VALUES(35);

INSERT INTO COTTAGE_OWNER(id)
	VALUES (50);
INSERT INTO COTTAGE_OWNER(id)
    VALUES (95);
INSERT INTO BOAT_OWNER(id)
	VALUES (75);
INSERT INTO FISHING_INSTRUCTORS(id)
    VALUES(55);

INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id, rating, number_of_ratings,cover_image,deleted, number_of_rooms, beds_per_room)
	VALUES (110, 'Good cottage on the river', 'CotLux', 100, 400, 50,5.0,10, 'cottage1.jpg',false, 3, 2);
INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id, rating, number_of_ratings, cover_image,deleted, number_of_rooms, beds_per_room)
	VALUES (111, 'Very good', 'The Overlook', 75, 500, 50,3.5,4,'cottage2.jpg',false,2,3);
INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id, rating, number_of_ratings, cover_image,deleted, number_of_rooms, beds_per_room)
	VALUES (112, null, 'The River', 75, 501, 50,4.6,123,'cottage3.jpg',false,2, 1);
INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id, rating, number_of_ratings, cover_image,deleted, number_of_rooms, beds_per_room)
	VALUES (113, 'Great cottage near the river', 'Acros', 75, 401, 50,0.0,0,'cottage4.jpg', true, 2, 2);

INSERT INTO ROOMS(id, bed_number, cottage_id)
	VALUES (124, 10, 111);
INSERT INTO ROOMS(id, bed_number, cottage_id)
	VALUES (123, 2, 111);
INSERT INTO ROOMS(id, bed_number, cottage_id)
	VALUES (122, 3, 112);
INSERT INTO ROOMS(id, bed_number, cottage_id)
	VALUES (121, 1, 110);



--special action
INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (135, '2', '12-02-2022', 7, 130, true, '10-02-2022', 111, false, true);

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (133, '5', '10-05-2022', 7, 130, false, '05-05-2022', 111, false, true);

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (134, '5', '10-05-2022', 7, 130, false, '07-05-2022', 111, false, true);

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (137, '5', '10-05-2022', 7, 130, false, '05-10-2022', 111, false, true);


INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (138, '5', '10-05-2022', 7, 130, false, '05-10-2022', 111, false, true);

--normal

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (139, '5', '10-05-2022', 7, 130, false, '05-10-2022', 113, false, false);

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (140, '5', '10-05-2022', 7, 130, false, '05-10-2022', 111, false, false);

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (141, '5', '10-05-2022', 7, 130, false, '05-10-2022', 111, false, false);

INSERT INTO BOATS(
	id, average_grade, capacity, cover_image, description, engine_number, engine_power, length, max_speed, name, price_per_hour, type, address_id, boat_owner_id, rating, number_of_ratings, deleted, cancellation_conditions)
	VALUES (123, 4.9, 5, 'boat1.jpg', 'Super', 3, 100, 15, 120, 'Yachta', 20, 'Deck boat', 303, 75, 4.3, 123, false, 'Free');
INSERT INTO BOATS(
	id, average_grade, capacity, cover_image, description, engine_number, engine_power, length, max_speed, name, price_per_hour, type, address_id, boat_owner_id, rating, number_of_ratings,deleted, cancellation_conditions)
	VALUES (125, 4.7, 10, 'boat2.jpg', 'Good', 3, 70, 10, 120, 'FishBo', 21, 'Fishing boat', 303, 75, 0.0 , 0, false, '10% of the reservation price');

INSERT INTO public.rules(id, content, cottage_id, deleted)
	VALUES (25, 'No smoking', 111, false);
INSERT INTO public.rules(id, content, cottage_id, deleted)
	VALUES (26, 'Forbidden food intake', 111, false);
INSERT INTO public.rules(id, content, cottage_id, deleted)
	VALUES (27, 'Forbidden pets', 111, false);
INSERT INTO public.rules(id, content, cottage_id, deleted)
	VALUES (28, 'Loud music forbidden', 112, false);
INSERT INTO public.rules(id, content, boat_id, deleted)
	VALUES (29, 'Forbidden food intake', 125, false);
INSERT INTO public.rules(id, content, boat_id, deleted)
	VALUES (30, 'Forbidden pets', 125, false);
INSERT INTO public.rules(id, content, boat_id, deleted)
	VALUES (31, 'Loud music forbidden', 125, false);


INSERT INTO ADDITIONAL_SERVICES(id, name, price, deleted, appointment_id, cottage_id, boat_id)
	VALUES (901, 'Parking', 0, false, 133, 111, null);
INSERT INTO public.additional_services(id, name, price, deleted, appointment_id, cottage_id, boat_id)
    VALUES (902, 'Wi-Fi', 10, false, 137, 111, null);
INSERT INTO public.additional_services(id, name, price, deleted, appointment_id,cottage_id, boat_id)
    VALUES (903, 'Air conditioning', 5, false, 137, 111, null);
INSERT INTO public.additional_services(id, name, price, deleted, appointment_id, cottage_id, boat_id)
    VALUES (904, 'Fruit', 2, false, 137, 111, null);
INSERT INTO public.additional_services(id, name, price, deleted, appointment_id, cottage_id, boat_id)
    VALUES (905, 'Swimming', 2, false, null, null, 125);
INSERT INTO public.additional_services(id, name, price, deleted, appointment_id, cottage_id, boat_id)
    VALUES (906, 'Coctel', 2, false, null, null, 125);
INSERT INTO public.additional_services(id, name, price, deleted, appointment_id, cottage_id, boat_id)
    VALUES (907, 'Free fruit', 2, false, null, null, 125);
--INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
--	VALUES (901, 135);
--INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
--	VALUES (902, 135);


INSERT INTO  fishing_adventures(id, cover_image, description, deleted, name, number_of_ratings, price_per_hour, rating, address_id, fishing_instructor_id, capacity)
	VALUES (100, 'adventure1.jpg', 'Have fun with your friends!', false, 'Sharks hunting', 120, 20, 4.3, 400, 55, 10);

INSERT INTO  fishing_adventures(id, cover_image, description, deleted, name, number_of_ratings, price_per_hour, rating, address_id, fishing_instructor_id,capacity)
	VALUES (101, 'adventure2.jpg', 'Go with me and find pink fish!', false, 'Pink fishing', 0, 10, 0, 500, 55, 5);

INSERT INTO request_for_deleting( id, approved, processed, reason, user_id)
	VALUES (100, false, false, 'I do not use this site anymore', 35);

INSERT INTO public.images(id, path, boat_id, cottage_id, deleted)
	VALUES (666, 'cottage1.jpg', null, 111, false);
INSERT INTO public.images(id, path, boat_id, cottage_id, deleted)
	VALUES (667, 'cottage2.jpg', null, 111, false);
INSERT INTO public.images(id, path, boat_id, cottage_id, deleted)
	VALUES (668, 'cottage3.jpg', null, 111, false);
INSERT INTO public.images(id, path, boat_id, cottage_id, deleted)
	VALUES (669, 'cottage4.jpg', null, 111, false);

INSERT INTO public.images(id, path, boat_id, cottage_id, deleted)
	VALUES (670, 'boat-adding.jpg', 125, null, false);
INSERT INTO public.images(id, path, boat_id, cottage_id, deleted)
	VALUES (671, 'adventure1.jpg', 125, null, false);
INSERT INTO public.images(id, path, boat_id, cottage_id, deleted)
	VALUES (672, 'adventure2.jpg', 125, null, false);
INSERT INTO public.images(id, path, boat_id, cottage_id, deleted)
	VALUES (673, 'boat2.jpg', 125, null, false);


INSERT INTO public.navigation_equipment(
	id, deleted, name, boat_id)
	VALUES (101, false, 'GPS', 125);
INSERT INTO public.navigation_equipment(
	id, deleted, name, boat_id)
	VALUES (102, false, 'Radar', 125);
INSERT INTO public.navigation_equipment(
	id, deleted, name, boat_id)
	VALUES (103, false, 'VHF radio', 125);


INSERT INTO public.fishing_equipment(
	id, name, boat_id, deleted)
	VALUES (100, 'Stap', 125, false);
INSERT INTO public.fishing_equipment(
	id, name, boat_id, deleted)
	VALUES (101, 'Udica', 125, false );

