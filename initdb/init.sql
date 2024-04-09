-- Users
create table roles (
	id bigint primary key generated always as identity,
	name varchar(50) not null check (name in ('E-BOARD', 'TEAM_LEAD', 'PRODUCT_MANAGER', 'DEVELOPER', 'BLUEPRINT_INTERNAL_TEAM'))
)

create table users (
	id bigint primary key generated always as identity, 
	name varchar(255) not null,
	username varchar(255) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	has_blueprint_email varchar(255) not null,
	is_enabled boolean not null,
	date_joined timestamp not null default current_timestamp,
	team_id bigint,
	foreign key (team_id) references teams(id)
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
	date_created timestamp not null default current_timestamp,
	team_class int not null,
	foreign key (team_lead_id) references users(id),
	foreign key (team_manager_id) references users(id)
);

-- Events
create table events (
	id bigint primary key generated always as identity,
	name varchar(255) not null,
	description varchar(255) not null,
	location varchar(255) not null,
	budget bigint not null,
	num_of_attendees int not null,
	date timestamp not null default current_timestamp,
);

create table forms (
	id bigint primary key generated always as identity,
	name varchar(255) not null,
	email varchar(255) not null,
	date_created timestamp not null default current_timestamp,
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
	times_applied_prviously int not null,
	role varchar(255) not null,
	is_accepted boolean not null,
);

create table application_satisfaction_forms (
	id bigint primary key generated always as identity,
	satisfactionForm bigint,
	rating int not null,
	body varchar(255) not null,
	application_form_id bigint,
	foreign key (application_form_id) references application_form(id) unique
);