#Use a base image
#Set working directory in container
#Copy target jar to working directory
#Expose port, default is 8080
#Run command to execute jar file

FROM openjdk:17
WORKDIR /app
COPY target/dentalsurgeryapp-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "./dentalsurgeryapp-0.0.1-SNAPSHOT.jar"]