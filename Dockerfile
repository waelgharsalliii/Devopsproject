FROM openjdk:8
ADD target/DevOps_Project-1.0.jar DevOps_Project.jar-1.0
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "DevOps_Project-1.0.jar"]