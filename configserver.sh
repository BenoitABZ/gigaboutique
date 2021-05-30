#!/bin/sh
echo "execution du serveur de configuration"
cd docker/dev/config
java -jar giga-configserver-0.0.1-SNAPSHOT.jar &
sleep 2m
