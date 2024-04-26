#Use a base image
#Set working directory in container
#Copy target jar to working directory
#Expose port, default is 8080
#Run command to execute jar file

#FROM openjdk:17
#WORKDIR /app
#COPY target/dentalsurgeryapp-0.0.1-SNAPSHOT.jar /app
#EXPOSE 8080
#CMD ["java", "-jar", "./dentalsurgeryapp-0.0.1-SNAPSHOT.jar"]

FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -Dmaven.compiler.release=17

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/dentalsurgeryapp-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "./dentalsurgeryapp-0.0.1-SNAPSHOT.jar"]
