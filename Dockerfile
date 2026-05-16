#Use the official OpenJDK 21 image from Docker Hub
#FROM openjdk:21
FROM public.ecr.aws/amazoncorretto/amazoncorretto:21-alpine
#Set working directory inside the container
WORKDIR /app
#Copy the compiled Java app JAR file into the container
COPY build/libs/*.jar /app/employee-management.jar
#Expose the port on which the Spring Boot app wil run
EXPOSE 8080
#Command to run the app
CMD ["java", "-jar", "employee-management.jar"]
