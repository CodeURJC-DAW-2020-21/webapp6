#!/bin/bash
cd ../backend/src/main/resources/static/
rm -r new/
mkdir new
cd ../../../../../frontend/
npm install
ng build --prod --base-href="/new/"
cp -r dist/data ../backend/src/main/resources/static/new/
cd ../backend
sudo docker run --rm -v "$PWD":/data -w /data maven mvn package
cp ./target/6ixShop-0.1.0-SNAPSHOT.jar ../Docker
cd ../Docker
sudo docker build -t sixshop/sixshop .
rm -rf *.jar