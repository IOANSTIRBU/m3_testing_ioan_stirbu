package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

//Esto es para JUnit 4 pero sigue funcionando en JUnit 5
//Sirve para ejectar todos los paquetes de un paquete
@RunWith(JUnitPlatform.class)
@SelectPackages("com.example.demo.service")
public class SuiteTest {



}
