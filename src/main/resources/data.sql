INSERT INTO Organization (Version, NAME,FULL_NAME,INN,KPP,ADDRESS,PHONE,is_active) VALUES(0,'Рога и копыта','ООО Рога и копыта','11111111','222222','г Уфа','34-34-34',TRUE),(0,'ACME','ACME inc','22222222','111111','Москва','34-34-34',false);

insert into DOC (version,CODE,NAME) VALUES(0,0 ,'Паспорт гражданина РФ'),(0,1 ,'Удостоверение личности офицера'),(0,2 ,'Военный билет солдата'),(0,3 ,'Паспорт Минморфлота'),(0,4 ,'Свидетельство о рождении'),(0,5 ,'Вид на жительство'),(0,6 ,'Загранпаспорт гражданина РФ');

INSERT INTO COUNTRY (version,CODE,NAME) VALUES(0,643,'Российская Федерация');

INSERT INTO Office (version,ORG_ID,NAME,ADDRESS,PHONE,IS_ACTIVE) VALUES (0,1,'Офис1','г уфа','11-11-11',TRUE),(0,1,'Офис2','г уфа','22-22-22',TRUE),(0,2,'Офис 1','г уфа','11-11-11',TRUE),(0,2,'Офис 1','г уфа','11-11-11',TRUE),(0,2,'Офис3','г уфа','33-33-33',TRUE);

INSERT INTO USER (version,OFFICE_ID,FIRST_NAME,SECOND_NAME,MIDDLE_NAME,POSITION,PHONE,DOC_CODE,DOC_NUMBER,DOC_DATE,CITIZENSHIP_CODE,IS_IDENTIFIED) VALUES  (0,1,'Иванов','Иван','Иванович','Директор','111111',1,'1111 123456','1955-02-01',1,'true'),(0,2,'Иванов1','Иван1','Иванович1','Директор','111111',1,'1111 123456','1955-02-01',1,'true');
