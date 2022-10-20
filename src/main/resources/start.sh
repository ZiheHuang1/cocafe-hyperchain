#!/bin/bash
JAR="cocafe-hyperchain-prod-1.0.0-SNAPSHOT.jar"
nohup java -jar -Dspring.profiles.active=test $JAR_HOME >/dev/null 2>&1