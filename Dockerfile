FROM java:11
ADD target/br-search-service.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -Djava.awt.bloomreach=true -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
