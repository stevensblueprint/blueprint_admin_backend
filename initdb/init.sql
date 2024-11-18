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
       date_joined DATE DEFAULT CURRENT_DATE NOT NULL,
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

   -- Insert sample organizations
   INSERT INTO organizations (name, team_lead_id, project_manager_id) VALUES
   ('Strawberry Fields', NULL, NULL),
   ('Fields of Gold',  NULL, NULL);

   -- Insert sample teams
   INSERT INTO teams (organization_id, name, team_lead_id, project_manager_id, designer_id, date_created) VALUES
   (1, 'Frontend Team', NULL, NULL, NULL, '2021-09-01 00:00:00'),
   (2, 'Backend Team', NULL, NULL, NULL, '2024-10-01 00:00:00');

    -- Insert sample members
    INSERT INTO members (team_id, name, username, email, password, is_active) VALUES
    (1, 'John Doe', 'jdoe', 'jdoe@stevens.edu', 'password', TRUE),
    (2, 'Jane Smith', 'janesmith', 'janesmith@stevens.edu', 'password2', TRUE),
    (2, 'Sophia Johnson', 'sophiaj', 'sophiaj@stevens.edu', 'a1234', FALSE);

    -- Insert sample member roles
    INSERT INTO member_roles (member_id, role_id) VALUES
    (1, 1), -- John Doe as E-BOARD member.
    (1, 5), -- John Doe as PROJECT_MANAGER.
    (2, 2), -- Jane Smith as TEAM_LEAD.
    (3, 3); -- Sophia Johnson as DEVELOPER.

    -- Insert sample blogs
    INSERT INTO blogs (author, title, date_created) VALUES
    ('John Doe', 'Welcome to the Team!', '2021-09-01 00:00:00'),
    ('Jane Smith', 'New Project Launch', '2024-10-01 00:00:00');

    -- Update Team table to set team_lead_id, project_manager_id and designer_id after members have been inserted
    UPDATE teams SET team_lead_id = 2, project_manager_id = 2, designer_id = 3 WHERE id = 1;
    UPDATE teams SET team_lead_id = 1, project_manager_id = 1, designer_id = 3 WHERE id = 2;
    UPDATE organizations SET team_lead_id = 2, project_manager_id = 2 WHERE id = 1;
    UPDATE organizations SET team_lead_id = 1, project_manager_id = 2 WHERE id = 2;

