FROM maven:3.6.3-jdk-11
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
ADD ./pom.xml $PROJECT_DIR
RUN mvn dependency:resolve
ADD ./src/ $PROJECT_DIR/src
RUN mvn clean install

FROM adoptopenjdk/openjdk11:jdk-11.0.7_10-alpine
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000
COPY --from=0 $PROJECT_DIR/target/higloadarch* $PROJECT_DIR/
CMD [ "java","-jar", "/opt/project/higloadarch.jar" ]
