This project is a springboot project develped with Intellij, the proposit of this project is
receives a string that is an user name, if this user name exist on database (in this case I used
an in-memory database that I usde H2 framework to implement) or have some restricted word on database(also with H2)the system
will give a list with 14 username suggestions. You can access this service through a rest service
(http://localhost:8080/userCheck/{userName}) and I developed some integrantion tests using junit framework. 
The technologies used in this project were:
 - SpringBoot
 - Gradle
 - H2
 - Spring JPA
 - Spring Injection
 - Junit

You can check the build.gradle file to see the project dependencies.
To running the project you need to build the project with this commmand in project directory:
	gradlew build
And running the jar generated with this command in jar directory and in a machine that have a jvm installed:
	java -jar check-username-0.0.1-SNAPSHOT.jar

 