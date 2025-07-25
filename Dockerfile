# Stage 1: Build Stage
FROM bellsoft/liberica-runtime-container:jdk-24-stream-musl AS builder

WORKDIR /home/app
COPY . /home/app/spring-boot-multiversion-docs-mkdocs-mike
WORKDIR /home/app/spring-boot-multiversion-docs-mkdocs-mike
RUN  chmod +x mvnw && ./mvnw -Dmaven.test.skip=true clean package

# Stage 2: Layer Tool Stage
FROM bellsoft/liberica-runtime-container:jdk-24-cds-slim-musl AS optimizer

WORKDIR /home/app
COPY --from=builder /home/app/spring-boot-multiversion-docs-mkdocs-mike/target/*.jar spring-boot-multiversion-docs-mkdocs-mike.jar
RUN java -Djarmode=tools -jar spring-boot-multiversion-docs-mkdocs-mike.jar extract --layers --launcher

# Stage 3: Final Stage
FROM bellsoft/liberica-runtime-container:jre-24-stream-musl

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080
COPY --from=optimizer /home/app/spring-boot-multiversion-docs-mkdocs-mike/dependencies/ ./
COPY --from=optimizer /home/app/spring-boot-multiversion-docs-mkdocs-mike/spring-boot-loader/ ./
COPY --from=optimizer /home/app/spring-boot-multiversion-docs-mkdocs-mike/snapshot-dependencies/ ./
COPY --from=optimizer /home/app/spring-boot-multiversion-docs-mkdocs-mike/application/ ./