DROP USER IF EXISTS 'dad'@'localhost';
CREATE USER 'dad'@'localhost' IDENTIFIED BY 'padre';
GRANT ALL PRIVILEGES ON gymtracker.* TO 'dad'@'localhost';
