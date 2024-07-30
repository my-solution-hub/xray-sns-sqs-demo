ARG PLATFORM
FROM --platform=linux/${PLATFORM} amazoncorretto:17

COPY ./agent/aws-opentelemetry-agent.jar /opt/aws-opentelemetry-agent.jar
ENV JAVA_TOOL_OPTIONS=-javaagent:/opt/aws-opentelemetry-agent.jar
COPY ./target/world.jar /app/demo.jar
# WORKDIR /app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]