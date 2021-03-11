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
## Tools used
```
Java - spring-boot - Elastic Beanstalk(aws)
```
