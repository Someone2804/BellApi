## Homework project for Bell Integrator
### Info
```
DB    - jdbc:h2:mem:bellApi_db
Stack - jdbc/jpa/h2/boot
Port  - 8080
```
### Implementation examples

Plain JDBC - [link](https://github.com/Someone2804/BellApi/blob/main/src/main/java/com/bell/BellApi/dao/impl/UserDaoImpl.java#L73)

JPQL - [PositionDaoImpl](https://github.com/Someone2804/BellApi/blob/main/src/main/java/com/bell/BellApi/dao/impl/PositionDaoImpl.java#L30), [DocumentNameDaoImpl](https://github.com/Someone2804/BellApi/blob/main/src/main/java/com/bell/BellApi/dao/impl/DocumentNameDaoImpl.java), [CountryDaoImpl](https://github.com/Someone2804/BellApi/blob/main/src/main/java/com/bell/BellApi/dao/impl/CountryDaoImpl.java)

JPA - almost all project

Criteria API - [UserDaoImpl](https://github.com/Someone2804/BellApi/blob/b7311b3932e0c9cb27afb63b32dce8b062945c68/src/main/java/com/bell/BellApi/dao/impl/UserDaoImpl.java#L138), [OrganizationDaoImpl](https://github.com/Someone2804/BellApi/blob/b7311b3932e0c9cb27afb63b32dce8b062945c68/src/main/java/com/bell/BellApi/dao/impl/OrganizationDaoImpl.java#L82), [OfficeDaoImpl](https://github.com/Someone2804/BellApi/blob/b7311b3932e0c9cb27afb63b32dce8b062945c68/src/main/java/com/bell/BellApi/dao/impl/OfficeDaoImpl.java#L93)

