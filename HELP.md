## Backend Recruitment Test from Wagawin

###DB configuration 

MySql server should be configured on jdbc:mysql://127.0.0.1:3306/test
 
to create the schema use the command:
```
CREATE SCHEMA `test`;
```

default credentials:
```
username=root
password=Testbackend273!
```
### Build and deploy

To build the application run

```
mvn clean install
```

entry point to start the application:
```
test/src/main/java/com/wagawin/test/TestApplication.java
```

Tomcat configured to listen on port 9092
