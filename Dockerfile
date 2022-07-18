FROM openjdk:17

COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY --from=build target/*.jar SchoolRegistrationSystem.jar
ADD target/SchoolRegistrationSystem-0.0.1-SNAPSHOT.jar SchoolRegistrationSystem.jar
ENTRYPOINT ["java","-jar","spring","SchoolRegistrationSystem.jar"]