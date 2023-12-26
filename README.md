# url-shortener-demo-backend
backend url shortener demo project made with spring boot 

This project is using a in-memory H2 database.

To start the app:

`./gradlew clean bootRun`

To generate a shortened url:

`curl --request POST 
--url http://localhost:8080/api/create 
--header 'Content-Type: application/json' 
--data '{ "originalUrl": "testerska-url.sk" }'`

To find the originalUrl && generatedUrl value from the service:

`curl --request GET 
--url http://localhost:8080/api/get/igbutkvnyj`

Where the /api/get/{generatedUrl} you've got from first step.

It was developed in a TDD, so there's some tests too ;)
