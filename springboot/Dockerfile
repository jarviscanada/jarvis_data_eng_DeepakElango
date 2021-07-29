#
# Build stage
#
FROM maven:3.6-jdk-8-slim AS build
COPY src /build/src
COPY pom.xml /build/
RUN mvn -f /build/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:8-alpine
COPY --from=build /build/target/trading-app*.jar /Users/apple/Desktop/GitHub/jarvis_data_eng_DeepakElango/springboot/target/trading-app-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Users/apple/Desktop/GitHub/jarvis_data_eng_DeepakElango/springboot/target/trading-app-1.0-SNAPSHOT.jar"]