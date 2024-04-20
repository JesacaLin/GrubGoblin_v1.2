-- Database: Grub Goblin v3 - TEST DATA
--
-- Contains tables for available deals based on establishment and days of the week.
START TRANSACTION;

DROP TABLE IF EXISTS place, deal, deal_availability, availability, review CASCADE;

 CREATE TABLE place (
    place_id SERIAL PRIMARY KEY,
    place_name VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6) ,
    google_rating DECIMAL(2, 1)
 );

 CREATE TABLE deal (
    deal_id SERIAL PRIMARY KEY,
    place_id INT REFERENCES place ON DELETE CASCADE,
    type_of_deal VARCHAR(20),
    deal_description VARCHAR(150) NOT NULL
 );

 CREATE TABLE availability (
    availability_id SERIAL PRIMARY KEY,
    day_of_week INT NOT NULL,
    start_time TIME,
    end_time TIME
 );

 CREATE TABLE deal_availability (
      deal_id INT REFERENCES deal ON DELETE CASCADE,
      availability_id INT REFERENCES availability ON DELETE CASCADE,
      CONSTRAINT pk_deal_availability PRIMARY KEY(deal_id, availability_id)
   );

 CREATE TABLE review (
    review_id SERIAL PRIMARY KEY,
    deal_id INT REFERENCES deal ON DELETE CASCADE,
    stars DECIMAL(2,1),
    review_description VARCHAR(200)
 );


 INSERT INTO place (place_name, address, latitude, longitude, google_rating)
    VALUES ('elsa', '136 Atlantic Ave, Brooklyn, NY 11201', 40.690239, -73.995361, 4.7);
  INSERT INTO place (place_name, address, latitude, longitude, google_rating)
     VALUES ('vine', '81 Fleet Pl, Brooklyn, NY 11201', 40.693211, -73.981331, 4.9);

-- Elsa deal
INSERT INTO deal (place_id, type_of_deal, deal_description)
    VALUES (1, 'drinks', '$8 cocktails, 2 types');
INSERT INTO deal (place_id, type_of_deal, deal_description)
    VALUES (1, 'drinks', '$8 cocktails, 2 types');
INSERT INTO deal (place_id, type_of_deal, deal_description)
    VALUES (1, 'drinks', '$8 cocktails, 2 types');
INSERT INTO deal (place_id, type_of_deal, deal_description)
    VALUES (1, 'drinks', '$8 cocktails, 2 types');
INSERT INTO deal (place_id, type_of_deal, deal_description)
    VALUES (1, 'drinks', '$8 cocktails, 2 types');

    -- Vine deal
INSERT INTO deal (place_id, type_of_deal, deal_description)
        VALUES (2, 'drinks', '$11 cocktails');
INSERT INTO deal (place_id, type_of_deal, deal_description)
        VALUES (2, 'drinks', '$11 cocktails');


INSERT INTO availability (day_of_week, start_time, end_time)
    VALUES (1, '17:00:00', '19:00:00');
    -- DEAL 2
INSERT INTO availability (day_of_week, start_time, end_time)
    VALUES (2, '17:00:00', '19:00:00');
    -- DEAL 3
INSERT INTO availability (day_of_week, start_time, end_time)
    VALUES (3, '17:00:00', '19:00:00');
    -- DEAL 4
INSERT INTO availability (day_of_week, start_time, end_time)
    VALUES (4, '17:00:00', '19:00:00');
    -- DEAL 5
INSERT INTO availability (day_of_week, start_time, end_time)
    VALUES (5, '17:00:00', '19:00:00');

    -- DEAL 6
INSERT INTO availability (day_of_week, start_time, end_time)
    VALUES (6, '17:00:00', '20:00:00');
    -- DEAL 7
INSERT INTO availability (day_of_week, start_time, end_time)
    VALUES (7, '17:00:00', '20:00:00');


INSERT INTO deal_availability (deal_id, availability_id)
    VALUES( 1, 1);
INSERT INTO deal_availability (deal_id, availability_id)
    VALUES( 2, 2);
INSERT INTO deal_availability (deal_id, availability_id)
    VALUES( 3, 3);
INSERT INTO deal_availability (deal_id, availability_id)
    VALUES( 4, 4);
INSERT INTO deal_availability (deal_id, availability_id)
    VALUES( 5, 5);
INSERT INTO deal_availability (deal_id, availability_id)
    VALUES( 6, 6);
INSERT INTO deal_availability (deal_id, availability_id)
    VALUES( 7, 7);



INSERT INTO review ( deal_id, stars,review_description)
    VALUES (1, 3.9, 'The happy hour drinks were ok, their full price cocktails are much better!');
INSERT INTO review ( deal_id, stars,review_description)
    VALUES (2, 3.9, 'The happy hour drinks were ok, their full price cocktails are much better!');
INSERT INTO review ( deal_id, stars,review_description)
    VALUES (3, 3.9, 'The happy hour drinks were ok, their full price cocktails are much better!');
INSERT INTO review ( deal_id, stars,review_description)
    VALUES (4, 3.9, 'The happy hour drinks were ok, their full price cocktails are much better!');
INSERT INTO review ( deal_id, stars,review_description)
    VALUES (5, 3.9, 'The happy hour drinks were ok, their full price cocktails are much better!');
INSERT INTO review ( deal_id, stars,review_description)
    VALUES (6, 4.5, 'Solid cocktails and friendly service. Wish it was a dollar or two less. The charcuterie is worth getting.');
INSERT INTO review ( deal_id, stars,review_description)
    VALUES (7, 4.5, 'Solid cocktails and friendly service. Wish it was a dollar or two less. The charcuterie is worth getting.');

COMMIT;