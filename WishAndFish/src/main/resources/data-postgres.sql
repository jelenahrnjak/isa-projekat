INSERT INTO ROLES (name) VALUES ('ROLE_CLIENT');
INSERT INTO ROLES (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLES (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO ROLES (name) VALUES ('ROLE_BOAT_OWNER');
INSERT INTO ROLES (name) VALUES ('ROLE_FISHING_INSTRUCTOR');

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
	VALUES (55, false, 'jelenahrnjak99+1@gmail.com', true, null, 30,  'Dušan', 'jelena', '0607363683',1510, 'Šišarica', 100, 5, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (50, false, 'stojic.kris@gmail.com', true, null, 20, 'Kristina', 'kris', '0643515864', 900, 'Stojić', 200, 3, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (35, false, 'jelenahrnjak99@gmail.com', true, null, 10, 'Jelena', 'jelena', '0617294870', 0, 'Hrnjak', 300, 1, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (40, false, 'urossisarica@gmail.com', true, null, 10, 'Uroš', 'urosuros', '0612345678', 0, 'Šišarica', 301, 2, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (60, false, 'dusanfarmville@gmail.com', false, null, 10, 'Duki', 'dusandusan', '0617890213', 0, 'Suzuki', 302, 2, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (75, false, 'markomarko@gmail.com', true, null, 20, 'Marko', 'marko', '0643515864', 900, 'Markovic', 200, 4, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (95, false, 'jelenahrnjak99+123@gmail.com', true, null, 20, 'Jovana', 'jovana', '0623526598', 900, 'Petrović', 200, 3, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category_id, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (36, false, 'jelenahrnjak99+2@gmail.com', true, null, 10, 'Jovan', 'jelena', '064352658', 0, 'Jovanović', 300, 1, null);
 
INSERT INTO CLIENT(id, penalties)
    VALUES(35, 1);
INSERT INTO CLIENT(id, penalties)
    VALUES(36, 3);

INSERT INTO COTTAGE_OWNER(id, number_of_ratings, rating)
	VALUES (50, 0 , 0.0);
INSERT INTO COTTAGE_OWNER(id, number_of_ratings, rating)
    VALUES (95, 0 ,0.0);
INSERT INTO BOAT_OWNER(id, number_of_ratings, rating)
	VALUES (75, 0 ,0.0);
INSERT INTO FISHING_INSTRUCTORS(id, number_of_ratings, rating)
    VALUES(55, 0 ,0.0);

INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id, rating, number_of_ratings,cover_image,deleted, number_of_rooms, beds_per_room)
	VALUES (110, 'Good cottage on the river', 'CotLux', 100, 400, 50,5.0,10, 'cottage1.jpg',false, 3, 2);
INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id, rating, number_of_ratings, cover_image,deleted, number_of_rooms, beds_per_room)
	VALUES (111, 'Cosy cottage to enjoy with family and friends', 'The Overlook', 75, 200, 50,3.5,4,'cottage5.jpg',false,2,3);
INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id, rating, number_of_ratings, cover_image,deleted, number_of_rooms, beds_per_room)
	VALUES (112, null, 'The River', 75, 300, 95,4.6,123,'cottage2.jpg',false,2, 1);
INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id, rating, number_of_ratings, cover_image,deleted, number_of_rooms, beds_per_room)
	VALUES (113, 'Great cottage with nice view', 'Acros', 75, 401, 50,0.0,0,'cottage3.jpg', false, 2, 2);

INSERT INTO BOATS(
	id, capacity, cover_image, description, engine_number, engine_power, length, max_speed, name, price_per_day, type, address_id, boat_owner_id, rating, number_of_ratings, deleted, cancellation_conditions)
	VALUES (123, 5, 'boat1.jpg', 'Have great time on our boat', 3, 100, 15, 120, 'Yacht', 50, 'Deck boat', 401, 75, 4.3, 123, false, 'Free');
INSERT INTO BOATS(
    id, capacity, cover_image, description, engine_number, engine_power, length, max_speed, name, price_per_day, type, address_id, boat_owner_id, rating, number_of_ratings,deleted, cancellation_conditions)
    VALUES (124, 5, 'boat3.jpg', 'Enjoy with us!', 3, 70, 10, 120, 'White ship', 15, 'Fishing boat', 200,  75,  0.0 , 0, false, 'We do not return money');
INSERT INTO BOATS(
    id, capacity, cover_image, description, engine_number, engine_power, length, max_speed, name, price_per_day, type, address_id, boat_owner_id, rating, number_of_ratings,deleted, cancellation_conditions)
    VALUES (125, 10, 'boat2.jpg', 'Great opportunity to forget daily problems', 3, 70, 10, 120, 'FishBo', 21, 'Fishing boat', 303, 75, 5.0 , 31, false, '10% of the reservation price');

INSERT INTO  fishing_adventures(id, cover_image, description, deleted, name, number_of_ratings, price_per_day, rating, address_id, fishing_instructor_id, capacity)
	VALUES (100, 'adventure1.jpg', 'Have fun with your friends!', false, 'Sharks hunting', 120, 20, 4.3, 400, 55, 10);
INSERT INTO  fishing_adventures(id, cover_image, description, deleted, name, number_of_ratings, price_per_day, rating, address_id, fishing_instructor_id,capacity)
	VALUES (101, 'adventure2.jpg', 'Go with me and find pink fish!', false, 'Pink fishing', 0, 10, 0, 500, 55, 5);
INSERT INTO  fishing_adventures(id, cover_image, description, deleted, name, number_of_ratings, price_per_day, rating, address_id, fishing_instructor_id,capacity)
	VALUES (102, 'adventure3.jpg', 'Prepare for best time of your life!', false, 'Happy fish', 17, 17, 1.7, 500, 55, 5);

INSERT INTO public.rules(id, content, cottage_id, deleted)
	VALUES (25, 'No smoking', 111, false);
INSERT INTO public.rules(id, content, cottage_id, deleted)
	VALUES (26, 'Forbidden food intake', 111, false);
INSERT INTO public.rules(id, content, cottage_id, deleted)
	VALUES (27, 'Forbidden pets', 112, false);
INSERT INTO public.rules(id, content, cottage_id, deleted)
	VALUES (28, 'Loud music forbidden', 113, false);
INSERT INTO public.rules(id, content, cottage_id, deleted)
    VALUES (32, 'Pets not allowed', 113, false);
INSERT INTO public.rules(id, content, cottage_id, deleted)
    VALUES (33, 'Bikinis not allowed', 110, false);
INSERT INTO public.rules(id, content, boat_id, deleted)
	VALUES (29, 'Forbidden food intake', 125, false);
INSERT INTO public.rules(id, content, boat_id, deleted)
	VALUES (30, 'Forbidden pets', 125, false);
INSERT INTO public.rules(id, content, boat_id, deleted)
	VALUES (31, 'Loud music forbidden', 123, false);
INSERT INTO public.rules(id, content, boat_id, deleted)
	VALUES (34, 'Kids forbidden', 123, false);
INSERT INTO public.rules(id, content, boat_id, deleted)
	VALUES (35, 'Loud music forbidden', 124, false);


INSERT INTO ADDITIONAL_SERVICES(id, name, price, deleted, cottage_id, boat_id)
	VALUES (901, 'Parking', 5, false, 111, null);
INSERT INTO public.additional_services(id, name, price, deleted, cottage_id, boat_id)
    VALUES (902, 'Wi-Fi', 10, false, 111, null);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (903, 'Air conditioning', 5, false, 111, null);
INSERT INTO public.additional_services(id, name, price, deleted, cottage_id, boat_id)
    VALUES (904, 'Fruit', 2, false, 111, null);
INSERT INTO public.additional_services(id, name, price, deleted, cottage_id, boat_id)
    VALUES (905, 'Swimming', 2, false, null, 125);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (906, 'Cocktail', 2, false, null, 125);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (907, 'Free fruit', 2, false, null, 125);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (908, 'Air conditioning', 5, false, 112, null);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (909, 'Wi-Fi', 5, false, 112, null);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (910, 'Air conditioning', 5, false, 113, null);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (911, 'Wi-Fi', 5, false, 113, null);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (912, 'Bike', 3, false, 110, null);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (913, 'Wi-Fi', 5, false, 110, null);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (914, 'Wi-Fi', 3, false, null, 123);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (915, 'Cocktail', 5, false, null, 123);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (916, 'Massage', 25, false, null, 123);
INSERT INTO public.additional_services(id, name, price, deleted,cottage_id, boat_id)
    VALUES (917, 'Cocktail', 5, false, null, 124);
INSERT INTO public.additional_services(id, name, price, deleted, adventure_id)
    VALUES (918, 'Fishing rod', 5, false, 100);
INSERT INTO public.additional_services(id, name, price, deleted, adventure_id)
    VALUES (919, 'Hat', 2, false, 100);
INSERT INTO public.additional_services(id, name, price, deleted, adventure_id)
    VALUES (920, 'Fishing rod', 5, false, 101);
INSERT INTO public.additional_services(id, name, price, deleted, adventure_id)
    VALUES (921, 'Hat', 2, false, 101);
INSERT INTO public.additional_services(id, name, price, deleted, adventure_id)
    VALUES (922, 'Fishing rod', 5, false, 102);
INSERT INTO public.additional_services(id, name, price, deleted, adventure_id)
    VALUES (923, 'Hat', 2, false, 102);

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
	VALUES (100, 'Fishing rod', 125, false);
INSERT INTO public.fishing_equipment(
	id, name, boat_id, deleted)
	VALUES (101, 'Hook', 125, false );

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

--normal

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (100, '5', '06-30-2022 12:00', 7, 0, false, '06-01-2022 14:00', 110, false, false);


--booked normal

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (200, '5', '07-10-2022 12:00', 7, 503, true, '07-05-2022 14:00', 110, false, false);
INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
	VALUES (912, 200);
INSERT INTO public.reservations(
	id, canceled, appointment_id, client_id, finished, commented ,total_price, commented_owner, commented_entity, complaint_owner, complaint_entity)
	VALUES (100, false, 200, 35, false , false, 503, false, false, false, false);

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
	VALUES (201, '5', '06-01-2022 12:00', 7, 200, true, '05-30-2022 14:00', 110, false, false);
INSERT INTO public.reservations(
	id, canceled, appointment_id, client_id, finished, commented ,total_price, commented_owner, commented_entity, complaint_owner, complaint_entity)
	VALUES (101, false, 201, 35, false , false, 200, true, true, false, false);
INSERT INTO public.reviews(
	id, content, date, is_approved, is_for_owner, is_reviewed, rating, reservation_id, client_id)
	VALUES (10, 'Everything was ok!', '04-30-2022 12:00' , true, false, true, 4, 101, 35);
INSERT INTO public.reviews(
	id, content, date, is_approved, is_for_owner, is_reviewed, rating, reservation_id, client_id)
	VALUES (11, 'Owner was very kind!', '04-30-2022 12:00' , true, true, true, 4, 101, 35);

--free special action

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action, expiration_date)
	VALUES (300, '5', '07-05-2022 12:00', 7, 350, false, '07-01-2022 14:00', 110, false, true, '06-20-2022 14:00');

INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
	VALUES (912, 300);

--booked special action


--
--INSERT INTO APPOINTMENTS(
--	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
--	VALUES (1311, '5', '06-07-2022 12:00', 7, 750, true, '06-02-2022 14:00', 110, false, false);
--
--INSERT INTO public.reservations(
--	id, canceled, appointment_id, client_id, finished, commented ,total_price, commented_owner, commented_entity, complaint_owner, complaint_entity)
--	VALUES (1313, false, 1311, 35, false , false, 750, false, false, false, false);
--
--INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
--	VALUES (906, 1111);
--
--INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
--	VALUES (907, 1111);
--
--INSERT INTO APPOINTMENTS(
--	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id, deleted, action)
--	VALUES (1331, '5', '06-12-2022 12:00', 7, 750, true, '06-05-2022 14:00', 110, false, false);
--
--INSERT INTO public.reservations(
--	id, canceled, appointment_id, client_id, finished, commented ,total_price, commented_owner, commented_entity, complaint_owner, complaint_entity)
--	VALUES (1333, false, 1331, 35, false , false, 750, false, false, false, false);
--
--
--
--INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
--	VALUES (906, 1331);
--
--INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
--	VALUES (907, 1331);



--INSERT INTO public.reservations(
--	id, canceled, appointment_id, client_id, finished, commented ,total_price, commented_owner, commented_entity, complaint_owner, complaint_entity)
--	VALUES (1111, false, 133, 35, false , false, 500, false, false, false, false);
--
--INSERT INTO public.reservations(
--	id, canceled, appointment_id, client_id, finished, commented ,total_price, commented_owner, commented_entity, complaint_owner, complaint_entity)
--	VALUES (2222, false, 140, 35, false, false, 250,  false, false, false, false);


INSERT INTO public.comments(id, content, client_id) VALUES (111, 'She was late!', 35);
INSERT INTO public.comments(id, content, client_id) VALUES (222, 'Messy! Room was very dirty after reservation.', 35);
INSERT INTO public.comments(id, content, client_id) VALUES (333, 'Everything was fine!', 36);

INSERT INTO request_for_deleting( id, approved, processed, reason, user_id)
	VALUES (100, false, false, 'I do not use this site anymore', 35);