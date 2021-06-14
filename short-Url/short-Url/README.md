# Short URL Application

### Objective
To implement a URL shortening service using Java and Spring.

### Brief

ShortLink is a URL shortening service where you enter a URL such as https://codesubmit.io/library/react and it returns a short URL such as http://short.est/GeAi9K.

Service will maintain the key value pair of short and long url. It will redirect to long url whenever short url hitted. The short url key will be generated based on random logic with 7 char length.

It is a spring boot based web service. System exposes an endpoint to generate short url for given long url. System will have the logic for generating the key which will be used to as short url. Same will be stored in redis cache with long url.

Redis cache configuration are added into application.properties file.

### API Endpoints

You can access following API endpoints at http://localhost:8081

###application.properties file

  server.port = 8081
  spring.cache.type=redis
  spring.redis.host=http://localhost  
  spring.redis.port=6379
  #unlimted seconds 0
  spring.redis.cache.expiration=0


###Project Build 

To build this project, run

//shell script
git clone https://.git
//cd into the project 
cd my-repository
Build: mvn package
Deploy: mvn spring-boot:run

###Now Use Postman to make GET or POST Request

###Calls Collection
All calls are found within the file "Andries collection.postman_collection.json" in the "src/main/resources/static/postman/" folder. 


###Response exception codes:

Please note that API works only with valid HTTP or HTTPS Urls. In case of malformed Url, it returns `400 Bad Request` error with response body containing a JSON object in the following format

'''json format
{
  "field":"fullUrl",
  "value":"<Malformed Url provided in the request>",
  "message":"<Exception message>"
}
'''
Further respose error codes are listed below:

| HTTP Status | Description           |
|-------------|-----------------------|
| 200         | successful operation  |
| 404         | not found             |
| 500         | internal server error |

_________________________________END___________________________________________
              ____________Made by Andries___________









