FROM openjdk:11
ADD target/recommend.jar recommend.jar
EXPOSE 8095
ENTRYPOINT ["java", "-jar", "recommend.jar"]