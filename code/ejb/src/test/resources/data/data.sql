INSERT INTO ROLE_(ID, "NAME") VALUES(1, 'ADMIN')
INSERT INTO ROLE_(ID, "NAME") VALUES(2, 'MEMBER')

INSERT INTO COUNTRY(ID, "NAME", CODE) VALUES(1, 'Belgique', 'BE')
INSERT INTO COUNTRY(ID, "NAME", CODE) VALUES(2, 'France', 'FR')
INSERT INTO COUNTRY(ID, "NAME", CODE) VALUES(3, 'Royaume-Uni', 'GB')

INSERT INTO CITY(ID, "NAME", ZIP, COUNTRY_ID) VALUES(1, 'Little Whinging', 'KT220LQ', 3)
INSERT INTO CITY(ID, "NAME", ZIP, COUNTRY_ID) VALUES(2, 'Ottery St Catchpole', 'DT12RY', 3)
INSERT INTO CITY(ID, "NAME", ZIP, COUNTRY_ID) VALUES(3, 'London', 'N1P2AL', 3)

INSERT INTO ADDRESS(ID, STREET, NB, CITY_ID) VALUES(1, 'Privet Drive', '4', 3)
INSERT INTO ADDRESS(ID, STREET, NB, CITY_ID) VALUES(2, 'The Burrow', null, 3)
INSERT INTO ADDRESS(ID, STREET, NB, CITY_ID) VALUES(3,  'Pudding Lane', '125', 3)

INSERT INTO PASSWORD(ID, HASHED) VALUES(1, 'harry')
INSERT INTO PASSWORD(ID, HASHED) VALUES(2, 'ron')
INSERT INTO PASSWORD(ID, HASHED) VALUES(3, 'hermione')

INSERT INTO USER_(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD_ID, ADDRESS_ID) VALUES(1, 'Harry', 'Potter', 'harry.potter@hogwarts.edu', 1, 1)
INSERT INTO USER_(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD_ID, ADDRESS_ID) VALUES(2, 'Ron', 'Weasley', 'ron.weasley@hogwarts.edu', 2, 2)
INSERT INTO USER_(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD_ID, ADDRESS_ID, TOKEN) VALUES(3, 'Hermione', 'Granger', 'hermione.granger@hogwarts.edu', 3, 3, 'example token')

INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE) VALUES(1, 'Veau marengo', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE')
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE) VALUES(2, 'Marbré au thé matcha', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE')
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE) VALUES(3, 'Lasagne aux deux saumons', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE')
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE) VALUES(4, 'Veggie lasagne', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE')
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE) VALUES(5, 'Ma super lasagne', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE')
INSERT INTO ARTIFACT(ID, TITLE, DESCRIPTION, DTYPE) VALUES(6, 'Saumon en papillotte', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'RE')

INSERT INTO INGREDIENT(ID, NAME) VALUES(1, 'sel')
INSERT INTO INGREDIENT(ID, NAME) VALUES(2, 'tomate')
INSERT INTO INGREDIENT(ID, NAME) VALUES(3, 'beurre')