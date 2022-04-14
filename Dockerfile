FROM openjdk:11
ADD build/libs/backend-0.0.1-SNAPSHOT.jar recommend.jar
EXPOSE 8095
ENTRYPOINT ["java", "-jar", "recommend.jar"]