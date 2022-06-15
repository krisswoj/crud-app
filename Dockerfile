FROM openjdk:11-jdk AS build

ADD . .

ENV GRADLE_USER_HOME .gradle-home
RUN ./gradlew clean build -x test -x integrationTests --no-daemon -i

CMD java $JAVA_OPTS -jar ./build/libs/finestmedia-*.jar
