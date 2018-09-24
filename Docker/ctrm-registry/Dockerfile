FROM java:8-jre

ADD ./ctrm-registry-0.0.1-SNAPSHOT.jar /app/

CMD [ "java", "-Xmx200m", "-jar", "/app/ctrm-registry-0.0.1-SNAPSHOT.jar","-Dspring.profiles.active=dev"]

EXPOSE 8761
