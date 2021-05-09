#!/bin/bash
cd ../backend/src/main/resources/static/
rm -r new/
mkdir new
cd ../../../../../frontend/
npm install
ng build --prod --base-href="/new/"
cp -r dist/frontend ../backend/src/main/resources/static/new/
cd ../backend
sudo docker run --rm -v "$PWD":/usr/src/app -w /usr/src/app node:latest /bin/bash -c "npm install && npm run build"
sudo docker run --rm -v "$PWD":/data -w /data maven mvn package
cp ./target/6ixShop-0.1.0-SNAPSHOT.jar ../Docker
cd ../Docker
sudo docker build -t sixshop/sixshop .
rm -rf *.jar