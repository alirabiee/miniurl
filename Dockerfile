FROM openjdk:8-jre

ENV JAVA_OPTS -server -XX:+UseG1GC -XX:MaxGCPauseMillis=500 -XX:G1RSetUpdatingPauseTimePercent=5

COPY target/miniurl-0.1.0-SNAPSHOT.jar /opt/miniurl.jar
COPY docker-entrypoint.sh /docker-entrypoint.sh

RUN ["mkdir", "/opt/db"]

RUN ["chmod", "+x", "/docker-entrypoint.sh"]

EXPOSE 8080

CMD ["-DoperationMode=production"]

ENTRYPOINT ["/docker-entrypoint.sh"]

