package com.example.demo.service.junit;

import com.example.demo.service.IRPFCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Calculando el IRPF de un salario de la entidad de Employee")
public class IRPFCalculatorTest {

    IRPFCalculator service = new IRPFCalculator();
    @DisplayName("Testeando el calculo del IRPF")
    @Test
    void calcularIRPFTest() {
        Double amount = service.calculateIRPF(15);
        assertEquals(2.25, amount);

    }

    @DisplayName("Testeando el calculo del IRPF con pasarle un 0")
    @Test
    void calcularIRPFCeroTest() {
        Double amount = service.calculateIRPF(0);
        assertEquals(0,amount);
    }

    @DisplayName("Testeando el calculo del IRPF con pasarle un -1")
    @Test
    void calcularIRPFNegativoTest() {
        Double amount = service.calculateIRPF(-1);
        assertEquals(-0.15,amount);
    }


}
