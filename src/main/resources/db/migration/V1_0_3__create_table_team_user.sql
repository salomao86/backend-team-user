CREATE TABLE team_user
(
    user_id     UUID            NOT NULL,
    team_id     UUID            NOT NULL,
    role_id     UUID            NOT NULL,
    CONSTRAINT FK_TEAM_USER_USER foreign key (user_id) references user(id),
    CONSTRAINT FK_TEAM_USER_TEAM foreign key (team_id) references team(id),
    CONSTRAINT FK_TEAM_USER_ROLE foreign key (role_id) references user_role(id),
    CONSTRAINT UK_TEAM_USER UNIQUE (user_id, team_id)
);
