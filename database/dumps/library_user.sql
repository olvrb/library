CREATE TABLE user (
    dtype         VARCHAR(31)  NOT NULL,
    id            VARCHAR(255) NOT NULL
        PRIMARY KEY,
    name          VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    ssn           VARCHAR(255) NULL,
    supervisor_id VARCHAR(255) NULL,
    CONSTRAINT UK_o14ahnprxuevixmhn8cg59s4d
        UNIQUE (ssn),
    CONSTRAINT FKrvnycmphjyvdxat1lycui3hpk
        FOREIGN KEY (supervisor_id) REFERENCES user (id)
);

INSERT INTO library.user (dtype, id, name, password, ssn, supervisor_id) VALUES ('Student', '25ea3efe-7264-40b5-99dc-533ca5bd445c', 'jack', '$2a$10$zPyp353VtGe.Kc4WoXQgW.UK2OrmCfowzkAMp3q1Mdm9jl34knR4S', '9452917335', null);
INSERT INTO library.user (dtype, id, name, password, ssn, supervisor_id) VALUES ('Student', '865c89d7-be70-4e0b-b198-6ea1c5914217', 'jack', '$2a$10$KfvJPhiIdW2k3ToNx6m7fugIgTm5sTmP9/f.K34HGciTXlWeXnEJ2', '7206949644', null);
INSERT INTO library.user (dtype, id, name, password, ssn, supervisor_id) VALUES ('Student', '875d6c5b-25a3-4344-95fa-84d2d1131057', 'jack', '$2a$10$TzBXUuq/T8Z/zLXlnZO8w.IJWtErcrYRTlmhVtxl.kz7kICULreHG', '8673188475', null);
INSERT INTO library.user (dtype, id, name, password, ssn, supervisor_id) VALUES ('Employee', 'a75ade70-8457-45af-b0b9-59e922e89373', 'jack', '$2a$10$QPGYKMTtciwBvjoVNyQj1eXOLh.y1ZZExVhxfG0tLUZZ0LYbhjyzm', '0204150072', null);
INSERT INTO library.user (dtype, id, name, password, ssn, supervisor_id) VALUES ('Student', 'b186ed72-4143-41f5-910c-686d4b9cd42a', 'jack', '$2a$10$aoytPuee32LoUswplpH8YuQ0EDrBFSDI5W1ZhzzOaBYUaVcXaBZ8y', '3080815177', null);
INSERT INTO library.user (dtype, id, name, password, ssn, supervisor_id) VALUES ('Student', 'e921f0d1-3e63-48ad-bf8f-d45788b8224f', 'jack', '$2a$10$39.uNz2uv6hpqxxLSQmh6.36floztdWAJ./HvlUHcQrZZETWKpZle', '6122872430', null);