CREATE OR REPLACE VIEW USERGROUP_VW AS SELECT u.email AS username, p.hashed AS password, r.name AS groupname FROM user_ u JOIN password p ON u.password_id = p.id JOIN user__role_ ur ON u.id = ur.user_id JOIN role_ r ON ur.roles_id = r.id UNION SELECT s.validatedid AS username, p.hashed AS password, r.name AS groupname FROM socialuser s LEFT JOIN user_ u ON s.user_id = u.id LEFT JOIN user__role_ ur ON u.id = ur.user_id LEFT JOIN password p ON s.password_id = p.id LEFT JOIN role_ r ON ur.roles_id = r.id;

ALTER TABLE usergroup_vw OWNER TO sociall

INSERT INTO MENUITEM(ID, INDEX, FILEPATH, TITLE) VALUES(1, 0, '/pages/member/profile', 'profile.menu.profile');
INSERT INTO MENUITEM(ID, INDEX, FILEPATH, TITLE) VALUES(2, 1, '/pages/member/create', 'profile.menu.create');
INSERT INTO MENUITEM(ID, INDEX, FILEPATH, TITLE) VALUES(3, 2, '/pages/member/posts', 'profile.menu.posts');
INSERT INTO MENUITEM(ID, INDEX, FILEPATH, TITLE) VALUES(4, 3, '/pages/member/messages', 'profile.menu.messages');
INSERT INTO MENUITEM(ID, INDEX, FILEPATH, TITLE) VALUES(5, 4, '/pages/member/friends', 'profile.menu.friends');
INSERT INTO MENUITEM(ID, INDEX, FILEPATH, TITLE) VALUES(6, 5, '/pages/member/agenda', 'profile.menu.agenda');
INSERT INTO MENUITEM(ID, INDEX, FILEPATH, TITLE) VALUES(7, 6, '/pages/member/settings', 'profile.menu.settings');
INSERT INTO MENUITEM(ID, INDEX, FILEPATH, TITLE) VALUES(8, 7, '/pages/admin/manage', 'profile.menu.manage');

INSERT INTO ROLE_(ID, NAME) VALUES(1, 'ADMIN');
INSERT INTO ROLE_(ID, NAME) VALUES(2, 'MEMBER');

INSERT INTO MENUITEM_ROLE_ (MENUITEM_ID, ROLESRESTRICTION_ID) VALUES(8, 1);

INSERT INTO PASSWORD(ID, HASHED) VALUES(1, '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO PASSWORD(ID, HASHED) VALUES(2, '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

INSERT INTO IMAGE(ID, FILENAME) VALUES(1, 'pp-1-foodish.png');
INSERT INTO IMAGE(ID, FILENAME) VALUES(2, 'pp-2-frodo.png');
INSERT INTO IMAGE(ID, FILENAME) VALUES(3, 'pp-3-sam.jpg');
INSERT INTO IMAGE(ID, FILENAME) VALUES(4, 'pp-4-gandalf.jpg');
INSERT INTO IMAGE(ID, FILENAME) VALUES(5, 'virgi.jpg');


INSERT INTO USER_(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD_ID, PROFILEPIC_ID) VALUES(1, 'Foodish', '', 'admin', 1, 1);
INSERT INTO USER_(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD_ID, PROFILEPIC_ID) VALUES(2, 'Frodo', 'Baggins', 'frodo', 2, 2);
INSERT INTO USER_(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD_ID, PROFILEPIC_ID) VALUES(3, 'Sam', 'Gamegie', 'sam', 2, 3);
INSERT INTO USER_(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD_ID, PROFILEPIC_ID) VALUES(4, 'Gandalf', 'The White', 'gandalf', 2, 4);
INSERT INTO USER_(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD_ID, PROFILEPIC_ID) VALUES(5, 'Virginie', 'Desamoury', 'vdesamoury@gmail.com', 2, 5);

INSERT INTO SOCIALUSER(ID, SOCIALPROVIDER, VALIDATEDID, AUTHKEY, USER_ID, PASSWORD_ID) VALUES(1, 'FACEBOOK', '744505028927921', 'test', 5, 2);
INSERT INTO SOCIALUSER(ID, SOCIALPROVIDER, VALIDATEDID, AUTHKEY, USER_ID, PASSWORD_ID) VALUES(2, 'TWITTER', '167080317', 'test', 5, 2);

INSERT INTO USER__ROLE_ (USER_ID, ROLES_ID) VALUES(1, 1);
INSERT INTO USER__ROLE_ (USER_ID, ROLES_ID) VALUES(1, 2);
INSERT INTO USER__ROLE_ (USER_ID, ROLES_ID) VALUES(2, 2);
INSERT INTO USER__ROLE_ (USER_ID, ROLES_ID) VALUES(3, 2);
INSERT INTO USER__ROLE_ (USER_ID, ROLES_ID) VALUES(4, 2);
INSERT INTO USER__ROLE_ (USER_ID, ROLES_ID) VALUES(5, 2);

INSERT INTO SETTING(ID, KEY_, TEXTVALUE, DESCRIPTION) VALUES(1, 'upload_path', 'D:\java\uploads\images', 'Chemin du répertoire où sont stockées les images uploadées.');
INSERT INTO SETTING(ID, KEY_, NUMERICVALUE, DESCRIPTION) VALUES(2, 'max_rating', 5, 'Nombre maximum de stars.');
INSERT INTO SETTING(ID, KEY_, TEXTVALUE, DESCRIPTION) VALUES(3, 'date_time_format', 'dd/MM/yy hh:mm', 'Format utilisé par défaut pour affichage des dates.');

INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(1, 'Veau marengo', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE', '2014-06-01 09:00:00');
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(2, 'Marbré au thé matcha', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE', '2014-02-06 09:00:00');
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(3, 'Lasagne aux deux saumons', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE', '2013-03-01 09:00:00');
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(4, 'Veggie lasagne', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE', '2013-03-15 09:00:00');
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(5, 'Ma super lasagne', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE', '2013-03-15 09:00:00');
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(6, 'Saumon en papillotte', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE', '2013-04-01 09:00:00');
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(7, 'Le saumon îvre', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'PL', '2014-04-01 09:00:00');
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(8, 'Café des sports', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'PL', '2013-05-01 09:00:00');
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(9, 'example product 1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'PL', '2012-05-01 09:00:00');
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE, CREATEDON) VALUES(10, 'example product 2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'PL', '2012-06-01 09:00:00');

INSERT INTO RECIPE(ID, NBPERSONS, PRICE) VALUES(1, 4, 2);
INSERT INTO RECIPE(ID, NBPERSONS, PRICE) VALUES(2, 4, 2);
INSERT INTO RECIPE(ID) VALUES(3);
INSERT INTO RECIPE(ID) VALUES(4);
INSERT INTO RECIPE(ID) VALUES(5);
INSERT INTO RECIPE(ID) VALUES(6);

INSERT INTO PLACE(ID) VALUES(7);
INSERT INTO PLACE(ID) VALUES(8);

INSERT INTO PRODUCT(ID) VALUES(9);
INSERT INTO PRODUCT(ID) VALUES(10);

INSERT INTO IMAGE(ID, FILENAME, ARTIFACT_ID) VALUES(20, '1-veaumarengo.jpg', 1);
INSERT INTO IMAGE(ID, FILENAME, ARTIFACT_ID) VALUES(21, '2-marbreauthematcha-1.jpg', 2);
INSERT INTO IMAGE(ID, FILENAME, ARTIFACT_ID) VALUES(22, '2-marbreauthematcha-2.jpg', 2);
INSERT INTO IMAGE(ID, FILENAME, ARTIFACT_ID) VALUES(23, '2-marbreauthematcha-3.jpg', 2);
INSERT INTO IMAGE(ID, FILENAME, ARTIFACT_ID) VALUES(24, '7-lesaumonivre.jpg', 7)

INSERT INTO REVIEW(ID, ARTIFACT_ID, USER_ID, TITLE, TEXT, RATING, REVIEWDATE) VALUES (1, 2, 3, 'Super', 'J''ai adoré.', 80, '2013-08-15 09:00:00');
INSERT INTO REVIEW(ID, ARTIFACT_ID, USER_ID, TITLE, TEXT, RATING, REVIEWDATE) VALUES (2, 2, 2, 'Pas mal', 'C''était bien mais pas top.', 50, '2014-05-30 09:00:00');
INSERT INTO REVIEW(ID, ARTIFACT_ID, USER_ID, TITLE, TEXT, RATING, REVIEWDATE) VALUES (3, 1, 2, 'Super, à refaire', '.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 70, '2014-05-10 12:00:00');
INSERT INTO REVIEW(ID, ARTIFACT_ID, USER_ID, TITLE, TEXT, RATING, REVIEWDATE) VALUES (4, 7, 2, 'Bof bof', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 40, '2014-05-02 09:00:00');

INSERT INTO INGREDIENT(ID, NAME_, RECIPE_ID) VALUES(1, '100 grammes de beurre', 2);
INSERT INTO INGREDIENT(ID, NAME_, RECIPE_ID) VALUES(2, '100 grammes de farine', 2);
INSERT INTO INGREDIENT(ID, NAME_, RECIPE_ID) VALUES(3, '50 grammes de sucre', 2);

-- INSERT INTO RECIPE_INGREDIENT(RECIPE_ID, INGREDIENTS_ID) VALUES(2, 1);
-- INSERT INTO RECIPE_INGREDIENT(RECIPE_ID, INGREDIENTS_ID) VALUES(2, 2);
-- INSERT INTO RECIPE_INGREDIENT(RECIPE_ID, INGREDIENTS_ID) VALUES(2, 3);

INSERT INTO CONVERSATION(ID, THISUSER_ID, THATUSER_ID) VALUES(1, 1, 2);
INSERT INTO CONVERSATION(ID, THISUSER_ID, THATUSER_ID) VALUES(2, 2, 3);
INSERT INTO CONVERSATION(ID, THISUSER_ID, THATUSER_ID) VALUES(3, 2, 4);

INSERT INTO MESSAGE (ID, SENDER_ID, SENTON, IS_READ, TEXT, CONVERSATION_ID) VALUES (1, 1, '2013-01-01 09:00:00', false, 'Bienvenue chez Foodish', 1);

INSERT INTO MESSAGE (ID, SENDER_ID, SENTON, IS_READ, TEXT, CONVERSATION_ID) VALUES (2, 2, '2014-02-01 13:42:00 ', true, 'Salut Sam, ça va?', 2);
INSERT INTO MESSAGE (ID, SENDER_ID, SENTON, IS_READ, TEXT, CONVERSATION_ID) VALUES (3, 3, '2014-02-01 13:45:30', true, 'Bonjour monsieur Frodo :)', 2);
INSERT INTO MESSAGE (ID, SENDER_ID, SENTON, IS_READ, TEXT, CONVERSATION_ID) VALUES (4, 2, '2014-02-01 14:00:00', true, 'Je pars en voyage bientôt, tu viens avec moi?', 2);

INSERT INTO MESSAGE (ID, SENDER_ID, SENTON, IS_READ, TEXT, CONVERSATION_ID) VALUES (5, 4, '2014-04-22 22:17:03', true, 'Jeune monsieur Frodo, comment allez-vous?', 3);
INSERT INTO MESSAGE (ID, SENDER_ID, SENTON, IS_READ, TEXT, CONVERSATION_ID) VALUES (6, 2, '2014-04-23 07:30:12', true, 'Vous me faites peur, Gandalf :/', 3);
INSERT INTO MESSAGE (ID, SENDER_ID, SENTON, IS_READ, TEXT, CONVERSATION_ID) VALUES (7, 4, '2014-04-23 10:17:22', false, 'Je reviens vers vous en ces temps difficiles. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 3);

INSERT INTO AGENDAEVENT(ID, USER_ID, STARTDATE, ENDDATE, ARTIFACT_ID) VALUES(1, 2, '2014-05-01 12:00:00', '2014-05-01 12:00:00', 2);
INSERT INTO AGENDAEVENT(ID, USER_ID, STARTDATE, ENDDATE, ARTIFACT_ID, NOTE) VALUES(2, 2, '2014-05-10 19:00:00', '2014-05-10 19:00:00', 1, 'Avec Gandalf');
INSERT INTO AGENDAEVENT(ID, USER_ID, STARTDATE, ENDDATE, ARTIFACT_ID, NOTE) VALUES(3, 2, '2014-05-15 20:00:00', '2014-05-15 20:00:00', 3, 'Trop salé, attention la prochaine fois!');
INSERT INTO AGENDAEVENT(ID, USER_ID, STARTDATE, ENDDATE, ARTIFACT_ID) VALUES(4, 2, '2014-05-22 19:00:00', '2014-05-22 23:00:00', 7);

INSERT INTO TAG(ID, NAME_) VALUES(1, 'sucré');
INSERT INTO TAG(ID, NAME_) VALUES(2, 'thé');
INSERT INTO TAG(ID, NAME_) VALUES(3, 'salé');
INSERT INTO TAG(ID, NAME_) VALUES(4, 'français');
INSERT INTO TAG(ID, NAME_) VALUES(5, 'italien');

INSERT INTO ARTIFACT_TAG(ARTIFACT_ID, TAGS_ID) VALUES(1, 3);
INSERT INTO ARTIFACT_TAG(ARTIFACT_ID, TAGS_ID) VALUES(1, 5);
INSERT INTO ARTIFACT_TAG(ARTIFACT_ID, TAGS_ID) VALUES(2, 1);
INSERT INTO ARTIFACT_TAG(ARTIFACT_ID, TAGS_ID) VALUES(2, 2);