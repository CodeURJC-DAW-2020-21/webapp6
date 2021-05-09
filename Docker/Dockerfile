#FROM openjdk:11
#COPY ./6ixShop-0.1.0-SNAPSHOT.jar ./6ixShop-0.1.0-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "6ixShop-0.1.0-SNAPSHOT.jar"]

FROM openjdk:11
#WORKDIR /app
#COPY ./6ixShop-0.1.0-SNAPSHOT.jar /app
COPY ./6ixShop-0.1.0-SNAPSHOT.jar ./6ixShop-0.1.0-SNAPSHOT.jar
EXPOSE 8443
CMD ["java", "-jar", "6ixShop-0.1.0-SNAPSHOT.jar"]