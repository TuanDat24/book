FROM openjdk:17
ADD target/sell_book-0.0.1-SNAPSHOT.jar /application.jar
ADD target/classes/templates /templates
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","application.jar"]