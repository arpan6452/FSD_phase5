FROM openjdk:8
COPY ./target/spring_boot_jwt-0.0.1-SNAPSHOT.jar spring_boot_jwt-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java" ,"-jar","spring_boot_jwt-0.0.1-SNAPSHOT.jar"]
#CMD ["java" ,"-jar","spring_boot_jwt-0.0.1-SNAPSHOT.jar"]
RUN echo "jenkins ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers

#-> Create a image for springboot webservice
# -->  Dockerfile
#  - -> docker image build -t <imagetagname> .

#-> run container by using custom image
# -> docker container run  --name <ecom-web-service> -p 8081:8081 -d <ecom-webservice>


#-> verify log 
#  -> docker container log <container-name>