FROM anapsix/alpine-java:8_jdk
MAINTAINER Paweł Motyl <pawmot@gmail.com>
ENV REFRESHED_AT 2017-01-03

ADD echo-1.0-SNAPSHOT-all.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
