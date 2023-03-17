plugins {
  java
  id("org.springframework.boot") version "3.0.4"
  id("io.spring.dependency-management") version "1.1.0"
}

group = "pl.delukesoft"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  implementation("javax.xml.bind:jaxb-api:2.3.0")
  implementation("javax.validation:validation-api:2.0.1.Final")
  runtimeOnly("com.h2database:h2")
  runtimeOnly("mysql:mysql-connector-java:8.0.32")
}

tasks.withType<Test> {
  useJUnitPlatform()
}
