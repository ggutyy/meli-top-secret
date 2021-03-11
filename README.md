# Quasar

Api Rest to locate the spaceship postion based on distance of particular satellites. 
Also this application generate a comunication with all sattellites requested and get a clean message sent between all the space.

## Installation
### Locally
```
mvn spring-boot:run
```
### Hosted
This command generate the .jar file to delivery for host and execute.
```
mvn package
```
## Usage

### Locally
```
swagger doc in http://localhost:5000/swagger-ui.html <-- In this url you will see all services exposed when was executed locally
```
### Actual Hosted in AWS Elastic Beanstalk
```
http://meliquasarrest-env.eba-p46x6h4c.us-east-1.elasticbeanstalk.com

```
#### Services exposed
```
POST -> /topsecret
Sample request body:

{
   "satellites":[
      {
         "name":"kenobi",
         "distance":200.0,
         "message":[
            "este",
            "",
            "",
            "mensaje",
            ""
         ]
      },
      {
         "name":"skywalker",
         "distance":115.5,
         "message":[
            "",
            "secreto"
         ]
      },
      {
         "name":"sato",
         "distance":142.7,
         "message":[
            "este",
            "",
            "un",
            "",
            ""
         ]
      }
   ]
}
```
```
POST -> /topsecret_split/{satellite_name} <---ADD PATH VARIABLE REPLACING "satellite_name"

Sample request body:
{
  "distance": 0,
  "message": [
    "string"
  ]
}
```
```
GET -> /topsecret_split/ 
```

## Tools used
```
Java - spring-boot - Elastic Beanstalk(aws)
```
