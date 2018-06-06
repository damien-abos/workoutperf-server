--
-- acl
--

CREATE TABLE public.acl (
  id character varying(255) NOT NULL,
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- acl_class
--

CREATE TABLE public.acl_class (
  collection character varying(255) NOT NULL,
  acl_id character varying(255),
  PRIMARY KEY (collection)
);

-- --------------------------------------------------------

--
-- acl_entry
--

CREATE TABLE public.acl_entry (
  id character varying(255) NOT NULL,
  permission integer NOT NULL,
  sid character varying(255),
  acl_id character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- contest
--

CREATE TABLE public.contest (
  id character varying(255) NOT NULL,
  description character varying(255),
  name character varying(255),
  begin_period timestamp without time zone,
  end_period timestamp without time zone,
  acl_id character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- contestposition_workoutposition
--

CREATE TABLE contestposition_workoutposition (
  workoutposition_id character varying(255) NOT NULL,
  contestposition_id character varying(255) NOT NULL,
  PRIMARY KEY (workoutposition_id,contestposition_id)
);

-- --------------------------------------------------------

--
-- contest_divisions
--

CREATE TABLE public.contest_divisions (
  contest_id character varying(255) NOT NULL,
  division_id character varying(255) NOT NULL,
  PRIMARY KEY (contest_id,division_id)
);

-- --------------------------------------------------------

--
-- contest_judges
--

CREATE TABLE public.contest_judges (
  contest_id character varying(255) NOT NULL,
  person_id character varying(255) NOT NULL,
  PRIMARY KEY (contest_id,person_id)
);

-- --------------------------------------------------------

--
-- contest_leaderboard
--

CREATE TABLE public.contest_leaderboard (
  id character varying(255) NOT NULL,
  begin_period timestamp without time zone,
  end_period timestamp without time zone,
  acl_id character varying(255),
  contest_id character varying(255),
  division_id character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- contest_members
--

CREATE TABLE public.contest_members (
  contest_id character varying(255) NOT NULL,
  person_id character varying(255) NOT NULL,
  PRIMARY KEY (contest_id,person_id)
);

-- --------------------------------------------------------

--
-- contest_organizers
--

CREATE TABLE public.contest_organizers (
  contest_id character varying(255) NOT NULL,
  person_id character varying(255) NOT NULL,
  PRIMARY KEY (contest_id,person_id)
);

-- --------------------------------------------------------

--
-- contest_position
--

CREATE TABLE public.contest_position (
  id character varying(255) NOT NULL,
  points double precision NOT NULL,
  rank integer NOT NULL,
  acl_id character varying(255),
  athlete_id character varying(255),
  leaderboard_id character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- group_members
--

CREATE TABLE public.group_members (
  group_id character varying(255) NOT NULL,
  person_id character varying(255) NOT NULL,
  PRIMARY KEY (group_id,person_id)
);

-- --------------------------------------------------------

--
-- location
--

CREATE TABLE public.location (
  id character varying(255) NOT NULL,
  address character varying(255),
  latitude double precision,
  longitude double precision,
  name character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- people_group
--

CREATE TABLE public.people_group (
  id character varying(255) NOT NULL,
  name character varying(255),
  acl_id character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- performance
--

CREATE TABLE public.performance (
  id character varying(255) NOT NULL,
  comment character varying(255),
  date timestamp without time zone,
  duration bigint,
  score integer NOT NULL,
  acl_id character varying(255),
  athlete_id character varying(255),
  judge_id character varying(255),
  workout_id character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- person
--

CREATE TABLE public.person (
  id character varying(255) NOT NULL,
  birthday date,
  gender character varying(255),
  name character varying(255),
  acl_id character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- workout
--

CREATE TABLE public.workout (
  id character varying(255) NOT NULL,
  description character varying(255),
  name character varying(255),
  begin_period timestamp without time zone,
  end_period timestamp without time zone,
  type character varying(255),
  weight double precision NOT NULL,
  acl_id character varying(255),
  contest_id character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- workout_leaderboard
--

CREATE TABLE public.workout_leaderboard (
  id character varying(255) NOT NULL,
  begin_period timestamp without time zone,
  end_period timestamp without time zone,
  acl_id character varying(255),
  division_id character varying(255),
  workout_id character varying(255),
  PRIMARY KEY (id)
);

-- --------------------------------------------------------

--
-- workout_position
--

CREATE TABLE public.workout_position (
  id character varying(255) NOT NULL,
  points double precision NOT NULL,
  rank integer NOT NULL,
  athlete_id character varying(255),
  leaderboard_id character varying(255),
  performance_id character varying(255),
  workout_id character varying(255),
  PRIMARY KEY (id)
);
