FROM openjdk:21
EXPOSE 8080
ADD target/Forum-0.0.1-SNAPSHOT.jar forum.jar

ENTRYPOINT ["java", "-jar", "forum.jar"]