# Use a base image with Java 17 (choose the appropriate version)
FROM openjdk:21-oracle

# Copy the application JAR file into the container
COPY build/libs/management-0.0.1-SNAPSHOT-plain.jar /

# Expose the port that your application will listen on (if needed)
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "management-0.0.1-SNAPSHOT-plain.jar"]