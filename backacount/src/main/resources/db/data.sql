INSERT INTO FINANCIALCOMPANIES (IDFINANCIALCOMPANY, NAME) VALUES ('1', 'citibank');

INSERT INTO DOCUMENTTYPES (IDDOCTYPE, TYPENAME) VALUES ('CC', 'Cedula ciudadania');
INSERT INTO DOCUMENTTYPES (IDDOCTYPE, TYPENAME) VALUES ('NIT', 'Número de Identificación Tributaria');

INSERT INTO TransactionTypes (IDTransactionType, TransactionName) VALUES (1, 'credit');
INSERT INTO TransactionTypes (IDTransactionType, TransactionName) VALUES (2, 'debit');

--user czar8a
INSERT INTO FINANCIALUSERS (IDDOCUMENT, IDDOCTYPE, IDFINANCIALCOMPANY, LOGINUSER, PASS,financialusername,financialuserlastname) VALUES ('98332954', 'CC', 1, 'czar8a', '$2a$10$wPZ/lBrzqjpdqJJEHeC2mOiy2bYlMNPQLroCnqhRNDj02QWjCHOtm','Cesar','Ochoa');--12345$
--acounts
INSERT INTO ACCOUNTS (IDAcount, UsersIDfinancialCompany,LoginUser, Balance) VALUES ('74926A4045324543',1 , 'czar8a', 30000.32);
INSERT INTO ACCOUNTS (IDAcount, UsersIDfinancialCompany,LoginUser, Balance) VALUES ('645345B045324543',1 , 'czar8a', 5000.45);

--user owen
INSERT INTO FINANCIALUSERS (IDDOCUMENT, IDDOCTYPE, IDFINANCIALCOMPANY, LOGINUSER, PASS,financialusername,financialuserlastname) VALUES ('498368468', 'CC', 1, 'owen', '$2a$10$IteN9IYbNtuaxr0/8Vw4BOi4kmw2rmbKsa7N9DYi8apwvXksQJR4C','Owen','Mcakey');--#$erwaf343
--acounts
INSERT INTO ACCOUNTS (IDAcount, UsersIDfinancialCompany,LoginUser, Balance) VALUES ('12354352J543',1 , 'owen', 50000.12);
INSERT INTO ACCOUNTS (IDAcount, UsersIDfinancialCompany,LoginUser, Balance) VALUES ('53436988P24543',1 , 'owen', 10000.98);

--user elena
INSERT INTO FINANCIALUSERS (IDDOCUMENT, IDDOCTYPE, IDFINANCIALCOMPANY, LOGINUSER, PASS,financialusername,financialuserlastname) VALUES ('683054325', 'CC', 1, 'elena', '$2a$10$wPZ/lBrzqjpdqJJEHeC2mOiy2bYlMNPQLroCnqhRNDj02QWjCHOtm','Elena','Raamirez');--12345$
--acounts
INSERT INTO ACCOUNTS (IDAcount, UsersIDfinancialCompany,LoginUser, Balance) VALUES ('877643345O987R',1 , 'elena', 20000.45);
INSERT INTO ACCOUNTS (IDAcount, UsersIDfinancialCompany,LoginUser, Balance) VALUES ('3455987JDR7890',1 , 'elena', 6000.36);

--user diana
INSERT INTO FINANCIALUSERS (IDDOCUMENT, IDDOCTYPE, IDFINANCIALCOMPANY, LOGINUSER, PASS,financialusername,financialuserlastname) VALUES ('74926340', 'CC', 1, 'diana', '$2a$10$IteN9IYbNtuaxr0/8Vw4BOi4kmw2rmbKsa7N9DYi8apwvXksQJR4C','Diana','Leon');--#$erwaf343
--acounts
INSERT INTO ACCOUNTS (IDAcount, UsersIDfinancialCompany,LoginUser, Balance) VALUES ('SDFSADF335432908',1 , 'diana', 17000.45);
INSERT INTO ACCOUNTS (IDAcount, UsersIDfinancialCompany,LoginUser, Balance) VALUES ('JHLJKHASZD8734523',1 , 'diana', 94600.36);
