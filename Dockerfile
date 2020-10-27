FROM maven:3.3-jdk-8

ENV LANG=en_US.UTF-8

WORKDIR /app
COPY ./ ./
RUN mvn clean install
