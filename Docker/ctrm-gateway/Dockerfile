FROM java:8-jre

ADD ./ctrm-gateway-0.0.1-SNAPSHOT.jar /app/

CMD [ "java", "-Xmx200m", "-jar", "/app/ctrm-gateway-0.0.1-SNAPSHOT.jar","-Dspring.profiles.active=dev"]

EXPOSE 80
