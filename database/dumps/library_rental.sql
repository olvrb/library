CREATE TABLE rental (
    rental_object_id VARCHAR(255) NOT NULL,
    user_id          VARCHAR(255) NOT NULL,
    returned         BIT          NOT NULL,
    start_date       DATETIME(6)  NULL,
    PRIMARY KEY (rental_object_id, user_id),
    CONSTRAINT FK1atgfj6jk8v8cvdvbn4guwk6d
        FOREIGN KEY (rental_object_id) REFERENCES rental_object (id),
    CONSTRAINT FKm6f1r8a0m7w8n5upyjslprj25
        FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('0187d7cf-79df-48e3-abd1-651e62b5036f', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-03-24 15:47:53.505000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('12378699-f246-4133-b64b-9d9e6409a5f1', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-04-19 14:17:17.792000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('135f6ba8-7d0d-46c1-a939-f01020f599df', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 13:22:40.198000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('22514e27-2f05-4c60-87a9-55fa351d951f', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 13:02:08.622000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('26ec20ad-280f-4885-87f2-dff936e926d2', 'a75ade70-8457-45af-b0b9-59e922e89373', false, '2021-04-19 14:17:17.792000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('2f48c692-a0ed-4f03-8ff7-1fef107ba7d4', 'a75ade70-8457-45af-b0b9-59e922e89373', false, '2021-03-24 15:46:16.536000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('3160f372-477b-4a61-b2d7-bc6f210300ca', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 13:23:57.763000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('412fb569-f0f6-4835-8d18-3b4ef68bfa58', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2022-03-07 15:17:28.804000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('44b8507d-0a00-4b56-a8ab-7bffddb2c9a2', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 13:02:06.484000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('4570591a-eb7b-41e8-9a57-535add721594', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 13:28:00.797000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('556891be-5f9b-45b8-b5b0-3b530ae7027f', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 12:29:04.543000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('81e748e1-fb2c-42a1-b97e-a43f961232f4', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 12:54:00.330000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('88691400-6b4c-42a2-bf2c-cf71be823c71', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 12:17:33.294000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('a4e9bde8-c8b2-400d-86e1-31f10a4c3492', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-06-09 12:17:35.369000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('e6957ca8-18a0-40cb-be34-0a003ff1f966', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 12:52:34.563000');
INSERT INTO library.rental (rental_object_id, user_id, returned, start_date) VALUES ('f42988ce-ec98-41d2-93e8-633dff1f0ac7', 'a75ade70-8457-45af-b0b9-59e922e89373', true, '2021-05-10 12:22:17.559000');