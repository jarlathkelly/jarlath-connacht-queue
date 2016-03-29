# jarlath-connacht-queue

This is a queue based assignment for Jarlath.

## Installation
The install scripts provided will perform the following tasks:
- Maven Clean and Install of the codebase
- Generate JavaDocs for the codebase
- Start up Spring Boot

#### Prerequisites:
1. Maven 3.2.1
2. Java 8 (jdk1.8.0_25 used to develop)

From project root directory:

For Windows Systems run:
```sh
install.bat
```

For Linux/MacOs systems run:
```sh
$ sh ./install.sh
```
Rest service now available at http://localhost:8080/

## Usage
 - POST http://localhost:8080/workorders?id=1234&createdTs=28032016193012
 - DELETE http://localhost:8080/workorders
 - DELETE http://localhost:8080/workorders/ids?id=1234
 - GET http://localhost:8080/workorders/ids
 - GET http://localhost:8080/workorders/ids/positions?id=1234
 - GET http://localhost:8080/workorders/waittimes?createdTs=28032016193012




## History

Created on March 29th 2016

## Credits

Developed by Jarlath Kelly

## License

Na