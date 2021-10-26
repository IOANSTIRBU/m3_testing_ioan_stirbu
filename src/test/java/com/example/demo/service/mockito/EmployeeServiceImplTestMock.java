package com.example.demo.service.mockito;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Ejecutando test con operaciones CRUD sobre la entidad de Employee con Mockito")
public class EmployeeServiceImplTestMock {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.out.println("Ejecutandose Mockito");
    }

    @Mock//Dependencia
    EmployeeRepository employeeRepository;

    @InjectMocks//Dependendiente
    EmployeeServiceImpl service;


    @DisplayName("Contando el numero de employees")
    @Test
    void countTestMock() {
        //1.	Configurar mock/s: when(), thenReturn()
        when(employeeRepository.count()).thenReturn(3);

        //2.	Ejecutar el comportamiento o método a testear
        Integer resultado = service.count();
        // 3.	Aserciones y verificaciones
        assertEquals(3, resultado);
        verify(employeeRepository).count();
    }


    @DisplayName("findAll():Buscar todos los empleados ")
    @Test
    void findAllMockTest() {
        //1.	Configurar mock/s: when(), thenReturn()
        /**
         * debemos de crear una Lista ficticia de Employess
         * con datos ficticios  para que nos permita hacer el test
         *
         */
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Nombre1", 40),
                new Employee(1L, "Nombre2", 50)
        );

        when(employeeRepository.findAll()).thenReturn(employees);

        //2.	Ejecutar el comportamiento o método a testear
        List<Employee> result = service.findAll();
        // 3.	Aserciones y verificaciones
        assertEquals(2, result.size());
        verify(employeeRepository).findAll();


    }


    @DisplayName("Comprobando si hay un employee con id 1")
    @Test
    void findOneMockTest() {
        //1.	Configurar mock/s: when(), thenReturn()
        Employee employee = new Employee();
        when(employeeRepository.findOne(1L)).thenReturn(employee);
        //2.	Ejecutar el comportamiento o método a testear
        service.findOne(1L);
        // 3.	Comprobar y verificar
        assertNotNull(employee);
        verify(employeeRepository).findOne(1L);

    }



    @DisplayName("Buscando un Employee con el metodo de->findOneOptional()")
    @Test
    void finOneOptionalMockTest() {
        //1.	Configurar mock/s: when(), thenReturn()
        Employee employee = new Employee(1L, "Employee1", 60);
        when(employeeRepository.findOne(anyLong())).thenReturn(employee);
        //2. Ejecutar el comportamiento o método a testear
        Optional<Employee> employeeOpt = service.findOneOptional(1L);
        //3. Aserciones y verificaciones
        assertTrue(employeeOpt.isPresent());
        verify(employeeRepository).findOne(anyLong());
    }


    @DisplayName("Buscando un employee que devuelva un null")
    @Test
    void findOneNullOptionalTest() {

        // PONER thenReturn(null) Y NO PONER NADA ES LO MISMO PORQUE
        // SI EL MOCK NO ESTÁ CONFIGURADO ENTONCES DEVUELVE NULL
        // POR TANTO NO ES NECESARIO CONFIGURAR EL MOCK
        // PARA QUE DEVUELVA NULL PORQUE
        // YA DEVUELVE NULL SIN CONFIGURARLO

        //1.	Configurar mock/s: when(), thenReturn()
        when(employeeRepository.findOne(anyLong())).thenReturn(null);
        //2. Ejecutar el comportamiento o método a testear
        Optional<Employee> employeeOpt = service.findOneOptional(1L);
        //3. Aserciones y verificaciones
        assertTrue(employeeOpt.isEmpty());
        verify(employeeRepository).findOne(anyLong());


    }


    @DisplayName("Buscando un Employee con Optional y si arroja una excepcion")
    @Test
    void findOneOptionalExceptionTest() {

        //1.	Configurar mock/s: when(), thenReturn()
        when(employeeRepository.findOne(anyLong())).thenThrow(IllegalArgumentException.class);
        //2. Ejecutar el comportamiento o método a testear
        Optional<Employee> employeeOpt = service.findOneOptional(100L);
        //3. Aserciones y verificaciones
        assertTrue(employeeOpt.isEmpty());
        verify(employeeRepository).findOne(anyLong());
    }

    @DisplayName("Guardando un Employee-> save()")
    @Test
    void saveEmployeeMockTest() {
        //1.	Configurar mock/s: when(), thenReturn()
        Employee employee = new Employee();
        when(employeeRepository.save(employee)).thenReturn(employee);

        //2.	Ejecutar el comportamiento o método a testear
        Employee result = service.save(employee);
        // 3. Aserciones y verificaciones
        assertEquals(employee.getId(), result.getId());
        verify(employeeRepository).save(result);

    }



    @DisplayName("Guardando un empleado sino se encuentra lanzara una excepcion")
    @Test
    void saveMockTest() {

        Employee employee = new Employee(1L, "NuevoEmpleado", 30);
        Employee employee2 = new Employee(2L, "SegundoEmpleado", 40);

        //1.	Configurar mock/s: when(), thenReturn()
        when(employeeRepository.save(any())).thenReturn(employee);

        //2. Ejecutar el comportamiento o método a testear
        Employee resultado = service.save(employee);
        Employee resultado2 = service.save(employee2);
        //3. Aserciones y verificaciones
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertNotNull(resultado2);
        assertEquals(1L, resultado2.getId());


    }

    @DisplayName("Guardando un empleado que es null")
    @Test
    void saveEmployeeNullTest(){
        Employee employee = new Employee(null, "NuevoEmpleado", 30);

        when(employeeRepository.save(any())).thenReturn(null);

        Employee result = service.save(employee);

        assertNull(result);


    }



    @DisplayName("Borrando un empleado por id")
    @Test
    void  deleteTest(){
        when(employeeRepository.delete(any())).thenReturn(true);

        boolean resultado = service.delete(1L);

        assertTrue(resultado);
    }


    @DisplayName("Boranndo todos los empleados")
    @Test
    void deleteAllTest(){
        service.deleteAll();
        verify(employeeRepository).deleteAll();

    }


}