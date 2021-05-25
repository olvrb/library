CREATE TABLE user_supervisees (
    employee_id    VARCHAR(255) NOT NULL,
    supervisees_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (employee_id, supervisees_id),
    CONSTRAINT UK_fdac4pcr6vhgpx624vki6pwk0
        UNIQUE (supervisees_id),
    CONSTRAINT FKfckhqw3474f1k4sbafkal9l1t
        FOREIGN KEY (supervisees_id) REFERENCES user (id),
    CONSTRAINT FKqjcpmsvdp4m1p2t4wpk3xjpkp
        FOREIGN KEY (employee_id) REFERENCES user (id)
);

