#!/bin/bash

cd ..
sudo apt-get update
sudo apt-get install docker.io docker-compose -y

sudo docker run --rm -v "$PWD":/data -w /data maven mvn package
cp /target/*.jar ../Docker

cd 'Docker'

docker build -t sixshop/sixshop .

docker-compose up --build -d

docker login --username=sixshop

docker push sixshop/sixshop

exit 0
