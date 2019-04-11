FROM java:8
ADD target/search-assembler-service.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -Djava.awt.bloomreach=true -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
