-- Create roles table
CREATE TABLE roles (
                       id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       PRIMARY key(id)
);

-- Insert roles into roles table
INSERT INTO roles (name) VALUES
                             ('E-BOARD'),
                             ('TEAM_LEAD'),
                             ('DEVELOPER'),
                             ('TECH_TEAM'),
                             ('PROJECT_MANAGER');

-- Create members table
CREATE TABLE members (
                         id BIGINT GENERATED ALWAYS AS IDENTITY,
                         team_id BIGINT,
                         name VARCHAR(255) NOT NULL,
                         username VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         is_active BOOLEAN,
                         date_joined TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                         PRIMARY KEY (id)
);

-- Create organizations table
CREATE TABLE organizations (
                               id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
                               name VARCHAR(255) NOT NULL,
                               team_lead_id BIGINT,
                               project_manager_id BIGINT,
                               PRIMARY KEY (id),
                               FOREIGN KEY (team_lead_id) REFERENCES members(id),
                               FOREIGN KEY (project_manager_id) REFERENCES members(id)
);

-- Create teams table
CREATE TABLE teams (
                       id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
                       organization_id BIGINT NOT NULL,
                       name VARCHAR(255),
                       team_lead_id BIGINT,
                       project_manager_id BIGINT,
                       designer_id BIGINT,
                       date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       PRIMARY KEY (id),
                       FOREIGN KEY (organization_id) REFERENCES organizations(id),
                       FOREIGN KEY (team_lead_id) REFERENCES members(id),
                       FOREIGN KEY (project_manager_id) REFERENCES members(id),
                       FOREIGN KEY (designer_id) REFERENCES members(id)
);

-- Create member_roles table (to handle many-to-many relationship between members and roles)
CREATE TABLE member_roles (
                              member_id BIGINT NOT NULL,
                              role_id BIGINT NOT NULL,
                              PRIMARY KEY (member_id, role_id),
                              FOREIGN KEY (member_id) REFERENCES members(id),
                              FOREIGN KEY (role_id) REFERENCES roles(id)
);

--Create blogs table
CREATE TABLE blogs (
                       id BIGINT GENERATED ALWAYS AS IDENTITY,
                       author VARCHAR(255) NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       PRIMARY KEY (id)
);

ALTER TABLE members
    ADD CONSTRAINT fk_team_id FOREIGN KEY (team_id) REFERENCES teams(id);