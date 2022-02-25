FROM openjdk:17

COPY ./affinity/target/affinity-3.21ea84-SNAPSHOT-jar-with-dependencies.jar /app/
COPY ./affinity/run.sh /app/
WORKDIR /app
ENTRYPOINT ["./run.sh"]
