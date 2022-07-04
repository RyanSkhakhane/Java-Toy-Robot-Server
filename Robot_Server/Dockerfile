FROM ubuntu:latest
FROM openjdk:11

COPY src/libs/Robot_Server-4.0.0-jar-with-dependencies.jar usr/local/libs/Robot_Server-4.0.0-jar-with-dependencies.jar


EXPOSE 5000
ENTRYPOINT ["java","-jar","/usr/local/libs/Robot_Server-4.0.0-jar-with-dependencies.jar"]

# sudo docker run sha256:5c547fabd6e08a998eb2f65351312c02e09062d5ac317468847532b9a8353b7b
