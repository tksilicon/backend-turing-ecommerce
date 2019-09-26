# backend-turing-ecommerce

System architecture

This backend was developed with Spring Boot version 2.2.0.M2. 

Spring boot is a module of spring framework which is used to create stand-alone, production-grade Spring based Applications with minimum programmer’s efforts. It is developed on top of core spring framework. The main concept behind spring boot is to avoid lot of boilerplate code and configuration to improve development, unit test etc. 

Security was implemented with Spring Security and JSON Web Token (JWT) which is a means of representing claims to be transferred between two parties.The claims in a JWT are encoded as a JSON object that is digitally signed using JSON Web Signature (JWS) and/or encrypted using JSON Web Encryption (JWE).

Payment API was done with Stripe specified.

Documentations developed with Swagger.

Database is MySQL

Live Deployment link: https://backend-turing-ecommerce.herokuapp.com/api/ (Best viewed on Mozilla Firefox)

List of endpoints can be found here:

https://backend-turing-ecommerce.herokuapp.com/swagger-ui.html


# To run in Docker

Create a network for Spring Boot Backend and MySQL

docker network create turingmysql

Create a MySQL database

docker container run -p  3306:3306 --name mysqldb --network turingmysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=tshirtshop -d mysql:5.7

Copy the image id and insert in this command, example:
docker container exec -it e816f70fe435a4c38c6bbfaaa765a7ac488c7290b73f944124ffd87224a26f3e bash and run

docker container exec -it <image id here> bash

Log in as root

mysql -uroot -proot

Copy and paste MySQL file from server-turing-ecommerce/database run

type 'exit' and enter. Do that twice. 

Stop the MySQL image

docker stop mysqldb

Run the application/containers

docker-compose up  (it will take some moments)

open

http://localhost:8080/swagger-ui.html#/
http://localhost:8080/api/


To close them in another window type

docker-compose down

Or Ctril-C. If 'docker-compose up' encounters 3306 port already bound, run 

docker stop mysqldb


For MySQL on localhost and running with Maven

Start database on localhost
Run SQL file in server-turing-ecommerc/database folder on MySQL on localhost

mvn spring-boot:run


Hours spent on the project - Minimum of 12 hours for 14 days (168hours)


