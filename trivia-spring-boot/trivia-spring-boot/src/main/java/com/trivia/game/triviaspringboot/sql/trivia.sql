--table Answer

create table answer (
	id bigint primary key,
	"text" varchar(200),
	is_correct bool,
	letter varchar(1),
	question_id bigint references question(id) 
);

--table Question

create table question (
	id bigint primary key,
	score integer,
	"level" integer,
	"text" varchar(200)
);

insert into question(id, score, "level", "text") values 
(1, 100, 1, 'Which of these cities is the capital of the Republic of Moldova?'),
(2, 100, 1, 'Who composed the masterpiece "Luceafarul"?'),
(3, 100, 1, 'What is the most dangerous breed of dog?'),
(4, 250, 2, 'What is the most useful platform to study the Java programming language?'),
(5, 250, 2, 'Which of these is NOT an non-access modifier?'),
(6, 250, 2, 'Which of these is NOT a feature and component of Java?'),
(7, 350, 3, 'Where are stored object reference variables?'),
(8, 350, 3, 'Who is awesome??? :)'),
(9, 350, 3, 'Which of these data types have smallest size?');

insert into answer(id, "text", is_correct, letter, question_id) values 
(1, 'Rome', false, 'A', 1),
(2, 'Vienna', false, 'B', 1),
(3, 'Chisinau', true, 'C', 1),
(4, 'Monaco', false, 'D', 1),
(5, 'Shakespeare', false, 'A', 2),
(6, 'Eminescu', true, 'B', 2),
(7, 'Pushkin', false, 'C', 2),
(8, 'Alighieri', false, 'D', 2),
(9, 'chi hua hua', false, 'A', 3),
(10, 'doberman', false, 'B', 3),
(11, 'pit bull', true, 'C', 3),
(12, 'vasea bolea', false, 'D', 3),
(13, 'Facebook', false, 'A', 4),
(14, 'Instagram', false, 'B', 4),
(15, 'Tik Tok', false, 'C', 4),
(16, 'docs.oracle.com/en/java/', true, 'D', 4),
(17, 'abstract', false, 'A', 5),
(18, 'private', true, 'B', 5),
(19, 'static', false, 'C', 5),
(20, 'final', false, 'D', 5),
(21, 'encapsulation', false, 'A', 6),
(22, 'platform independence', false, 'B', 6),
(23, 'drinking beer', true, 'C', 6),
(24, 'object orientation', false, 'D', 6),
(25, 'In Stack memory', false, 'A', 7),
(26, 'In brain', false, 'B', 7),
(27, 'In Heap memory', true, 'C', 7),
(28, 'On the roof', false, 'D', 7),
(29, 'Java', true, 'A', 8),
(30, 'Python', false, 'B', 8),
(31, 'C#', false, 'C', 8),
(32, 'JavaScript', false, 'D', 8),
(33, 'byte', true, 'A', 9),
(34, 'short', false, 'B', 9),
(35, 'int', false, 'C', 9),
(36, 'long', false, 'D', 9);


select * from answer where question_id = 7;

select * from answer where is_correct;

drop table answer;

select text from question where score = 250;

select * from answer where letter = 'B';

update answer set letter = 'E' where letter = 'B';

delete from answer where text = 'Java';