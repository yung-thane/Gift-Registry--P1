FROM openjdk
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/musica-1.0-SNAPSHOT-all.jar musica.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar musica.jar
