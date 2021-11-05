## Backend Recruitment Test from Wagawin

##Database 

### Mysql installation
On macOS, you can install MySQL easily using Homebrew.

```
brew install mysql
```
You can now start the MySQL server by running:
```
brew services start mysql
```
Run:
```
mysql_secure_installation
```
default credentials:
```
username=root
password=Testbackend273!
```
MySql server should be configured on jdbc:mysql://127.0.0.1:3306/test

to create the schema 'test' use the command:
```
CREATE SCHEMA `test`;
```
to use another credentials change application.properties

full instruction to set up MySql is here: 

https://flaviocopes.com/mysql-how-to-install/

### DB population  
Db can be populated with PopulateTest.

Fake data provided by com.github.javafaker

## Build and deploy

To build the application run

```
mvn clean install
```

entry point to start the application:
```
/src/main/java/com/wagawin/test/TestApplication.java
```

Tomcat configured to listen on port 9092

## Redis
redis is configured in application.properties
 

### Redis installation macOS

Alternative way to install Redis locally with brew(macOS only):

With an up-to-date version of Homebrew, install Redis by using the command:
```
brew install redis
```

After installing redis, type from terminal:

```
redis-server
```

##Endpoints

http://localhost:9092/child/info/1 returns the Parent and the most favorite meal of a child.

http://localhost:9092/house/1 returns the House for a given person id.

http://localhost:9092/persons/children returns an array in which the element n is the amount of people having n children.

To speed up this request, there is ParentSummaryScheduler which runs every 15 minutes, 
calculates those numbers and puts them into ParentSummary.

!!! IMPORTANT 
I would prefer to follow rest style (/house/person/1), because I followed the documentation.

I did not use request parameters because they were not defined in the documentation as well.


###Further improvements
Redis and Mysql can be installed with the help of Docker
https://dev.to/ziishaned/running-mongodb-redis-mysql-and-nats-as-a-docker-container-2b59

It would be great to start the app via docker-compose further.