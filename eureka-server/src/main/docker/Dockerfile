FROM java:8
VOLUME /tmp
ADD eureka-service-1.0-SNAPSHOT.jar app.jar
ENV TZ=Asia/Shanghai
RUN bash -c 'touch /app.jar' \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]