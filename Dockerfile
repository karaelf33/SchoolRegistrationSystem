FROM openjdk:17
ADD target/SchoolRegistrationSystem-0.0.1-SNAPSHOT.jar SchoolRegistrationSystem.jar
ENTRYPOINT ["java","-jar","spring","SchoolRegistrationSystem.jar"]