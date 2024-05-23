FROM amazoncorretto:17.0.11-al2
ADD ./build/libs/cuenta-0.0.1-SNAPSHOT.jar /home
ADD ./config/application.yml /home
ADD ./config/entrypoint.sh /home
ENTRYPOINT ["/home/entrypoint.sh"]
EXPOSE 8080
