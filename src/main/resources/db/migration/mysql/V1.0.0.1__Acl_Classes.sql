INSERT INTO acl(id)
VALUES ('Class_Contest'),
       ('Class_ContestLeaderboard');

INSERT INTO acl_class(collection, acl_id)
VALUES ('Contest', 'Class_Contest'),
       ('ContestLeaderboard', 'Class_ContestLeaderboard');
