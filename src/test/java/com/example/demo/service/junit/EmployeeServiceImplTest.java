package com.example.demo.service.junit;

import com.example.demo.domain.Employee;
import com.example.demo.domain.SmartWatch;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operaciones CRUD sobre la entidad de Employee")
public class EmployeeServiceImplTest {




    // SUT - System Under Test - La clase a testear
    EmployeeServiceImpl service;



    @BeforeEach
    void setUp() {

        EmployeeRepository repository = new EmployeeRepositoryImpl();
        service = new EmployeeServiceImpl(repository);
    }


    @DisplayName("count()->Contar el numero de employees")
    @Test
    void countTest() {
        Integer num = service.count();

        assertNotNull(num);
        assertEquals(3, num);

    }


    @DisplayName("findAll():Buscar todos los empleados ")
    @Test
    void findAllTest() {

        List<Employee> employees = service.findAll();
        assertNotNull(employees);
        assertEquals(3, employees.size());

    }

    @DisplayName("Buscando todos los empleados que contengan un id Null")
    @Test
    void findAllNullTest() {
        List<Employee> employees = service.findAll();
        assertNull(null);

        assertEquals(3, employees.size());
    }


    @DisplayName("Buscando un empleado o employee con id 1")
    @Test
    void findOneTest() {
        Employee employee = service.findOne(1L);
        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertNotNull(employee.getName());


    }

    @Test
    void EmployeeClassTest(){
        Employee employee = new Employee();
    }

    @DisplayName("Clase de Employee Entity")
    @Test
    void claseEntityNameEmployeeTest(){
        Employee employee = service.findOne(1L);
        employee.setName("Juan");
        assertNotNull(employee.getName());
    }

    @DisplayName("Clase de Employee Entity")
    @Test
    void claseEntityAgeEmployeeTest(){
        Employee employee = service.findOne(1L);
        employee.setAge(50);
        assertNotNull(employee.getAge());
    }



    @DisplayName("Buscando un empleado con id 33 o que no existe")
    @Test
    void findOneEmployee33Test() {
        Employee employee = service.findOne(33L);
        assertNull(employee);

    }

    @DisplayName("Buscando un empleado que tenga un id negativo en este caso -3")
    @Test
    void findOneEmployeeNegativeTest() {
        Employee result = service.findOne(-3L);

        assertNull(result);

    }

    @DisplayName("Devolviendo un optional del id de employee 1")
    @Test
    void findOneOptionalTest(){
        Optional<Employee> employeeOpt = service.findOneOptional(1L);
        assertNotNull(employeeOpt);
        assertTrue(employeeOpt.isPresent());

        Employee employee = employeeOpt.get();
        Long id = employee.getId();


        assertEquals(1L, id);


    }

    @DisplayName("Comprobar excepcion al buscar un employee null")
    @Test
    void findOneOptionalExceptionTest(){

        assertThrows(
                IllegalArgumentException.class, () -> service.findOne(null)
        );

    }

    @DisplayName("Buscando un optional que tenga un id Null y este vacio")
    @Test
    void findOneOptionalNullTest() {
        Optional<Employee> employeeOpt = service.findOneOptional(null);
        assertTrue(employeeOpt.isEmpty());
    }


    @DisplayName("save()->Con este metodo guardo un Employee")
    @Test
    void saveEmployeeCeroTest() {
        Employee employee = new Employee(0L,"Employe1",30);
        Employee resultado = service.save(employee);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals(4,resultado.getId());

    }


    @DisplayName("Guardando un empleado con id null")
    @Test
    void saveIdNullTest(){
        Employee employee = new Employee(null,"Empleado1",40);
        Employee resultado = service.save(employee);
        assertNull(resultado);



    }

    @DisplayName("Obteniendo el maximo de id en la entidad de Employee")
    @Test
    void getMaxIdEmployeeTest() {
    service.deleteAll();
    Employee emp1 = new Employee(0L, "Emp 1", 30);
    Employee result = service.save(emp1);
    assertEquals(1, result.getId());




    }


    @DisplayName("delete()->Eliminar un empleado por id")
    @Test
    void deleteIdEmployeeTest() {
        boolean resultado = service.delete(1L);
        assertTrue(resultado);

    }



    @DisplayName("Eliminar un empleado por id null")
    @Test
    void deleteIdNullEmployeeTest() {
        Boolean employee = service.delete(null);
        assertFalse(employee);
    }

    @DisplayName("Eliminando un empleado con id negativo")
    @Test
    void deleteIdNegativeEmployeeTest() {
        Boolean employee = service.delete(-2L);
        assertFalse(employee);
    }


    @DisplayName("deleteAll()->Eliminando todos los empleados")
    @Test
    void deleteAllEmployeesTest(){
        service.deleteAll();
        assertTrue(true);



    }



}
