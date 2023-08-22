BEGIN TRANSACTION;

DROP TABLE IF EXISTS result, quiz_question, question, quiz, users CASCADE;

CREATE TABLE users(
    user_id serial,
    name varchar(30) NOT NULL,
    quiz_taken_count int NULL,
    password varchar(200) NOT NULL,
    user_email varchar(50) NULL,
    CONSTRAINT PK_users PRIMARY KEY(user_id),
    CONSTRAINT UQ_name UNIQUE(name)
);

CREATE TABLE quiz(
    quiz_id serial,
    quiz_name varchar(50) NOT NULL,
    num_of_questions int NOT NULL,
    type varchar(50) NOT NULL,
    difficulty varchar(15) NULL,
    CONSTRAINT PK_quiz PRIMARY KEY(quiz_id),
    CONSTRAINT UQ_quiz_name UNIQUE(quiz_name),
    CONSTRAINT CHK_type CHECK (type IN ('Multiple Choice', 'True or False', 'Math')),
    CONSTRAINT CHK_difficulty CHECK (difficulty IN ('Easy', 'Medium', 'Hard', 'Challenging')),
    CONSTRAINT CHK_num_of_questions CHECK(num_of_questions > 0)
);

CREATE TABLE question(
    question_id serial,
    question varchar(150),
    answer_choice_one varchar(50) NOT NULL,
    answer_choice_two varchar(50) NULL,
    answer_choice_three varchar(50) NULL,
    answer_choice_four varchar(50) NULL,
    correct_answer_choice int NOT NULL,
    CONSTRAINT PK_question_id PRIMARY KEY(question_id),
    CONSTRAINT UQ_question UNIQUE(question),
    CONSTRAINT CHK_correct_answer_choice CHECK(correct_answer_choice BETWEEN 0 AND 5)
);

CREATE TABLE quiz_question(
    quiz_id int NOT NULL,
    question_id int NOT NULL,
    CONSTRAINT PK_quiz_question PRIMARY KEY(quiz_id, question_id),
    CONSTRAINT FK_quiz_id FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id),
    CONSTRAINT FK_question_id FOREIGN KEY (question_id) REFERENCES question(question_id)
);

CREATE TABLE result(
    result_id serial,
    quiz_id int NOT NULL,
    user_id int NOT NULL,
    score int NULL,
    quiz_timestamp timestamp NOT NULL,
    CONSTRAINT PK_quiz_user_score PRIMARY KEY(quiz_id, user_id, score),
    CONSTRAINT FK_quiz_id FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT CHK_score CHECK(score <= 100 AND score >= 0)
);

--base users for initial tests
INSERT INTO users(name, quiz_taken_count, password, user_email) VALUES ('Mr. Bubble', 0, 'popME451', 'jsmith@yachtmail.kom');
INSERT INTO users(name, quiz_taken_count, password, user_email) VALUES ('JewelsAndCats', 0, 'Norton19', 'superfake1@gmail.com');

--base quizzes for initial tests
INSERT INTO quiz(quiz_name, num_of_questions, type, difficulty) VALUES ('Variety Science', 5, 'Multiple Choice', 'Easy');
INSERT INTO quiz(quiz_name, num_of_questions, type, difficulty) VALUES ('Movies & Entertainment', 5, 'Multiple Choice', 'Easy');
INSERT INTO quiz(quiz_name, num_of_questions, type,difficulty) VALUES ('Intro To Addition', 5, 'Math', 'Easy');
INSERT INTO quiz(quiz_name, num_of_questions, type,difficulty) VALUES ('History: True or False?', 5, 'True or False', 'Easy');

--base questions for quizzes for initial tests
--quiz1q1
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('What is the name of the element with the chemical symbol ‘He’?', 'Hydrogen', 'Helium', 'Holmium', 'Hafnium', 2);
--quiz1q2
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('What type of scientist studies living plants?', 'Botanist', 'Geologist', 'Paleontologist', 'Entomologist', 1);
--quiz1q3
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('Which of the following is NOT scientifically considered a fruit?', 'Pear', 'Tomato', 'Broccoli', 'Pumpkin', 3);
--quiz1q4
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('A lobster’s teeth are located in which part of its body?', 'Legs', 'Claws', 'Mouth', 'Stomach', 4);
--quiz1q5
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('Which one of the following is the largest ocean in the world?', 'Pacific Ocean', 'Atlantic Ocean', 'Arctic Ocean', 'Indian Ocean', 1);


--quiz2q1
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('What was the original name of Mickey Mouse', 'Mortimer Mouse', 'The Rat', 'Marvin Mouse', 'Marshall Mouse', 1);
--quiz2q2
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('Which superhero, with the alter ego Wade Wilson and the powers of accelerated healing, was played by Ryan Reynolds in a 2016 film of the same name?','Black Panther', 'Deadpool', 'Ant-Man', 'Hawk', 2);
--quiz2q3
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('What is the next line for American Pie? Bye, bye Miss American Pie ---', 'I am hitting the road', 'Drove my Ford to the fjord', 'Drove my Chevy to the levee', 'I don’t want to see you again', 3);
--quiz2q4
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('What did Aladdin steal in the marketplace at the beginning of “Aladdin”?', 'Gold', 'Rice', 'Apples', 'Bread', 4);
--quiz2q5
INSERT INTO question(question, answer_choice_one, answer_choice_two, answer_choice_three, answer_choice_four, correct_answer_choice)
VALUES('Which College Is Elle Applying For In Legally Blonde?', 'Harvard', 'Yale', 'Duke', 'Princeton', 1);


--quiz3q1
INSERT INTO question(question, answer_choice_one, correct_answer_choice)
VALUES('What is (5 + 5)', '10', 1);
--quiz3q2
INSERT INTO question(question, answer_choice_one, correct_answer_choice)
VALUES('What is (10 + 7)', '17', 1);
--quiz3q3
INSERT INTO question(question, answer_choice_one, correct_answer_choice)
VALUES('What is (42 + 12)', '54', 1);
--quiz3q4
INSERT INTO question(question, answer_choice_one, correct_answer_choice)
VALUES('What is (123 + 234)', '357', 1);
--quiz3q5
INSERT INTO question(question, answer_choice_one, correct_answer_choice)
VALUES('What is (1,234 + 1,200)', '2,434', 1);


--quiz4q1
INSERT INTO question(question, answer_choice_one, answer_choice_two, correct_answer_choice)
VALUES('The second president of the USA was Thomas Jefferson.', 'TRUE', 'FALSE', 2);
--quiz4q2
INSERT INTO question(question, answer_choice_one, answer_choice_two, correct_answer_choice)
VALUES('Vatican City is the smallest country in the world.', 'TRUE', 'FALSE', 1);
--quiz4q3
INSERT INTO question(question, answer_choice_one, answer_choice_two, correct_answer_choice)
VALUES('The Chernobyl disaster occurred in 1976.', 'TRUE', 'FALSE', 2);
--quiz4q4
INSERT INTO question(question, answer_choice_one, answer_choice_two, correct_answer_choice)
VALUES('Columbus was the first European to sail to the Americas.', 'TRUE', 'FALSE', 2);
--quiz4q5
INSERT INTO question(question, answer_choice_one, answer_choice_two, correct_answer_choice)
VALUES('The Hundred Years War lasted less than 100 years.', 'TRUE', 'FALSE', 2);


INSERT INTO result(user_id, quiz_id, score, quiz_timestamp) VALUES ((SELECT user_id FROM users WHERE name = 'Mr. Bubble'), (SELECT quiz_id FROM quiz WHERE quiz_name = 'Movies & Entertainment'), 80, '2022-08-10 14:30:22');
INSERT INTO result(user_id, quiz_id, score, quiz_timestamp) VALUES ((SELECT user_id FROM users WHERE name = 'Mr. Bubble'), (SELECT quiz_id FROM quiz WHERE quiz_name = 'Intro To Addition'), 100, '2022-10-08 17:22:05');
INSERT INTO result(user_id, quiz_id, score,quiz_timestamp) VALUES ((SELECT user_id FROM users WHERE name = 'JewelsAndCats'), (SELECT quiz_id FROM quiz WHERE quiz_name = 'Variety Science'), 100, '2022-08-10 15:10:43');
INSERT INTO result(user_id, quiz_id, score, quiz_timestamp) VALUES ((SELECT user_id FROM users WHERE name = 'JewelsAndCats'), (SELECT quiz_id FROM quiz WHERE quiz_name = 'History: True or False?'), 60, '2022-08-10 15:15:55');


INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Variety Science'),(SELECT question_id FROM question where question = 'What is the name of the element with the chemical symbol ‘He’?'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Variety Science'),(SELECT question_id FROM question where question = 'What type of scientist studies living plants?'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Variety Science'),(SELECT question_id FROM question where question = 'Which of the following is NOT scientifically considered a fruit?'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Variety Science'),(SELECT question_id FROM question where question = 'A lobster’s teeth are located in which part of its body?'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Variety Science'),(SELECT question_id FROM question where question = 'Which one of the following is the largest ocean in the world?'));


INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Movies & Entertainment'),(SELECT question_id FROM question where question = 'What was the original name of Mickey Mouse'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Movies & Entertainment'),(SELECT question_id FROM question where question = 'Which superhero, with the alter ego Wade Wilson and the powers of accelerated healing, was played by Ryan Reynolds in a 2016 film of the same name?'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Movies & Entertainment'),(SELECT question_id FROM question where question = 'What is the next line for American Pie? Bye, bye Miss American Pie ---'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Movies & Entertainment'),(SELECT question_id FROM question where question = 'What did Aladdin steal in the marketplace at the beginning of “Aladdin”?'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Movies & Entertainment'),(SELECT question_id FROM question where question = 'Which College Is Elle Applying For In Legally Blonde?'));


INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Intro To Addition'),(SELECT question_id FROM question where question = 'What is (5 + 5)'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Intro To Addition'),(SELECT question_id FROM question where question = 'What is (10 + 7)'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Intro To Addition'),(SELECT question_id FROM question where question = 'What is (42 + 12)'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Intro To Addition'),(SELECT question_id FROM question where question = 'What is (123 + 234)'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'Intro To Addition'),(SELECT question_id FROM question where question = 'What is (1,234 + 1,200)'));


INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'History: True or False?'),(SELECT question_id FROM question where question = 'The second president of the USA was Thomas Jefferson.'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'History: True or False?'),(SELECT question_id FROM question where question = 'Vatican City is the smallest country in the world.'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'History: True or False?'),(SELECT question_id FROM question where question = 'The Chernobyl disaster occurred in 1976.'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'History: True or False?'),(SELECT question_id FROM question where question = 'Columbus was the first European to sail to the Americas.'));
INSERT INTO quiz_question(quiz_id, question_id) VALUES ((SELECT quiz_id FROM quiz WHERE quiz_name = 'History: True or False?'),(SELECT question_id FROM question where question = 'The Hundred Years War lasted less than 100 years.'));

COMMIT;
--ROLLBACK;


