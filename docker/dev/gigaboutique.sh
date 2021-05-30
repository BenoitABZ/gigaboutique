#!/bin/bash
echo "Lancement de gigaboutique.fr"
echo "Initialisation de la Base de données..."
docker-compose up -d
echo "Initialisation du serveur de configuration..."
cd config
docker build -t config .
docker run --network="host" -d -p9101:9101 config:latest
sleep 3m
echo "Initialisation de Eureka..."
cd ..
cd eureka
docker build -t eureka .
docker run --network="host" -d -p9102:9102 eureka:latest
sleep 3m
echo "Initialisation de user service..."
cd .. 
cd user 
docker build -t user .
docker run --network="host" -d -p8001:8001 user:latest
sleep 3m
echo "Initialisation de product service..."
cd .. 
cd product 
docker build -t product .
docker run --network="host" -d -p8002:8002 product:latest
sleep 3m
echo "Initialisation de seller service..."
cd .. 
cd seller 
docker build -t seller .
docker run --network="host" -d -p8003:8003 seller:latest
sleep 3m
echo "Initialisation de gateway service..."
cd .. 
cd gateway 
docker build -t gateway .
docker run --network="host" -d -p8080:8080 gateway:latest
sleep 3m
echo "Initialisation de client service..."
cd .. 
cd client 
docker build -t client .
docker run --network="host" -d -p8007:8007 client:latest
sleep 3m
echo "gigaboutique.fr !"
