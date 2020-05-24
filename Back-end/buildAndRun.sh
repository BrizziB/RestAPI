#!/bin/sh
mvn clean package && docker build -t brizzi/RestAPI_prova .
docker rm -f RestAPI_prova || true && docker run -d -p 8080:8080 -p 4848:4848 --name RestAPI_prova brizzi/RestAPI_prova 
