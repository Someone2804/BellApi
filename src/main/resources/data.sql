INSERT INTO CITIZENSHIP(id, VERSION, CITIZENSHIP_CODE, CITIZENSHIP_NAME) VALUES (
1, 1, '643', 'Российская Федерация');

INSERT INTO ORGANIZATION(id, VERSION, SHORT_NAME, FULL_NAME, INN, KPP, ADDRESS, IS_ACTIVE) VALUES (
1, 1, 'Bell', 'Bell Integrator', '777777777777', '999999999', 'br. r', 1),
(2, 1, 'Bell', 'Bell Integrator2', '777777777777', '999999999', 'br. r', 1);

INSERT INTO OFFICE(id, VERSION, OFFICE_NAME, ADDRESS, IS_ACTIVE, ORGANIZATION_ID) VALUES
(1, 1, 'Bell office1', 'qwerty', 1, 1),
(2, 1, 'Bell office2', '916', 1, 1),
(3, 1, 'Bell office3', 'qwerty', 1, 2);

INSERT INTO POSITION(id, VERSION, POSITION_NAME) VALUES
(1, 1, 'Java Senior'),
(2, 1, 'Java Middle'),
(3, 1, 'Java Junior');


INSERT INTO USR(id, VERSION, FIRST_NAME, IS_IDENTIFIED, OFFICE_ID, CITIZENSHIP_ID) VALUES (
1, 1, 'Bell worker', 1, 1, 1),
(2, 1, 'Bell worker2', 1, 1, 1),
(3, 1, 'Bell worker3', 1, 1, null),
(4, 1, 'Bell worker4', 1, 1, null);


INSERT INTO DOCUMENT_NAME(id, VERSION, DOC_CODE, DOC_NAME) VALUES (1, 1, '21', 'Паспорт Гражданина РФ');

INSERT INTO DOCUMENT(VERSION, DOC_NUMBER, DOC_DATE, DOC_NAME_ID, USR_ID, ID) VALUES (
1, '445365', '1999-12-22', 1, 1, 1),
(1, '445365', '1999-12-22', 1, 2, 2);

INSERT INTO USR_POSITION VALUES
(1, 2),
(1, 1),
(2, 1),
(3, 3);

