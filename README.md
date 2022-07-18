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