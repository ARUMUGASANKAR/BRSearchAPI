FROM openjdk:8-jre-alpine

ADD br-search-service.jar /opt/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/br-search-service.jar"]
