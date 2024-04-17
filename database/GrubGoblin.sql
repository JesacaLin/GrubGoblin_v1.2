-- Database: Grub Goblin v3
--
-- Contains tables for available deals based on establishment and days of the week.
START TRANSACTION;

DROP TABLE IF EXISTS place, deal, deal_availability, availability, reviews CASCADE;

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
    start_time TIME NOT NULL,
    end_time TIME
 );

 CREATE TABLE deal_availability (
      deal_id INT REFERENCES deal ON DELETE CASCADE,
      availability_id INT REFERENCES availability ON DELETE CASCADE,
      CONSTRAINT pk_deal_availability PRIMARY KEY(deal_id, availability_id)
   );

 CREATE TABLE reviews (
    rating_id SERIAL PRIMARY KEY,
    deal_id INT REFERENCES deal ON DELETE CASCADE,
    stars DECIMAL(2,1),
    review_description VARCHAR(200)
 );


 INSERT INTO place (place_name, address, latitude, longitude, google_rating)
    VALUES
    ('elsa', '136 Atlantic Ave, Brooklyn, NY 11201', 40.690239, -73.995361, 4.7),
    ('vine', '81 Fleet Pl, Brooklyn, NY 11201', 40.693211, -73.981331, 4.9),
    ('osteria brooklyn @dekalb', '445 Albee Square Brooklyn, NY 11201', 40.690740, -73.983270, null),
    ('osteria brooklyn', '458 Myrtle Ave, Brooklyn, NY 11205', 40.693120, -73.967140, 4.6);

INSERT INTO deal (place_id, type_of_deal, deal_description)
    VALUES
    -- DEAL 1
    (1, 'drinks', '$8 cocktails, 2 types'),
    -- DEAL 2
    (2, 'drinks', '$11 cocktails'),
    -- DEAL 3
    (3, 'drinks', '50% off drinks'),
    -- DEAL 4
    (3, 'food', '2 course lunch set menu $25'),
    -- DEAL 5
    (4, 'drinks', '$8 cocktails');

INSERT INTO availability (day_of_week, start_time, end_time)
    VALUES
    -- DEAL 1
    (1, '17:00:00', '19:00:00'),
    -- DEAL 1
    (2, '17:00:00', '19:00:00'),
    -- DEAL 1
    (3, '17:00:00', '19:00:00'),
    -- DEAL 1
    (4, '17:00:00', '19:00:00'),
    -- DEAL 1
    (5, '17:00:00', '19:00:00'),
    -- DEAL 2
    (6, '17:00:00', '20:00:00'),
    -- DEAL 2
    (7, '17:00:00', '20:00:00'),
    -- DEAL 3
    (5, '16:00:00', '18:00:00'),
    -- DEAL 4
    (1, '11:00:00', '14:00:00'),
    -- DEAL 4
    (5, '11:00:00', '14:00:00'),
    -- DEAL 5
    (2, '16:00:00', '21:00:00');


INSERT INTO deal_availability (deal_id, availability_id)
    VALUES
    ( 1, 1),
    ( 1, 2),
    ( 1, 3),
    ( 1, 4),
    ( 1, 5),
    ( 2, 6),
    ( 2, 7),
    ( 3, 8),
    ( 4, 9),
    ( 4, 10),
    ( 5, 11);

INSERT INTO reviews ( deal_id, stars,review_description)
    VALUES
    (1, 3.9, 'The happy hour drinks were ok, their full price cocktails are much better!'),
    (2, 4.5, 'Solid cocktails and friendly service. Wish it was a dollar or two less. The charcuterie is worth getting.'),
    (3, 3.0, 'Good place to get a quick drink if you need to rest while shopping, nothing to write home about.' ),
    (4, 3.0, 'Very large portions so the deal is worth it!'),
    (5, 4.0, 'Love the vibe here, good place for drinks with friends. Drinks are STRONG!');
	
COMMIT;