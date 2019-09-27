#!/bin/bash

docker network create turingmysql
docker container run -p  3306:3306 --name mysqldb --network turingmysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=tshirtshop -d mysql:5.7
docker-compose build
docker-compose up

