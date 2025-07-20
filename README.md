<h1 style="text-align: center;">Spring Boot Multi Version Documentation</h1>

<p style="text-align: center;">
  <a href="https://github.com/deepaksorthiya/spring-boot-multiversion-docs-mkdocs-mike/workflows/maven-build.yml">
    <img src="https://github.com/deepaksorthiya/spring-boot-multiversion-docs-mkdocs-mike/actions/workflows/maven-build.yml/badge.svg" alt="Java Maven Build Test"/>
  </a>
  <a href="https://hub.docker.com/r/deepaksorthiya/spring-boot-multiversion-docs-mkdocs-mike">
    <img src="https://img.shields.io/docker/pulls/deepaksorthiya/spring-boot-multiversion-docs-mkdocs-mike" alt="Docker"/>
  </a>
  <a href="https://spring.io/projects/spring-boot">
    <img src="https://img.shields.io/badge/spring--boot-3.5.0-brightgreen?logo=springboot" alt="Spring Boot"/>
  </a>
</p>

## Live Demo

TBD

---

## 📑 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Requirements](#-requirements)
- [Getting Started](#-getting-started)
    - [Clone the Repository](#1-clone-the-repository)
    - [Start Docker](#2-start-docker)
    - [Build the Project](#3-build-the-project)
    - [Run Project Locally](#4-run-the-project)
    - [Build Docker Image](#5-optional-build-docker-image-docker-should-be-running)
    - [Run Docker Image](#6-optional-running-on-docker)
    - [Deploy on Kubernetes with Helm](#7-optionalrun-on-local-minikube-kubernetes-using-helm-chart)
- [Testing](#-testing)
- [Clean Up](#-cleanup)
- [Reference Documentation](#-reference-documentation)
- [Guides](#-guides)

---

## 🚀 Overview

Spring Boot Multi Version Documentation Using Mkdocs material

---

## 🚀 Features

- Spring Boot 3.5.0 (Java 24)
- RESTful API with CRUD endpoints
- Spring Data JPA (H2 in-memory DB)
- Actuator endpoints enabled
- Async & scheduled tasks
- Docker & multi-stage build
- Kubernetes manifests & Helm chart
- GitHub Actions CI/CD

---

## 📦 Requirements

- Git `2.50.0+`
- Java `24`
- Maven `3.9+`
- Spring Boot `3.5.0`
- (Optional)Docker Desktop (tested on `4.42.0`)
- (Optional) Minikube/Helm for Kubernetes

---

## 🛠️ Getting Started

### 1. Clone the Repository

```sh
git clone https://github.com/deepaksorthiya/spring-boot-multiversion-docs-mkdocs-mike.git
cd spring-boot-multiversion-docs-mkdocs-mike
```

### 2. Start Docker

```sh
docker compose up
```

### 3. Build the Project

```sh
./mvnw clean package -DskipTests
```

OR

for native build run

```bash
./mvnw clean native:compile -Pnative
```

### 4. Run the Project

```sh
./mvnw spring-boot:run
```

OR Jar file

```sh
java -jar .\target\spring-boot-multiversion-docs-mkdocs-mike-1.0.0.jar
```

OR

Run native image directly

```bash
target/spring-boot-multiversion-docs-mkdocs-mike
```

### 5. (Optional) Build Docker Image (docker should be running)

```sh
./mvnw clean spring-boot:build-image -DskipTests
```

OR

To create the native container image, run the following goal:

```bash
./mvnw spring-boot:build-image -Pnative
```

### 6. (Optional) Running On Docker

```sh
docker run -p 8080:8080 --name spring-boot-multiversion-docs-mkdocs-mike deepaksorthiya/spring-boot-multiversion-docs-mkdocs-mike:latest
```

### 7. (Optional)Run on Local minikube Kubernetes using Helm Chart

```sh
cd helm
helm create spring-boot-multiversion-docs-mkdocs-mike
helm lint spring-boot-multiversion-docs-mkdocs-mike
helm install spring-boot-multiversion-docs-mkdocs-mike --values=spring-boot-multiversion-docs-mkdocs-mike/values.yaml spring-boot-multiversion-docs-mkdocs-mike
helm install spring-boot-multiversion-docs-mkdocs-mike spring-boot-multiversion-docs-mkdocs-mike
helm uninstall spring-boot-multiversion-docs-mkdocs-mike
```

---

## 🧪 Testing

- Access the API: [http://localhost:8080](http://localhost:8080)
- H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### Postman API Collection

TBD

### Rest API Endpoints

TBD

### Run Unit-Integration Test Cases

```bash
./mvnw clean test
```

To run your existing tests in a native image, run the following goal:

```bash
./mvnw test -PnativeTest
```

---

## 🧹 Cleanup

```sh
docker compose down -v
```

---

## 📚 Reference Documentation

For further reference, please consider the following sections:

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/maven-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/maven-plugin/build-image.html)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/reference/actuator/index.html)
- [Spring Web](https://docs.spring.io/spring-boot/reference/web/servlet.html)
- [Spring Data JPA](https://docs.spring.io/spring-boot/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [Validation](https://docs.spring.io/spring-boot//io/validation.html)
- [Flyway Migration](https://docs.spring.io/spring-boot/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)

---

## 📚 Guides

The following guides illustrate how to use some features concretely:

- [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Validation](https://spring.io/guides/gs/validating-form-input/)
- [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

---

<p style="text-align: center;">
  <b>Happy Coding!</b> 🚀
</p>
