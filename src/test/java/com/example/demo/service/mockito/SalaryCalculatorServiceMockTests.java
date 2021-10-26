package com.example.demo.service.mockito;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeServiceImpl;
import com.example.demo.service.IRPFCalculator;
import com.example.demo.service.IVACalculator;
import com.example.demo.service.SalaryCalculatorService;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@DisplayName("Calculando el servicio de los salarios con Mockito")
public class SalaryCalculatorServiceMockTests {


    @Mock//Dependencia
    IRPFCalculator irpfCalculator;
    @Mock
    IVACalculator ivaCalculator;

    @InjectMocks//Dependendiente
    SalaryCalculatorService service;//SUT


    @BeforeEach
    void setUp() {
        System.out.println("Test SalaryCalculatorService Iniciado");
        MockitoAnnotations.openMocks(this);

    }


    @DisplayName("Calculando el irpf y el iva del salario del employee 1 sin la clase inOrder")
    @Test
    void calculateServiceTest() {



        when(irpfCalculator.calculateIRPF(anyDouble())).thenReturn(4950d);
        when(ivaCalculator.calculateIVA(anyDouble())).thenReturn(7969.5);

        Employee employee = new Employee(1L, "Empleado1", 40);
        double result = service.calculateSalary(employee);

        assertEquals(46919.5, result);
        verify(irpfCalculator).calculateIRPF(anyDouble());
        verify(ivaCalculator).calculateIVA(anyDouble());


    }

    @DisplayName("Calculando el irpf y el iva del salario del employee 1 con la clase InOrder")
    @Test
    void testInOrderMockTest() {

        when(irpfCalculator.calculateIRPF(anyDouble())).thenReturn(4950d);
        when(ivaCalculator.calculateIVA(anyDouble())).thenReturn(7969.5);

        Employee employee = new Employee(1L, "Empleado1", 20);
        double resultado = service.calculateSalary(employee);

        InOrder ordenMock = inOrder(irpfCalculator, ivaCalculator);

        ordenMock.verify(irpfCalculator).calculateIRPF(anyDouble());
        ordenMock.verify(ivaCalculator).calculateIVA(anyDouble());
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test SalaryCalculatorService  Finalizado");

    }


}
