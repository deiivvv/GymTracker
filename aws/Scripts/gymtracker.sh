#!bin/bash
nohup java -jar GymTracker-0.0.1-SNAPSHOT.jar > first.log 2>&1 &
nohup java -jar Api-Ejercicios-0.0.1-SNAPSHOT.jar > second.log 2>&1 &