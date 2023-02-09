FROM maven:3.8.7-eclipse-temurin-11

WORKDIR /controle-projetos

COPY controle-projetos .

RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar", "target/controle-projetos-0.0.1-SNAPSHOT.war"]