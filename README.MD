# MachineReference

## Описание

Этот проект был создан для небольшой практики,
которая заключалась в создании маленького REST приложения.
 
- Maven
- MySql
- Spring Boot 2

## Сборка и запуск

### Сборка

```shell script
mvn clean package
```

### Запуск

```shell script
java -Dfile.encoding=utf-8 MachineReference-1.4-SNAPSHOT.jar
```
 
По-умолчанию, сервер запустится по адресу `localhost` на порту `8080`.  
Для изменения этих значений, нужно указать новые значения
в файле `/src/main/resources/application.properties`:

```properties
server.address=localhost
server.port=8090
```
