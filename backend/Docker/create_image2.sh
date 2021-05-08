#!/bin/bash

cd ../../frontend

docker run --rm --name front-container -v ${PWD}:/data -w /data node:latest /bin/bash -c "npm install; npm run build"

mkdir ../backend/src/main/resources/static/new

cp -R dist/data/* ../backend/src/main/resources/static/new

cd ../

docker run --rm -v ${PWD}/backend:/backend -w /backend maven:alpine mvn package

docker build -t sixshop/sixshop .

docker login --username=sixshop

docker push sixshop/sixshop

exit 0