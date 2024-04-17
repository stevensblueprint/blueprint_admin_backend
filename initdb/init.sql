-- Users
create table roles (
	id bigint primary key generated always as identity,
	name varchar(50) not null check (name in ('E-BOARD', 'TEAM_LEAD', 'PRODUCT_MANAGER', 'DEVELOPER', 'BLUEPRINT_INTERNAL_TEAM'))
);

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

-- Events
create table events (
	id bigint primary key generated always as identity,
	name varchar(255) not null,
	description varchar(255) not null,
	location varchar(255) not null,
	budget bigint not null,
	num_of_attendees int not null,
	date timestamp not null default current_timestamp
);

create table forms (
	id bigint primary key generated always as identity,
	name varchar(255) not null,
	email varchar(255) not null,
	date_created timestamp not null default current_timestamp
);

create table event_forms (
	event_id bigint not null,
	form_id bigint not null,
	primary key (event_id, form_id),
	foreign key (event_id) references events(id),
	foreign key (form_id) references forms(id)
);

-- Applications
create table application_forms (
	id bigint primary key generated always as identity,
	name varchar(255) not null,
	graduation_year varchar(255) not null,
	major varchar(255) not null,
	times_applied_previously int not null,
	role varchar(255) not null,
	is_accepted boolean not null
);

create table application_satisfaction_forms (
	id bigint primary key generated always as identity,
	rating int not null,
	body text not null,
	application_form_id bigint unique,
	foreign key (application_form_id) references application_forms(id)
);

-- Blogs
create table blogs (
	id bigint primary key generated always as identity,
	title varchar(255) not null,
	date_published timestamp not null,
	last_modified timestamp not null,
	status varchar(50) not null check (status in ('Published', 'In Progress', 'Deleted')),
	path_to_markdown varchar(255) not null
);

-- Roles Insert
insert into roles (name)
values ('E-BOARD'), ('TEAM_LEAD'), ('PRODUCT_MANAGER'), ('DEVELOPER'), ('BLUEPRINT_INTERNAL_TEAM');

-- Users Insert
insert into users (name, username, email, password, has_blueprint_email, is_enabled, date_joined, team_id) values
('John Doe', 'john_doe', 'john@example.com', 'password123', 'yes', true, CURRENT_TIMESTAMP, null),
('Jane Smith', 'jane_smith', 'jane@example.com', 'password456', 'no', true, CURRENT_TIMESTAMP, null),
('Michael Johnson', 'michael_johnson', 'michael@example.com', 'password789', 'yes', true, CURRENT_TIMESTAMP, null),
('Emily Davis', 'emily_davis', 'emily@example.com', 'passwordabc', 'yes', true, CURRENT_TIMESTAMP, null),
('Chris Wilson', 'chris_wilson', 'chris@example.com', 'passworddef', 'no', true, CURRENT_TIMESTAMP, null),
('Sarah Brown', 'sarah_brown', 'sarah@example.com', 'passwordghi', 'yes', true, CURRENT_TIMESTAMP, null),
('Matthew Taylor', 'matthew_taylor', 'matthew@example.com', 'passwordjkl', 'no', true, CURRENT_TIMESTAMP, null),
('Laura Martinez', 'laura_martinez', 'laura@example.com', 'passwordmno', 'yes', true, CURRENT_TIMESTAMP, null),
('Kevin Rodriguez', 'kevin_rodriguez', 'kevin@example.com', 'passwordpqr', 'yes', true, CURRENT_TIMESTAMP, null),
('Amanda Hernandez', 'amanda_hernandez', 'amanda@example.com', 'passwordstu', 'no', true, CURRENT_TIMESTAMP, null),
('Jason Lopez', 'jason_lopez', 'jason@example.com', 'passwordvwx', 'yes', true, CURRENT_TIMESTAMP, null),
('Michelle Gonzalez', 'michelle_gonzalez', 'michelle@example.com', 'passwordyz', 'no', true, CURRENT_TIMESTAMP, null),
('Daniel Perez', 'daniel_perez', 'daniel@example.com', 'password1234', 'yes', true, CURRENT_TIMESTAMP, null),
('Angela Sanchez', 'angela_sanchez', 'angela@example.com', 'password5678', 'no', true, CURRENT_TIMESTAMP, null),
('Ryan Lee', 'ryan_lee', 'ryan@example.com', 'password9012', 'yes', true, CURRENT_TIMESTAMP, null),
('Tiffany Kim', 'tiffany_kim', 'tiffany@example.com', 'password3456', 'yes', true, CURRENT_TIMESTAMP, null),
('Erica Nguyen', 'erica_nguyen', 'erica@example.com', 'password7890', 'no', true, CURRENT_TIMESTAMP, null),
('Justin Wu', 'justin_wu', 'justin@example.com', 'password123abc', 'yes', true, CURRENT_TIMESTAMP, null),
('Stephanie Chen', 'stephanie_chen', 'stephanie@example.com', 'passworddef456', 'no', true, CURRENT_TIMESTAMP, null),
('Brandon Tran', 'brandon_tran', 'brandon@example.com', 'passwordghi789', 'yes', true, CURRENT_TIMESTAMP, null),
('Amy Chang', 'amy_chang', 'amy@example.com', 'passwordjkl012', 'yes', true, CURRENT_TIMESTAMP, null),
('Jeremy Kim', 'jeremy_kim', 'jeremy@example.com', 'passwordmno345', 'no', true, CURRENT_TIMESTAMP, null),
('Christina Nguyen', 'christina_nguyen', 'christina@example.com', 'passwordpqr678', 'yes', true, CURRENT_TIMESTAMP, null),
('Peter Smith', 'peter_smith', 'peter@example.com', 'passwordstu901', 'no', true, CURRENT_TIMESTAMP, null),
('Nicole Wang', 'nicole_wang', 'nicole@example.com', 'passwordvwx234', 'yes', true, CURRENT_TIMESTAMP, null),
('David Lee', 'david_lee', 'david@example.com', 'passwordyz567', 'yes', true, CURRENT_TIMESTAMP, null),
('Rachel Chen', 'rachel_chen', 'rachel@example.com', 'password1234abc', 'no', true, CURRENT_TIMESTAMP, null),
('Jonathan Kim', 'jonathan_kim', 'jonathan@example.com', 'password5678def', 'yes', true, CURRENT_TIMESTAMP, null),
('Linda Nguyen', 'linda_nguyen', 'linda@example.com', 'password9012ghi', 'yes', true, CURRENT_TIMESTAMP, null),
('Mark Garcia', 'mark_garcia', 'mark@example.com', 'password3456jkl', 'no', true, CURRENT_TIMESTAMP, null),
('Stephanie Martinez', 'stephanie_martinez', 'stephanie@example.com', 'passwordmno789', 'yes', true, CURRENT_TIMESTAMP, null),
('Andrew Johnson', 'andrew_johnson', 'andrew@example.com', 'passwordpqr012', 'yes', true, CURRENT_TIMESTAMP, null),
('Kelly Kim', 'kelly_kim', 'kelly@example.com', 'passwordstu345', 'no', true, CURRENT_TIMESTAMP, null),
('Jeremy Davis', 'jeremy_davis', 'jeremy@example.com', 'passwordvwx678', 'yes', true, CURRENT_TIMESTAMP, null),
('Catherine Lee', 'catherine_lee', 'catherine@example.com', 'passwordyz901', 'yes', true, CURRENT_TIMESTAMP, null),
('Michelle Rodriguez', 'michelle_rodriguez', 'michelle@example.com', 'password1234abc', 'no', true, CURRENT_TIMESTAMP, null),
('Dylan Martinez', 'dylan_martinez', 'dylan@example.com', 'password5678def', 'yes', true, CURRENT_TIMESTAMP, null),
('Brianna Nguyen', 'brianna_nguyen', 'brianna@example.com', 'password9012ghi', 'yes', true, CURRENT_TIMESTAMP, null),
('Austin Kim', 'austin_kim', 'austin@example.com', 'password3456jkl', 'no', true, CURRENT_TIMESTAMP, null),
('Samantha Garcia', 'samantha_garcia', 'samantha@example.com', 'passwordmno789', 'yes', true, CURRENT_TIMESTAMP, null),
('Patrick Johnson', 'patrick_johnson', 'patrick@example.com', 'passwordpqr012', 'yes', true, CURRENT_TIMESTAMP, null),
('Madison Kim', 'madison_kim', 'madison@example.com', 'passwordstu345', 'no', true, CURRENT_TIMESTAMP, null),
('Ethan Davis', 'ethan_davis', 'ethan@example.com', 'passwordvwx678', 'yes', true, CURRENT_TIMESTAMP, null),
('Hannah Lee', 'hannah_lee', 'hannah@example.com', 'passwordyz901', 'yes', true, CURRENT_TIMESTAMP, null),
('Jordan Rodriguez', 'jordan_rodriguez', 'jordan@example.com', 'password1234abc', 'no', true, CURRENT_TIMESTAMP, null),
('Vanessa Martinez', 'vanessa_martinez', 'vanessa@example.com', 'password5678def', 'yes', true, CURRENT_TIMESTAMP, null),
('Kyle Nguyen', 'kyle_nguyen', 'kyle@example.com', 'password9012ghi', 'yes', true, CURRENT_TIMESTAMP, null),
('Alexis Kim', 'alexis_kim', 'alexis@example.com', 'password3456jkl', 'no', true, CURRENT_TIMESTAMP, null),
('Gabriel Garcia', 'gabriel_garcia', 'gabriel@example.com', 'passwordmno789', 'yes', true, CURRENT_TIMESTAMP, null),
('Victoria Johnson', 'victoria_johnson', 'victoria@example.com', 'passwordpqr012', 'yes', true, CURRENT_TIMESTAMP, null),
('Zoe Kim', 'zoe_kim', 'zoe@example.com', 'passwordstu345', 'no', true, CURRENT_TIMESTAMP, null),
('Wyatt Davis', 'wyatt_davis', 'wyatt@example.com', 'passwordvwx678', 'yes', true, CURRENT_TIMESTAMP, null);

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
insert into user_roles (user_id, role_id) VALUES
(1, 1), (1, 4),
(2, 2), (2, 4),
(3, 3), (3, 4),
(4, 4),
(5, 2), (5, 4),
(6, 3), (6, 4),
(7, 4),
(8, 2), (8, 4),
(9, 3), (9, 4),
(10, 4),
(11, 2), (11, 4),
(12, 3), (12, 4),
(13, 4),
(14, 2), (14, 4),
(15, 3), (15, 4),
(16, 4),
(17, 2), (17, 4),
(18, 3), (18, 4),
(19, 4),
(20, 2), (20, 4),
(21, 3), (21, 4),
(22, 4),
(23, 2), (23, 4),
(24, 3), (24, 4),
(25, 4),
(26, 2), (26, 4),
(27, 3), (27, 4),
(28, 4),
(29, 2), (29, 4),
(30, 3), (30, 4),
(31, 4),
(32, 2), (32, 4),
(33, 3), (33, 4),
(34, 4),
(35, 2), (35, 4),
(36, 3), (36, 4),
(37, 4),
(38, 2), (38, 4),
(39, 3), (39, 4),
(40, 4),
(41, 2), (41, 4),
(42, 3), (42, 4),
(43, 4),
(44, 2), (44, 4),
(45, 3), (45, 4),
(46, 4),
(47, 2), (47, 4),
(48, 3), (48, 4),
(49, 4),
(50, 2), (50, 4);

-- Update Users with Team Ids
update users
set team_id = floor(1 + random() * 7);

-- Add Foreign Key Constraints to Users and Teams
alter table users
add constraint fk_team_id
foreign key (team_id) references teams(id);

alter table teams
add constraint fk_team_lead_id
foreign key (team_lead_id) references users(id);

alter table teams
add constraint fk_team_manager_id
foreign key (team_manager_id) references users(id);

-- Events Insert
insert into events (name, description, location, budget, num_of_attendees, date) values
('Event 1', 'Description for Event 1', 'Location for Event 1', 1000, 50, CURRENT_TIMESTAMP),
('Event 2', 'Description for Event 2', 'Location for Event 2', 1500, 60, CURRENT_TIMESTAMP),
('Event 3', 'Description for Event 3', 'Location for Event 3', 2000, 70, CURRENT_TIMESTAMP),
('Event 4', 'Description for Event 4', 'Location for Event 4', 2500, 80, CURRENT_TIMESTAMP),
('Event 5', 'Description for Event 5', 'Location for Event 5', 3000, 90, CURRENT_TIMESTAMP),
('Event 6', 'Description for Event 6', 'Location for Event 6', 3500, 100, CURRENT_TIMESTAMP),
('Event 7', 'Description for Event 7', 'Location for Event 7', 4000, 110, CURRENT_TIMESTAMP),
('Event 8', 'Description for Event 8', 'Location for Event 8', 4500, 120, CURRENT_TIMESTAMP),
('Event 9', 'Description for Event 9', 'Location for Event 9', 5000, 130, CURRENT_TIMESTAMP),
('Event 10', 'Description for Event 10', 'Location for Event 10', 5500, 140, CURRENT_TIMESTAMP),
('Event 11', 'Description for Event 11', 'Location for Event 11', 6000, 150, CURRENT_TIMESTAMP),
('Event 12', 'Description for Event 12', 'Location for Event 12', 6500, 160, CURRENT_TIMESTAMP),
('Event 13', 'Description for Event 13', 'Location for Event 13', 7000, 170, CURRENT_TIMESTAMP),
('Event 14', 'Description for Event 14', 'Location for Event 14', 7500, 180, CURRENT_TIMESTAMP),
('Event 15', 'Description for Event 15', 'Location for Event 15', 8000, 190, CURRENT_TIMESTAMP),
('Event 16', 'Description for Event 16', 'Location for Event 16', 8500, 200, CURRENT_TIMESTAMP),
('Event 17', 'Description for Event 17', 'Location for Event 17', 9000, 210, CURRENT_TIMESTAMP),
('Event 18', 'Description for Event 18', 'Location for Event 18', 9500, 220, CURRENT_TIMESTAMP),
('Event 19', 'Description for Event 19', 'Location for Event 19', 10000, 230, CURRENT_TIMESTAMP),
('Event 20', 'Description for Event 20', 'Location for Event 20', 10500, 240, CURRENT_TIMESTAMP),
('Event 21', 'Description for Event 21', 'Location for Event 21', 11000, 250, CURRENT_TIMESTAMP),
('Event 22', 'Description for Event 22', 'Location for Event 22', 11500, 260, CURRENT_TIMESTAMP),
('Event 23', 'Description for Event 23', 'Location for Event 23', 12000, 270, CURRENT_TIMESTAMP),
('Event 24', 'Description for Event 24', 'Location for Event 24', 12500, 280, CURRENT_TIMESTAMP),
('Event 25', 'Description for Event 25', 'Location for Event 25', 13000, 290, CURRENT_TIMESTAMP);

-- Forms Insert
insert into forms (name, email, date_created) values
('Form 1', 'form1@example.com', CURRENT_TIMESTAMP),
('Form 2', 'form2@example.com', CURRENT_TIMESTAMP),
('Form 3', 'form3@example.com', CURRENT_TIMESTAMP),
('Form 4', 'form4@example.com', CURRENT_TIMESTAMP),
('Form 5', 'form5@example.com', CURRENT_TIMESTAMP),
('Form 6', 'form6@example.com', CURRENT_TIMESTAMP),
('Form 7', 'form7@example.com', CURRENT_TIMESTAMP),
('Form 8', 'form8@example.com', CURRENT_TIMESTAMP),
('Form 9', 'form9@example.com', CURRENT_TIMESTAMP),
('Form 10', 'form10@example.com', CURRENT_TIMESTAMP),
('Form 11', 'form11@example.com', CURRENT_TIMESTAMP),
('Form 12', 'form12@example.com', CURRENT_TIMESTAMP),
('Form 13', 'form13@example.com', CURRENT_TIMESTAMP),
('Form 14', 'form14@example.com', CURRENT_TIMESTAMP),
('Form 15', 'form15@example.com', CURRENT_TIMESTAMP),
('Form 16', 'form16@example.com', CURRENT_TIMESTAMP),
('Form 17', 'form17@example.com', CURRENT_TIMESTAMP),
('Form 18', 'form18@example.com', CURRENT_TIMESTAMP),
('Form 19', 'form19@example.com', CURRENT_TIMESTAMP),
('Form 20', 'form20@example.com', CURRENT_TIMESTAMP),
('Form 21', 'form21@example.com', CURRENT_TIMESTAMP),
('Form 22', 'form22@example.com', CURRENT_TIMESTAMP),
('Form 23', 'form23@example.com', CURRENT_TIMESTAMP),
('Form 24', 'form24@example.com', CURRENT_TIMESTAMP),
('Form 25', 'form25@example.com', CURRENT_TIMESTAMP);

-- Event Forms Insert
insert into event_forms (event_id, form_id) values
(1, 1), (1, 2),
(2, 3),
(3, 4),
(4, 5), (4, 6),
(5, 7),
(6, 8),
(7, 9), (7, 10),
(8, 11),
(9, 12),
(10, 13), (10, 14),
(11, 15),
(12, 16),
(13, 17), (13, 18),
(14, 19),
(15, 20),
(16, 21), (16, 22),
(17, 23),
(18, 24),
(19, 25), (19, 1),
(20, 2),
(21, 3), (21, 4),
(22, 5),
(23, 6),
(24, 7), (24, 8),
(25, 9);

-- Application Form Insert
insert into application_forms (name, graduation_year, major, times_applied_previously, role, is_accepted) values
('Applicant 1', '2023', 'Computer Science', 0, 'Developer', true),
('Applicant 2', '2022', 'Electrical Engineering', 1, 'Product Manager', true),
('Applicant 3', '2024', 'Mechanical Engineering', 0, 'Developer', false),
('Applicant 4', '2023', 'Chemical Engineering', 2, 'Team Lead', true),
('Applicant 5', '2025', 'Civil Engineering', 1, 'Developer', false),
('Applicant 6', '2023', 'Computer Science', 0, 'Product Manager', true),
('Applicant 7', '2022', 'Electrical Engineering', 0, 'Developer', false),
('Applicant 8', '2024', 'Mechanical Engineering', 1, 'Team Lead', true),
('Applicant 9', '2022', 'Computer Engineering', 0, 'Developer', true),
('Applicant 10', '2023', 'Information Technology', 2, 'Product Manager', false),
('Applicant 11', '2024', 'Computer Science', 0, 'Developer', true),
('Applicant 12', '2023', 'Electrical Engineering', 1, 'Team Lead', true),
('Applicant 13', '2022', 'Mechanical Engineering', 0, 'Developer', false),
('Applicant 14', '2025', 'Computer Engineering', 1, 'Product Manager', false),
('Applicant 15', '2023', 'Civil Engineering', 2, 'Developer', true),
('Applicant 16', '2022', 'Chemical Engineering', 0, 'Team Lead', true),
('Applicant 17', '2024', 'Information Technology', 1, 'Developer', false),
('Applicant 18', '2022', 'Electrical Engineering', 0, 'Product Manager', true),
('Applicant 19', '2023', 'Mechanical Engineering', 2, 'Developer', false),
('Applicant 20', '2024', 'Computer Science', 0, 'Team Lead', true),
('Applicant 21', '2023', 'Electrical Engineering', 1, 'Developer', true),
('Applicant 22', '2022', 'Computer Engineering', 0, 'Product Manager', false),
('Applicant 23', '2025', 'Civil Engineering', 1, 'Developer', false),
('Applicant 24', '2023', 'Chemical Engineering', 0, 'Team Lead', true),
('Applicant 25', '2022', 'Information Technology', 2, 'Developer', true);

-- Satisifcation Forms
insert into application_satisfaction_forms (rating, body, application_form_id) values
(5, 'I am very satisfied with the application process.', 1),
(4, 'The application process was smooth, but I had some minor issues.', 2),
(3, 'I encountered several difficulties during the application process.', 3),
(4, 'Overall, I am satisfied with the application process.', 4),
(5, 'The application process exceeded my expectations.', 5),
(3, 'The application process needs improvement in certain areas.', 6),
(4, 'I found the application process satisfactory.', 7),
(5, 'The application process was excellent.', 8),
(2, 'I am dissatisfied with the application process.', 9),
(4, 'The application process met my expectations.', 10),
(5, 'The application process was straightforward and easy to follow.', 11),
(3, 'I faced some challenges during the application process.', 12),
(4, 'Overall, the application process was good.', 13),
(4, 'I found the application process satisfactory.', 14),
(5, 'The application process was excellent.', 15),
(2, 'I am dissatisfied with the application process.', 16),
(4, 'The application process met my expectations.', 17),
(5, 'The application process was straightforward and easy to follow.', 18),
(3, 'I faced some challenges during the application process.', 19),
(4, 'Overall, the application process was good.', 20),
(4, 'I found the application process satisfactory.', 21),
(5, 'The application process was excellent.', 22),
(2, 'I am dissatisfied with the application process.', 23),
(4, 'The application process met my expectations.', 24),
(5, 'The application process was straightforward and easy to follow.', 25);

-- Blogs
insert into blogs (title, date_published, last_modified, status, path_to_markdown) values
('Blog 1', '2024-03-10 08:00:00', '2024-03-10 08:00:00', 'Published', '/path/to/blog1.md'),
('Blog 2', '2024-02-15 10:30:00', '2024-04-01 09:45:00', 'In Progress', '/path/to/blog2.md'),
('Blog 3', '2024-01-20 14:15:00', '2024-03-25 11:20:00', 'Published', '/path/to/blog3.md'),
('Blog 4', '2023-12-25 16:45:00', '2024-04-10 14:00:00', 'Deleted', '/path/to/blog4.md'),
('Blog 5', '2023-11-30 18:20:00', '2024-02-20 16:30:00', 'Published', '/path/to/blog5.md'),
('Blog 6', '2023-10-05 20:00:00', '2024-01-15 18:00:00', 'In Progress', '/path/to/blog6.md'),
('Blog 7', '2023-09-10 22:30:00', '2024-03-05 21:15:00', 'Published', '/path/to/blog7.md'),
('Blog 8', '2023-08-15 09:15:00', '2024-04-05 23:30:00', 'In Progress', '/path/to/blog8.md'),
('Blog 9', '2023-07-20 11:45:00', '2024-02-10 10:00:00', 'Published', '/path/to/blog9.md'),
('Blog 10', '2023-06-25 13:20:00', '2024-01-05 12:15:00', 'In Progress', '/path/to/blog10.md'),
('Blog 11', '2023-05-30 15:00:00', '2024-03-20 14:30:00', 'Published', '/path/to/blog11.md'),
('Blog 12', '2023-04-05 17:30:00', '2024-02-25 16:45:00', 'In Progress', '/path/to/blog12.md'),
('Blog 13', '2023-03-10 19:45:00', '2024-04-15 18:00:00', 'Published', '/path/to/blog13.md'),
('Blog 14', '2023-02-15 08:30:00', '2024-03-01 20:45:00', 'In Progress', '/path/to/blog14.md'),
('Blog 15', '2023-01-20 10:00:00', '2024-02-05 22:30:00', 'Published', '/path/to/blog15.md');