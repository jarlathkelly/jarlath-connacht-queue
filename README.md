# jarlath-connacht-queue

Jarlath's Rest/Queue based assignment for Aaron &  Andreas.

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

##  Rest Endpoints provided
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



7. Some management services have also been provided with the Spring Boot actuator module. /health and /beans are available on http://localhost:8080/.

##  Usage & Example Responses

#### Notes
The id parameter supplied to the REST calls must be a valid number between 1 and 9223372036854775807.
The createdTs supplied to the REST calls must be a valid 14 digit number that represents Date in
to the following format: ddMMyyyyHHmmss
- dd represents day (valid range 1-31
- MM represents month (valid range 1-12
- yyyy represents year (valid range 2010-2199
- HH represents hour (valid range 00-24
- mm represents year (valid range 00-59
- ss represents year (valid range 00-59

The 'position' member of returned Json string is for convenience. This will only ever be populated
when making a call to return the position for a specific ID on the queue.

A Rudimentary HATEOAS design has been incorporated into the returned Json.

#### Enqueue Work Order: POST
```
http://localhost:8080/workorders?id=1&createdTs=30032016093023

{
  "createdTS": "30032016093023",
  "position": null,
  "workOrderId": 1,
  "_links": {
    "self": {
      "href": "http://localhost:8080/workorders?id=1&createdTs=30032016093023"
    }
  }
}
```


#### Dequeue Highest Ranked WorkOrder: DELETE
```
http://localhost:8080/workorders
{
  "createdTS": "30032016093023",
  "position": null,
  "workOrderId": 15,
  "_links": {
    "self": [
      {
        "href": "http://localhost:8080/workorders?id=15&createdTs=30032016093023"
      },
      {
        "href": "http://localhost:8080/workorders"
      }
    ]
  }
}
```


#### Dequeue Highest Ranked WorkOrder: DELETE
```
http://localhost:8080/workorders/ids?id=40
{
  "createdTS": "30032016093023",
  "position": null,
  "workOrderId": 40,
  "_links": {
    "self": [
      {
        "href": "http://localhost:8080/workorders?id=40&createdTs=30032016093023"
      },
      {
        "href": "http://localhost:8080/workorders/ids?id=40"
      }
    ]
  }
}
```


#### List of ID's: GET
```
http://localhost:8080/workorders/ids
{
  "workOrderIdList": [
  15, 45, 3, 1, 2, 4
  ],
  "_links": {
    "self": {
      "href": "http://localhost:8080/workorders/ids"
    }
  }
}
```
#### Get position on Queue of a Specific Work Order: GET
```
http://localhost:8080/workorders/ids/positions?id=35
{
  "createdTS": "30032016093023",
  "position": 7,
  "workOrderId": 35,
  "_links": {
    "self": {
      "href": "http://localhost:8080/workorders/ids/positions?id=35"
    }
  }
}
```

#### Get Average Wait time on the Queue: GET
```
http://localhost:8080/workorders/waittimes?createdTs=30032016165012
{
  "waitTime": 1059589,
  "_links": {
    "self": {
      "href": "http://localhost:8080/workorders/waittimes?createdTs=28032016165012"
    }
  }
}
```


## ErrorHandling

#### InvalidIdParameterException
```
http://localhost:8080/workorders/waittimes?createdTs=300320161612
{
  "status": 400,
  "error": "Bad Request",
  "message": "ID Parameter Value is Invalid",
  "timeStamp": "Wed Mar 30 12:17:23 IST 2016",
  "trace": "com.jarlath.assignment.exception.InvalidIdParameterException\n\tat com.jarlath.assignment.service.ValidationServiceImpl.isIdValid(ValidationServiceImpl.java:33)"
}
```

#### InvalidTimestampParameterException
```
http://localhost:8080/workorders?id=0&createdTs=1603
{
  "status": 400,
  "error": "Bad Request",
  "message": "CreatedTs Parameter Value is Invalid",
  "timeStamp": "Wed Mar 30 11:13:28 IST 2016",
  "trace": "com.jarlath.assignment.exception.InvalidTimestampParameterException: CreatedTs Parameter Value is Invalid: 300320161612\n\tat com.jarlath.assignment.service.ValidationServiceImpl.isCreatedTsValid(ValidationServiceImpl"java:57)\n"
}
```

#### WorkOrderIdNotOnQueueException
```
http://localhost:8080/workorders/ids/positions?id=33
{
  "status": 403,
  "error": "Bad Request",
  "message": "WorkOrder ID not Found on Queue",
  "timeStamp": "Wed Mar 30 12:14:06 IST 2016",
  "trace": "com.jarlath.assignment.exception.WorkOrderIdNotOnQueueException\n\tat com.jarlath.assignment.service.WorkOrderQueueServiceImpl.retrieveIndexOfWorkOrderId(WorkOrderQueueServiceImpl.java:170"
}
```

#### WorkOrderExistsInQueueException
```
http://localhost:8080/workorders?id=1&createdTs=16032016093023
{
 "status": 403,
  "error": "Bad Request",
  "message": "WorkOrder ID already on Queue",
  "timeStamp": "Wed Mar 30 12:15:39 IST 2016",
  "trace": "com.jarlath.assignment.exception.WorkOrderExistsInQueueException: WorkOrder ID already on Queue: 1\n\tat com.jarlath.assignment.service.WorkOrderQueueServiceImpl.enqueueWorkOrder"
}
```

#### IdOutOfRangeException
```
http://localhost:8080/workorders?id=9223372036854775808&createdTs=28032016131357
{
  "status": 400,
  "error": "Bad Request",
  "message": "ID Parameter Value is not within Range",
  "timeStamp": "Wed Mar 30 22:32:53 IST 2016",
  "trace": "com.jarlath.assignment.exception.IdOutOfRangeException: WorkOrder ID supplied: 9223372036854775808 is not within the valid numeric range of 1-9223372036854775807."
}
```

#### NegativeDurationWaitTimeException
```
http://localhost:8080/workorders/waittimes?createdTs=20032010120000
{
  "status": 400,
  "error": "Bad Request",
  "message": "Waittime Calculation reulted in Negative Duration",
  "timeStamp": "Wed Mar 30 22:34:51 IST 2016",
  "trace": "com.jarlath.assignment.exception.NegativeDurationWaitTimeException: Waittime Calculation reulted in Negative Duration. Please ensure the createdTs parameter supplied is of the current time and not a stale date\n\tat com.jarlath.assignment.service.WorkOrderQueueServiceImpl"
```

#### HttpRequestMethodNotSupportedException
```
PUT http://localhost:8080/workorders/waittimes?createdTs=300320161612
{
  "status": 405,
  "error": "Method Not Allowed",
  "message": "Request method 'POST' not supported",
  "timeStamp": "Wed Mar 30 11:14:59 IST 2016",
  "trace": "org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported\n"
}
```

## Tests
Unit tests have been provided with the codebase and will run during the install as detailed above. Test cover all controller,service, dao & dto classes.

To run these tests on their own run the following command:
From project root directory run:
```sh
$ mvn test
```

## Documentation
JavaDoc Documentation will be generated in the /target/site/apidocs/com/jarlath/assignment folder once the project has been installed.

## History

Created on March 29th 2016

## Credits

Developed by Jarlath Kelly

## License

Na