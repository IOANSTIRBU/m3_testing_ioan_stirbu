##Proyecto M3 con JUnit & Mockito

En el `pom.xml` encontrarás las dependecias de:

`<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>
<project>
    <groupId>org.example</groupId>
    <artifactId>m3-junit</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <!--Para poner las dependencias de JUnit 5 -->
        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>

        <!-- Para poner la Suite de JUnit 4 -->
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-runner</artifactId>
            <version>1.8.1</version>
            <scope>test</scope>
        </dependency>

        <!-- Para poner la dependencia de Mockito -->
        <!-- https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>4.0.0</version>
            <scope>test</scope>
        </dependency>


    </dependencies>

</project>`


-Dos directorios:
1. El programa a testear

  a) El directorio: `src/main/java/com.example.demo`
    
2. Donde se van a realizar los test

b)El directorio: `test/java/com.example.demo.service`
que tendra dos subdirectorios despues del directorio service uno para el JUnit y otro para Mockito

Por último un directorio que tendra un Test llamado "SuiteTest" que servira
para poder ejecutar todos los test de golpe