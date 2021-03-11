DROP TABLE IF EXISTS spaceship_communication_request;

CREATE TABLE spaceship_communication_request(
	id INT AUTO_INCREMENT PRIMARY KEY,
	satellite_name VARCHAR(50) NOT NULL,
	distance DOUBLE NOT NULL,
	message VARCHAR(250) NOT NULL,
	is_not_located BOOLEAN DEFAULT TRUE
);