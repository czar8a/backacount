-- Generado por Oracle SQL Developer Data Modeler 18.2.0.179.0756
--   en:        2021-06-03 17:07:12 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE accounts (
    idacount                  VARCHAR2(20) NOT NULL,
    usersidfinancialcompany   INTEGER NOT NULL,
    loginuser                 VARCHAR2(30) NOT NULL,
    balance                   DOUBLE NOT NULL
);

ALTER TABLE accounts ADD CONSTRAINT account_pk PRIMARY KEY ( usersidfinancialcompany,
                                                             idacount );

CREATE TABLE accounttransfers (
    transferdate                VARCHAR2(23) NOT NULL,
    recipientaccountidcompany   INTEGER NOT NULL,
    recipientaccountidacount    VARCHAR2(20) NOT NULL,
    sourceaccountidcompany      INTEGER NOT NULL,
    sourceaccountidacount       VARCHAR2(20) NOT NULL,
    amount                      DOUBLE NOT NULL,
    transferdescription         VARCHAR2(500) NOT NULL,
    symbol                      VARCHAR2(3) NOT NULL,
    originalamount              DOUBLE NOT NULL
);

ALTER TABLE accounttransfers
    ADD CONSTRAINT accounttransfers_pk PRIMARY KEY ( transferdate,
                                                     recipientaccountidacount,
                                                     recipientaccountidcompany,
                                                     sourceaccountidcompany,
                                                     sourceaccountidacount );

CREATE TABLE documenttypes (
    iddoctype   VARCHAR2(10) NOT NULL,
    typename    VARCHAR2(50) NOT NULL
);

ALTER TABLE documenttypes ADD CONSTRAINT documenttype_pk PRIMARY KEY ( iddoctype );

CREATE TABLE financialcompanies (
    idfinancialcompany   INTEGER NOT NULL,
    name                 VARCHAR2(100) NOT NULL
);

ALTER TABLE financialcompanies ADD CONSTRAINT financialcompany_pk PRIMARY KEY ( idfinancialcompany );

CREATE TABLE financialusers (
    idfinancialcompany   INTEGER NOT NULL,
    loginuser            VARCHAR2(30) NOT NULL,
    iddoctype            VARCHAR2(10) NOT NULL,
    iddocument           VARCHAR2(20) NOT NULL,
    pass                 VARCHAR2(100) NOT NULL,
    financialusername       VARCHAR2(50) NOT NULL,
    financialuserlastname   VARCHAR2(50) NOT NULL
);

ALTER TABLE financialusers ADD CONSTRAINT user_pk PRIMARY KEY ( idfinancialcompany,
                                                                loginuser );

CREATE TABLE transactions (
    accountidfinancialcompany   INTEGER NOT NULL,
    idacount                    VARCHAR2(20) NOT NULL,
    idtipotransaccion           INTEGER NOT NULL,
    transactiondate             VARCHAR2(23) NOT NULL,
    amount                      DOUBLE NOT NULL
);

ALTER TABLE transactions
    ADD CONSTRAINT transaction_pk PRIMARY KEY ( idtipotransaccion,
                                                accountidfinancialcompany,
                                                transactiondate,
                                                idacount );

CREATE TABLE transactiontypes (
    idtransactiontype   INTEGER NOT NULL,
    transactionname     VARCHAR2(50) NOT NULL
);

ALTER TABLE transactiontypes ADD CONSTRAINT transactiontype_pk PRIMARY KEY ( idtransactiontype );

ALTER TABLE accounts
    ADD CONSTRAINT accountuser_fk FOREIGN KEY ( usersidfinancialcompany,
                                                loginuser )
        REFERENCES financialusers ( idfinancialcompany,
                                    loginuser );

ALTER TABLE accounttransfers
    ADD CONSTRAINT recipientaccount_fk FOREIGN KEY ( recipientaccountidcompany,
                                                     recipientaccountidacount )
        REFERENCES accounts ( usersidfinancialcompany,
                              idacount );

ALTER TABLE accounttransfers
    ADD CONSTRAINT sourceaccount_fk FOREIGN KEY ( sourceaccountidcompany,
                                                  sourceaccountidacount )
        REFERENCES accounts ( usersidfinancialcompany,
                              idacount );

ALTER TABLE transactions
    ADD CONSTRAINT transaccionesaccount_fk FOREIGN KEY ( accountidfinancialcompany,
                                                         idacount )
        REFERENCES accounts ( usersidfinancialcompany,
                              idacount );

ALTER TABLE transactions
    ADD CONSTRAINT transtipotransaccion_fk FOREIGN KEY ( idtipotransaccion )
        REFERENCES transactiontypes ( idtransactiontype );

ALTER TABLE financialusers
    ADD CONSTRAINT userfinancialcompany_fk FOREIGN KEY ( idfinancialcompany )
        REFERENCES financialcompanies ( idfinancialcompany );

ALTER TABLE financialusers
    ADD CONSTRAINT usertipodocumento_fk FOREIGN KEY ( iddoctype )
        REFERENCES documenttypes ( iddoctype );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             7
-- CREATE INDEX                             0
-- ALTER TABLE                             14
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
