FROM java:8-jre

ADD ./authorization-config-service-0.0.1-SNAPSHOT.jar /app/

CMD [ "java", "-Xmx200m", "-jar", "/app/authorization-config-service-0.0.1-SNAPSHOT.jar","-Dspring.profiles.active=dev"]

EXPOSE 8092
