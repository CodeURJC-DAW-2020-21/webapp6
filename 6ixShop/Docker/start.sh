#!/bin/bash

cd ..
sudo apt-get update
sudo apt-get install docker.io docker-compose -y

sudo mkdir /opt/mvn

sudo wget -O /opt/mvn/apache-maven-3.8.1-bin.tar.gz https://ftp.cixug.es/apache/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz
sudo tar -xvf /opt/mvn/apache-maven-3.8.1-bin.tar.gz -C /opt/mvn
export PATH=/opt/mvn/apache-maven-3.8.1/bin/:$PATH

mvn package

cd 'Docker'

docker build -t sixshop/sixshop .

docker login --username=sixshop

docker push sixshop/sixshop