FROM java:8
MAINTAINER michaelsavvas@ymail.com
RUN apt-get update && apt-get clean
ADD target/vapejuicetemple-1.0-SNAPSHOT.jar /data/vapejuicetemple-1.0-SNAPSHOT-DOCKER.jar
CMD ["java", "-Xmx256M", "-jar", "/data/vapejuicetemple-1.0-SNAPSHOT-DOCKER.jar", "server"]
EXPOSE 8080