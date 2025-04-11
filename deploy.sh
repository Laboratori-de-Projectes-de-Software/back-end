#!/bin/bash



cd ../front-end-bc-spn/
#npm install # (only necessary first time)
npm run build
rm -r ../back-end-bc-spn/src/main/resources/static/*
cp -r dist/* ../back-end-bc-spn/src/main/resources/static/

cd ../back-end-bc-spn/
mvn clean install
java -jar target/debateia-0.0.1-SNAPSHOT.jar
