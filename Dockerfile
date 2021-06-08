FROM openjdk:13
EXPOSE 8010
ADD ./target/ms-security-1.0.0.jar    micro-security.jar
ENTRYPOINT ["java","-jar","/micro-security.jar"]