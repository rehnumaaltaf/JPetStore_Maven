FROM dockersrv02.ad.infosys.com/infosys/java:8-jdk

ADD ./ctrm-config-0.0.1-SNAPSHOT.jar /app/

CMD [ "java", "-Xmx200m", "-jar", "/app/ctrm-config-0.0.1-SNAPSHOT.jar","-Dspring.profiles.active=dev"]

EXPOSE 8888