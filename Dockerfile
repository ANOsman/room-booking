FROM amazoncorretto:17-alpine3.17

EXPOSE 8080

LABEL maintainer Ali Osman "anosman6891@gmail.com"

WORKDIR /usr/local/bin/

COPY ./target/room-booking-0.0.1-SNAPSHOT.jar room-booking.jar

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "room-booking.jar"]