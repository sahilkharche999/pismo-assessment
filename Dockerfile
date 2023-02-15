FROM openjdk:11

ARG JAR_FILE=target/pismo-assessment.jar

COPY ${JAR_FILE} pismo-assessment.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/pismo-assessment.jar"]