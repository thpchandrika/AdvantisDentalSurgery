FROM openjdk:17
WORKDIR /app
COPY target/dentalsurgeryapp-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "./dentalsurgeryapp-0.0.1-SNAPSHOT.jar"]

#FROM maven:3.8.4-openjdk-17 AS build
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests
#FROM openjdk:17
#WORKDIR /app
#COPY --from=build /app/target/dentalsurgeryapp-0.0.1-SNAPSHOT.jar /app
#EXPOSE 8080
#CMD ["java", "-jar", "./dentalsurgeryapp-0.0.1-SNAPSHOT.jar"]

