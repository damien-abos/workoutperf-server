--
-- acl
--

CREATE TABLE acl (
  id varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- acl_class
--

CREATE TABLE acl_class (
  collection varchar(255) NOT NULL,
  acl_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (collection)
);

-- --------------------------------------------------------

--
-- acl_entry
--

CREATE TABLE acl_entry (
  id varchar(255) NOT NULL,
  permission int(11) NOT NULL,
  sid varchar(255) DEFAULT NULL,
  acl_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- contest
--

CREATE TABLE contest (
  id varchar(255) NOT NULL,
  description varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  begin datetime DEFAULT NULL,
  end datetime DEFAULT NULL,
  acl_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- contestposition_workoutposition
--

CREATE TABLE contestposition_workoutposition (
  workoutposition_id varchar(255) NOT NULL,
  contestposition_id varchar(255) NOT NULL,
  PRIMARY KEY (workoutposition_id,contestposition_id)
);

-- --------------------------------------------------------

--
-- contest_divisions
--

CREATE TABLE contest_divisions (
  contest_id varchar(255) NOT NULL,
  division_id varchar(255) NOT NULL,
  PRIMARY KEY (contest_id,division_id)
);

-- --------------------------------------------------------

--
-- contest_judges
--

CREATE TABLE contest_judges (
  contest_id varchar(255) NOT NULL,
  person_id varchar(255) NOT NULL,
  PRIMARY KEY (contest_id,person_id)
);

-- --------------------------------------------------------

--
-- contest_leaderboard
--

CREATE TABLE contest_leaderboard (
  id varchar(255) NOT NULL,
  begin datetime DEFAULT NULL,
  end datetime DEFAULT NULL,
  acl_id varchar(255) DEFAULT NULL,
  contest_id varchar(255) DEFAULT NULL,
  division_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- contest_members
--

CREATE TABLE contest_members (
  contest_id varchar(255) NOT NULL,
  person_id varchar(255) NOT NULL,
  PRIMARY KEY (contest_id,person_id)
);

-- --------------------------------------------------------

--
-- contest_organizers
--

CREATE TABLE contest_organizers (
  contest_id varchar(255) NOT NULL,
  person_id varchar(255) NOT NULL,
  PRIMARY KEY (contest_id,person_id)
);

-- --------------------------------------------------------

--
-- contest_position
--

CREATE TABLE contest_position (
  id varchar(255) NOT NULL,
  points double NOT NULL,
  rank int(11) NOT NULL,
  acl_id varchar(255) DEFAULT NULL,
  athlete_id varchar(255) DEFAULT NULL,
  leaderboard_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- contest_workouts
--

CREATE TABLE contest_workouts (
  contest_id varchar(255) NOT NULL,
  workouts_id varchar(255) NOT NULL,
  PRIMARY KEY (contest_id,workouts_id)
);

-- --------------------------------------------------------

--
-- group_members
--

CREATE TABLE group_members (
  group_id varchar(255) NOT NULL,
  person_id varchar(255) NOT NULL,
  PRIMARY KEY (group_id,person_id)
);

-- --------------------------------------------------------

--
-- location
--

CREATE TABLE location (
  id varchar(255) NOT NULL,
  address varchar(255) DEFAULT NULL,
  latitude double DEFAULT NULL,
  longitude double DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- people_group
--

CREATE TABLE people_group (
  id varchar(255) NOT NULL,
  name varchar(255) DEFAULT NULL,
  acl_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- performance
--

CREATE TABLE performance (
  id varchar(255) NOT NULL,
  comment varchar(255) DEFAULT NULL,
  date datetime DEFAULT NULL,
  duration bigint(20) DEFAULT NULL,
  score int(11) NOT NULL,
  acl_id varchar(255) DEFAULT NULL,
  athlete_id varchar(255) DEFAULT NULL,
  judge_id varchar(255) DEFAULT NULL,
  workout_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- person
--

CREATE TABLE person (
  id varchar(255) NOT NULL,
  birthday date DEFAULT NULL,
  gender varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  acl_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- workout
--

CREATE TABLE workout (
  id varchar(255) NOT NULL,
  description varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  begin datetime DEFAULT NULL,
  end datetime DEFAULT NULL,
  type varchar(255) DEFAULT NULL,
  weight double NOT NULL,
  acl_id varchar(255) DEFAULT NULL,
  contest_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- workout_leaderboard
--

CREATE TABLE workout_leaderboard (
  id varchar(255) NOT NULL,
  begin datetime DEFAULT NULL,
  end datetime DEFAULT NULL,
  acl_id varchar(255) DEFAULT NULL,
  division_id varchar(255) DEFAULT NULL,
  workout_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- workout_position
--

CREATE TABLE workout_position (
  id varchar(255) NOT NULL,
  points double NOT NULL,
  rank int(11) NOT NULL,
  athlete_id varchar(255) DEFAULT NULL,
  leaderboard_id varchar(255) DEFAULT NULL,
  performance_id varchar(255) DEFAULT NULL,
  workout_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
