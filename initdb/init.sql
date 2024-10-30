    -- Enum type for roles
    CREATE TYPE member_role AS ENUM ('E-BOARD', 'TEAM_LEAD', 'PRODUCT_MANAGER', 'DEVELOPER', 'BLUEPRINT_INTERNAL_TEAM');

    -- Create organization table
    CREATE TABLE Organization (
        orgId INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
        orgName VARCHAR(255) NOT NULL,
        PRIMARY KEY (orgId)
    );

    -- Create Team table
    CREATE TABLE Team (
        teamId INTEGER GENERATED ALWAYS AS IDENTITY,
        orgId INTEGER NOT NULL,
        teamName VARCHAR(255),
        teamLeadId INTEGER,
        projectManagerId INTEGER,
        dateCreated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
        PRIMARY KEY (teamId),
        FOREIGN KEY (orgId) REFERENCES Organization(orgId)
    );

    -- Create Member table
    CREATE TABLE Member (
        memberId INTEGER GENERATED ALWAYS AS IDENTITY,
        teamId INTEGER,
        name VARCHAR(255) NOT NULL,
        username VARCHAR(255) NOT NULL,
        email VARCHAR(255) UNIQUE,
        password VARCHAR(255) NOT NULL,
        role member_role,
        isActive BOOLEAN,
        dateJoined TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (memberId)
    );

    ALTER TABLE Member
    ADD CONSTRAINT fk_teamId FOREIGN KEY (teamId) REFERENCES Team(teamId);

    ALTER TABLE Team
    ADD CONSTRAINT fk_teamLeadId FOREIGN KEY (teamLeadId) REFERENCES Member(memberId),
    ADD CONSTRAINT fk_projectManagerId FOREIGN KEY (projectManagerId) REFERENCES Member(memberId);

    CREATE INDEX idx_member_teamid ON Member(teamId);
    CREATE INDEX idx_team_orgid ON Team(orgId);

    -- Insert sample organizations
    INSERT INTO Organization (orgName) VALUES
    ('Strawberry Fields'),
    ('Fields of Gold');

    -- Insert sample teams
    INSERT INTO Team (orgId, teamName, teamLeadId, projectManagerId, dateCreated) VALUES
    (1, 'TeachTeam', NULL, NULL, '2021-09-01 00:00:00'),
    (2, 'Backend Team', NULL, NULL, '2024-10-01 00:00:00');

    -- Insert sample members
    INSERT INTO Member (teamId, name, username, email, password, role, isActive) VALUES
    (1, 'John Doe', 'jdoe', 'jdoe@stevens.edu', 'password', 'E-BOARD', TRUE),
    (2, 'Jane Smith', 'janesmith', 'janesmith@stevens.edu', 'password2', 'TEAM_LEAD', TRUE),
    (2, 'Alice Johnson', 'alicej', 'alicej@stevens.edu', 'a1234', 'DEVELOPER', FALSE);

    -- Update Team table to set teamLeadId and projectManagerId after members have been inserted
    UPDATE Team SET teamLeadId = 2, projectManagerId = 2 WHERE teamId = 1;
    UPDATE Team SET teamLeadId = 1, projectManagerId = 1 WHERE teamId = 2;
