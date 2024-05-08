FROM openjdk:17
WORKDIR /app
COPY target/dentalsurgeryapp-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "./dentalsurgeryapp-0.0.1-SNAPSHOT.jar"]


