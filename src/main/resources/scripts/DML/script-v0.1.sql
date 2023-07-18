--start fazal - 17/07/2023

INSERT INTO maildb.recipient (email, `subject`, body, sent)
VALUES ("iqrar.kpk1000@gmail.com", "Test Email", "This is a test email from Spring Boot", false);

INSERT INTO maildb.recipient (email, `subject`, body, sent)
VALUES ("sdkfh2023@gmail.com", "Test Email", "This is a test email from Spring Boot", false);

INSERT INTO maildb.recipient (email, `subject`, body, sent)
VALUES ("kharoon187@gmail.com", "Test Email", "This is a test email from Spring Boot", false);

Delete from maildb.recipient WHERE id IN (2,3);

UPDATE maildb.recipient u SET u.sent = false WHERE u.id IN (1,2,3);

--end fazal - 17/07/2023