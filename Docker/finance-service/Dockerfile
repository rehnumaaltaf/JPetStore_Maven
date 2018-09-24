FROM java:8-jre

ADD ./finance-service-0.0.1-SNAPSHOT.jar /app/

CMD [ "java", "-Xmx200m", "-jar", "/app/finance-service-0.0.1-SNAPSHOT.jar","-Dspring.profiles.active=dev"]

EXPOSE 8093
