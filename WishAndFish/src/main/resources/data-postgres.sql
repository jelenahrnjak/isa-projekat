INSERT INTO ROLES (name) VALUES ('ROLE_CLIENT');
INSERT INTO ROLES (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLES (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO ROLES (name) VALUES ('ROLE_BOAT_OWNER');
INSERT INTO ROLES (name) VALUES ('ROLE_FISHING_INSTRUCTOR');


INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (100, 'Bačka Topola', 'Srbija', 45.806240, 19.624690, '24300' , 'Zagrebačka' , '9');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (200, 'Zvornik', 'Bosna i Hercegovina', 44.419320, 19.090030, '75400', 'Jardan', '129');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (300, 'Kula', 'Srbija', 45.609740, 19.519380, '25230', 'Novaka Pejčića', '128');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (400, 'Novi Sad', 'Srbija', 35.455, 29.519380, '32000', 'Dušana Petrovića', '16');
INSERT INTO ADDRESSES(id, city_name, country_name, latitude, longitude, postal_code, street, street_number)
	VALUES (500, 'Niš', 'Srbija', 345.45, 291.121, '23000', 'Marka Markovića', '25');

INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (55, false, 'jelenahrnjak99@gmail.com', true, null, 2, 'Jelena', 'flasaflasica123', '0607363683',151, 'Hrnjak', 100, 5, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (50, false, 'stojic.kris@gmail.com', true, null, 1, 'Kristina', 'Nikola1234.', '0643515864', 90, 'Stojić', 200, 3, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (35, false, 'dusansisarica@gmail.com', true, null, 0, 'Dušan', 'dusanglup', '0617294870', 0, 'Šišarica', 300, 1, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (40, false, 'urossisarica@gmail.com', true, null, 0, 'Uroš', 'urosuros', '0612345678', 0, 'Šišarica', 300, 2, null);


INSERT INTO COTTAGE_OWNER(id, deleted, email, enabled, last_password_reset_date, loyalty_category, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (50, false, 'stojic.kris@gmail.com', true, null, 1, 'Kristina', 'Nikola1234.', '0643515864', 90, 'Stojić', 200, 3, null);


INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id)
	VALUES (110, 'Good cottage on the river', 'CotLux', 100, 400, 50);
INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id)
	VALUES (111, 'Very good', 'The Overlook', 75, 500, 50);
INSERT INTO COTTAGES(id, description, name, price_per_day, address_id, cottage_owner_id)
	VALUES (112, 'Good', 'The River', 75, 500, 50);

INSERT INTO ROOMS(id, bed_number, cottage_id)
	VALUES (124, 10, 111);
INSERT INTO ROOMS(id, bed_number, cottage_id)
	VALUES (123, 2, 111);
INSERT INTO ROOMS(id, bed_number, cottage_id)
	VALUES (122, 3, 112);
INSERT INTO ROOMS(id, bed_number, cottage_id)
	VALUES (121, 1, 110);


INSERT INTO ADDITIONAL_SERVICES(id, name, price)
	VALUES (901, 'Parking', 0);
INSERT INTO public.additional_services(id, name, price)
    VALUES (902, 'Wi-Fi', 10);
INSERT INTO public.additional_services(id, name, price)
    VALUES (903, 'Air conditioning', 5);
INSERT INTO public.additional_services(id, name, price)
    VALUES (904, 'Fruit', 2);


INSERT INTO public.rules(id, content, cottage_id)
	VALUES (25, 'No smoking', 111);
INSERT INTO public.rules(id, content, cottage_id)
	VALUES (26, 'Forbidden food intake', 112);


INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id)
	VALUES (135, '2', '12-02-2022', 7, 130, false, '10-02-2022', 111);

INSERT INTO APPOINTMENTS(
	id, duration, end_date, max_persons, price, reserved, start_date, cottage_id)
	VALUES (133, '5', '10-05-2022', 7, 130, false, '05-05-2022', 111);

INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
	VALUES (901, 135);
INSERT INTO public.appointment_additional_services(additional_service_id, appointment_id)
	VALUES (902, 135);


