# jarlath-connacht-queue

This is a queue based assignment for Jarlath.

## Installation
From root directory:
1. Run mvn clean package 
2. Run mvn spring-boot:run

Rest service now available at http://localhost:8080/

## Usage
POST http://localhost:8080/workorders?id=1234&createdTs=28032016193012
DELETE http://localhost:8080/workorders
DELETE http://localhost:8080/workorders/ids?id=1234
GET http://localhost:8080/workorders/ids
GET http://localhost:8080/workorders/ids/positions?id=1234
GET http://localhost:8080/workorders/waittimes?createdTs=28032016193012



## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

Created on March 29th 2016

## Credits

Developed by Jarlath Kelly

## License

Na