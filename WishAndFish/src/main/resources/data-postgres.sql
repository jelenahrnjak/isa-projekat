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
	VALUES (400, 'Zlatibor', 'Srbija', 35.455, 29.519380, '32000', 'Dušana Petrovića', '16');

INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (55, false, 'jelenahrnjak99@gmail.com', true, null, 2, 'Jelena', 'flasaflasica123', '0607363683',151, 'Hrnjak', 100, 5, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (50, false, 'stojic.kris@gmail.com', true, null, 1, 'Kristina', 'Nikola1234.', '0643515864', 90, 'Stojić', 200, 3, null);
INSERT INTO USERS(id, deleted, email, enabled, last_password_reset_date, loyalty_category, name, password, phone_number, points, surname, address_id, role_id, verification_code)
	VALUES (35, false, 'dusansisarica@gmail.com', true, null, 0, 'Dušan', 'dusanglup', '0617294870', 0, 'Šišarica', 300, 1, null);

INSERT INTO ROOMS(id, bed_number)
	VALUES (124, 10);
INSERT INTO ROOMS(id, bed_number)
	VALUES (123, 2);
INSERT INTO ROOMS(id, bed_number)
	VALUES (122, 3);
INSERT INTO ROOMS(id, bed_number)
	VALUES (121, 1);



INSERT INTO COTTAGES(id, description, name, price_per_day, address_id)
	VALUES (110, "Good cottage on the river", "CotLux", 100, 400);
