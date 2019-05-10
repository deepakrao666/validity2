FROM java:8-jdk-alpine
MAINTAINER deepakrao666@gmail.com
COPY ./target/validity2-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch validity2-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","validity2-0.0.1-SNAPSHOT.jar"]