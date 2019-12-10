FROM openjdk:11-jre-slim
COPY ./target/geolocation-1.0-SNAPSHOT-shaded.jar geolocation.jar
EXPOSE 8080
CMD java ${JAVA_OPTS} -jar geolocation.jar