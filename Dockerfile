FROM maven:3.8.2-jdk-8

WORKDIR /devops_project
COPY . .
RUN mvn clean install -Dmaven.test.skip

CMD mvn spring-boot:run
