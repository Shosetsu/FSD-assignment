FROM frolvlad/alpine-oraclejre8
VOLUME [ "/usr/tmp" ]
ADD target/*-exec.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]