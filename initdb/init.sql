-- Users
create table roles (
	id bigint primary key generated always as identity,
	name varchar(50) not null check (name in ('E-BOARD', 'TEAM_LEAD', 'PRODUCT_MANAGER', 'DEVELOPER', 'BLUEPRINT_INTERNAL_TEAM'))
);

-- Roles Insert
insert into roles (name)
values ('E-BOARD'), ('TEAM_LEAD'), ('PRODUCT_MANAGER'), ('DEVELOPER'), ('BLUEPRINT_INTERNAL_TEAM');

create table users (
	id bigint primary key generated always as identity, 
	name varchar(255) not null,
	username varchar(255) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	has_blueprint_email varchar(255) not null,
	is_enabled boolean not null,
	date_joined timestamp not null,
	team_id bigint
);

create table user_roles (
	user_id bigint,
	role_id bigint,
	primary key(user_id, role_id),
	foreign key (user_id) references users(id),
	foreign key (role_id) references roles(id)
);

create table teams (
	id bigint primary key generated always as identity,
	name varchar(255) not null,
	team_lead_id bigint,
	team_manager_id bigint,
	date_created timestamp not null,
	team_class int not null
);

create table npos (
    id bigint primary key generated always as identity,
    name varchar(255) not null,
    foreign key (team_id) references teams(id),
    project_proposal_url varchar(255) not null,
    date_of_recruitment timestamp not null
)

create table blogs  (
    id bigint primary key generated always as identity,
    author varchar(255) not null,
    title varchar(255) not null,
    date_created timestamp not null
)

create table user_attendance (
    id bigint primary key generated always as identity,
    date timestamp not null,
    status boolean not null,
)

alter table users
    add constraint fk_team_id
        foreign key (team_id) references teams(id);

alter table teams
    add constraint fk_team_lead_id
        foreign key (team_lead_id) references users(id);

alter table teams
    add constraint fk_team_manager_id
        foreign key (team_manager_id) references users(id);


-- Insert users into the users table
insert into users (name, username, email, password, has_blueprint_email, is_enabled, date_joined, team_id) values
('John Doe', 'john_doe', 'john@example.com', 'password123', 'yes', true, CURRENT_TIMESTAMP, null),
('Jane Smith', 'jane_smith', 'jane@example.com', 'password456', 'no', true, CURRENT_TIMESTAMP, null),
('Michael Johnson', 'michael_johnson', 'michael@example.com', 'password789', 'yes', true, CURRENT_TIMESTAMP, null);

-- Teams Insert
insert into teams (name, team_lead_id, team_manager_id, date_created, team_class) values
('Team Alpha', 1, 2, CURRENT_TIMESTAMP, 1),
('Team Beta', 3, 4, CURRENT_TIMESTAMP, 2),
('Team Gamma', 5, 6, CURRENT_TIMESTAMP, 1),
('Team Delta', 7, 8, CURRENT_TIMESTAMP, 2),
('Team Epsilon', 9, 10, CURRENT_TIMESTAMP, 1),
('Team Zeta', 11, 12, CURRENT_TIMESTAMP, 2),
('Team Theta', 13, 14, CURRENT_TIMESTAMP, 1);

-- User_Roles Insert
insert into user_roles (user_id, role_id) values
(1, 1), -- John Doe with E-BOARD role
(2, 2), -- Jane Smith with TEAM_LEAD role
(3, 3); -- Michael Johnson with PRODUCT_MANAGER role