#!/bin/bash
mvn clean install;
mvn javadoc:javadoc;
mvn spring-boot:run;