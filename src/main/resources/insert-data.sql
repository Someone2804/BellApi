INSERT INTO COUNTRY(id, VERSION, CITIZENSHIP_CODE, CITIZENSHIP_NAME) VALUES (
1, 1, 643, 'Российская Федерация');

INSERT INTO ORGANIZATION(id, VERSION, SHORT_NAME, FULL_NAME, INN, KPP, ADDRESS, IS_ACTIVE) VALUES (
1, 1, 'Bell', 'Bell Integrator', '777777777777', '999999999', 'br. r', 1);

INSERT INTO OFFICE(id, VERSION, OFFICE_NAME, ADDRESS, IS_ACTIVE, ORGANIZATION_ID) VALUES (
1, 1, 'Bell office', 'qwerty', 1, 1);

INSERT INTO WORKER_POSITION(id, VERSION, POSITION_NAME) VALUES (1, 1, 'Java Senior');

INSERT INTO USR(id, VERSION, FIRST_NAME, IS_IDENTIFIED, POSITION_ID, OFFICE_ID, CITIZENSHIP_ID) VALUES (
1, 1, 'Bell worker', 1, 1, 1, 1);

INSERT INTO DOCNAME (id, VERSION, DOC_CODE, DOC_NAME) VALUES (1, 1, 21, 'Паспорт Гражданина РФ');

INSERT INTO DOCUMENT(id, VERSION, DOC_NUMBER, DOC_DATE, DOC_NAME_ID, USR_ID) VALUES (
1, 1, '445365', '1999-12-22', 1, 1);

UPDATE USR u SET u.DOCUMENT_ID = 1 WHERE u.ID = 1;
