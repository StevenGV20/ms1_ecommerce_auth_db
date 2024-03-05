FROM openjdk:17-jdk-alpine
COPY target/ecommerce_auth_db_p1-0.0.1-SNAPSHOT.jar ms1_ecommerce_auth_app.jar
ENTRYPOINT [ "java" , "-jar" , "ms1_ecommerce_auth_app.jar"]