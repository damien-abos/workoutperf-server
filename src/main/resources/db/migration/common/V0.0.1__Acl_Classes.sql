INSERT INTO acl(id)
VALUES
  ('Class'),
  ('Class_Contest'),
  ('Class_ContestLeaderboard');

INSERT INTO acl_class(collection, acl_id)
VALUES
  ('Class', 'Class'),
  ('Contest', 'Class_Contest'),
  ('ContestLeaderboard', 'Class_ContestLeaderboard');

INSERT INTO acl_entry(id, permission, sid, acl_id)
VALUES
  ('Class_Everyone', 9, 'Everyone', 'Class'),
  ('Class_Owner', 15, 'Owner', 'Class');