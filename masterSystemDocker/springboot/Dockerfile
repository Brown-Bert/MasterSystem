FROM java:8
MAINTAINER yzx
COPY *.jar /app.jar
EXPOSE 8080
ENV JAVA_HOME="/usr/share/jdk-17.0.7"
ENV PATH="${PATH}:${JAVA_HOME}/bin:${JAVA_HOME}/sbin"
ENTRYPOINT java ${PARAM} -jar app.jar