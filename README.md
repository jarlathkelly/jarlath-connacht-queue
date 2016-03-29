# jarlath-connacht-queue

Jarlath's Rest based assignment for Aaron &  Andreas.

## Installation
The install scripts (install.sh/install.bat) provided will perform the following tasks:
- Maven Clean and Install of the codebase (this step will also run a full suite of unit tests)
- Generate JavaDocs for the codebase
- Start up Spring Boot

#### Prerequisites:
1. Maven 3.2.1 +
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
Rest service resources will then be available at http://localhost:8080/

Use a rest Client to Test the endpoints. I use Postman. See https://www.getpostman.com/

##  Endpoints & Usage
1. An endpoint for adding a Work Order to the queue (enqueue). This endpoint accepts two parameters, the ID to enqueue and the time at which the ID was added to the queue.
 - POST http://localhost:8080/workorders?id=1234&createdTs=28032016193012
2. An endpoint for getting the top ranked ID from the queue and removing it (dequeue). This endpoint returns the highest ranked ID and the time it was entered into the queue.
 - DELETE http://localhost:8080/workorders
3. An endpoint for getting the list of IDs in the queue. This endpoint returns a list of IDs sorted from highest ranked to lowest.
 - GET http://localhost:8080/workorders/ids
4. An endpoint for removing a specific ID from the queue. This endpoint accepts a single parameter, the ID to remove.
 - DELETE http://localhost:8080/workorders/ids?id=1234
5. An endpoint to get the position of a specific ID in the queue. This endpoint accepts one parameter, the ID to get the position of. It returns the position of the ID in the queue indexed from 0.
 - GET http://localhost:8080/workorders/ids/positions?id=1234
6. An endpoint to get the average wait time. This endpoint accepts a single parameter, the current time, and returns the average number of seconds that each ID has been waiting in the queue.
 - GET http://localhost:8080/workorders/waittimes?createdTs=28032016193012



7. Some management services have also been provided with the Spring Boot actuator module. /health,/audits,/beans,/errors are also available on http://localhost:8080/.


## Tests
Unit tests have been provided with the codebase and will run during the install as detailed above. to run these tests on their own run the follwoing command:

From project root directory run:
```sh
$ mvn test
```

## History

Created on March 29th 2016

## Credits

Developed by Jarlath Kelly

## License

Na