package com.example.demo.service.junit;

import com.example.demo.service.IVACalculator;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculando el IVA de un salario de la entidad de Employee")
public class IVACalculatorTest {


    IVACalculator service = new IVACalculator();

    @DisplayName("Testeando el calculo del IVA")
    @Test
    void calcularIVATest() {
        Double amount = service.calculateIVA(21);
        assertEquals(4.41, amount);
    }

    @DisplayName("Comprobando test para calcular iva con 0")
    @Test
    void calcularIVACeroTest() {
        Double amount = service.calculateIVA(0);
        assertEquals(0, amount);


    }

    @DisplayName("Comprobando test con numero  negativo")
    @Test
    void calcularIVAConNegativo(){
        Double amount = service.calculateIVA(-1);
        assertEquals(-0.21, amount);
    }


}
