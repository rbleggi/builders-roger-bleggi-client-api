FROM azul/zulu-openjdk-alpine:16
MAINTAINER Roger Bleggi <roger.bleggi@hotmail.com>

# Add the service itself
ADD ./target/client-api.jar /usr/share/api/app.jar

# Startup service
ENTRYPOINT ["java", "-jar", "/usr/share/api/app.jar", "-Djava.net.preferIPv4Stack=true"]