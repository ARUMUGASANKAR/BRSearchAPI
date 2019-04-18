FROM openjdk:11-jre-slim

ADD br-search-service.jar /opt/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/br-search-service.jar"]
