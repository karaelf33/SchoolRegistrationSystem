<h1 style="color: white">Welcome to School Registration System</h1>

A guiding school registration system for students to enroll in the courses

<h2>Technologies</h2>
<ul>
  <li>JAVA 17</li>
  <li>Spring Boot 2.7.1</li>
  <li>Hibernate</li>
  <li>JPA</li>
 <li>JUnit</li>
 <li>Maven</li>
<li>REST Api</li>
<li>H2 database</li>
</ul>  

## Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Data JDBC](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#data.sql.jdbc)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#data.sql)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)


1-Create Student=

POST http://localhost:8080/api/v1/student

body
{
"name":"Taylor",
"surname": "Rw",
"studentCode":"12345"
}

2.)Create Course 

POST http://localhost:8080/api/v1/course

body
{
"name":"Java 17 Course",
"courseCode":"1234"
}

3.) Register Course

PUT http://localhost:8080/api/v1/course/register

body

{
"courseCode":"c2",
"studentCode":"s2"
}

4.) Delete Student,

 POST  http://localhost:8080/api/v1/student/s1

5.) Search

http://localhost:8080/api/v1/search/c2?codeType=courses

http://localhost:8080/api/v1/search/c2?codeType=students

6.) Get All Student

http://localhost:8080/api/v1/student

7.) Get All Course

http://localhost:8080/api/v1/course

8.) Get one Course

GET http://localhost:8080/api/v1/course/c1

GET http://localhost:8080/api/v1/course/c2

9.) Delete Course
DELETE http://localhost:8080/api/v1/course/c1


10.)

GET http://localhost:8080/api/v1/student/s1