FROM openjdk:8-jre-alpine

ADD search-assembler-service.jar /opt/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/search-assembler-service.jar"]