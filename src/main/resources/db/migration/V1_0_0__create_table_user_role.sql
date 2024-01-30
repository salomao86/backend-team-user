CREATE TABLE user_role
(
    id          UUID            NOT NULL PRIMARY KEY,
    name        VARCHAR(20)     NOT NULL,
    CONSTRAINT  UK_USER_ROLE UNIQUE (name)
);

INSERT INTO user_role(id, name) 
VALUES('a921bf00-0078-4e77-bad6-88cef3f4c732', 'DEVELOPER');

INSERT INTO user_role(id, name) 
VALUES('63cd95a1-d64c-4429-9ff0-7423d1168cb9', 'PRODUCT OWNER');

INSERT INTO user_role(id, name) 
VALUES('ae7a6dba-a074-4df6-bc64-6c8776d43210', 'TESTER');

INSERT INTO user_role(id, name) 
VALUES('539c9790-2779-48c0-8afb-e9ae93e473c3', 'TEAM LEADER');