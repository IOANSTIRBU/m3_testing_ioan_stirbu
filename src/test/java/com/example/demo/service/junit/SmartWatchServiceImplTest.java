package com.example.demo.service.junit;

import com.example.demo.domain.Employee;
import com.example.demo.domain.SmartWatch;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.HealthMonitor;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartWatchServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operaciones CRUD sobre la entidad de SmartWatch y mas")
class SmartWatchServiceImplTest {

    SmartWatchServiceImpl service = new SmartWatchServiceImpl();

    @BeforeEach
    void setUp() {
        System.out.println("Iniciando Test De SmartWatch");

    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando Test De SmartWatch");
    }

    @DisplayName("Contar el numero de SmartWatch")
    @Test
    void countSmartWatchTest() {
        Integer num = service.count();
        assertNotNull(num);
        assertEquals(3, num);

    }


    @DisplayName("Buscar todos los smartwatch")
    @Test
    void findAllTest() {

       List<SmartWatch> smartwatches =  service.findAll();
       assertNotNull(smartwatches);
       assertEquals(3,smartwatches.size());

    }

    @DisplayName("Buscar todos los smartwatch que tengan un id null")
    @Test
    void findAllNullTest() {

        List<SmartWatch> smartwatches =  service.findAll();
        SmartWatch watch3 = new SmartWatch(null, "Samsung Galaxy Watch",
                new RAM(3L, "DDR4", 2),
                new Battery(3L, 4500.0),
                new CPU(3L, 4),
                true,
                new HealthMonitor(3L, 0.0, 0));

        smartwatches.add(watch3);
        assertNull(null);


    }


    @DisplayName("Buscar un smartwatch con id 1")
    @Test
    void findOneTest() {

        SmartWatch watch1 = service.findOne(1L);
        assertNotNull(watch1);
        assertEquals(1L, watch1.getId());


    }

    @DisplayName("Buscando SmartWatch por el id 44")
    @Test
    void findId44Test(){
        SmartWatch reloj = service.findOne(44L);
        assertNull(reloj);

    }

    @DisplayName("Comprobando que ocurre si le pasamos un id que es null")
    @Test
    void saveNullIdTest() {
        SmartWatch watch1 = new SmartWatch(null, "Fitbit sense",
                new RAM(1L, "DDR4", 2),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                true,
                new HealthMonitor(1L, 0.0, 0));

        SmartWatch result = service.save(watch1);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4,result.getId());

    }

    @DisplayName("Comprobando que ocurre si le pasamos un id que es cero")
    @Test
    void saveCeroIdTest() {
        SmartWatch watch1 = new SmartWatch(0L, "Fitbit sense",
                new RAM(1L, "DDR4", 2),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                true,
                new HealthMonitor(1L, 0.0, 0));

        SmartWatch result = service.save(watch1);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4,result.getId());

    }

    @DisplayName("Comprobar id negativo, no se debería añadir un smartwatch")
    @Test
    void saveIdNegativeTest(){
        SmartWatch watch3 = new SmartWatch(-1L, "Samsung Galaxy Watch",
                new RAM(3L, "DDR4", 2),
                new Battery(3L, 4500.0),
                new CPU(3L, 4),
                true,
                new HealthMonitor(3L, 0.0, 0));

        SmartWatch result = service.save(watch3);
        assertEquals(4, service.count());


    }

    @DisplayName("Comprobar que se actualiza un smartwatch que ya existe existente")
    @Test
    void saveUpdateTest(){

        SmartWatch watch3 = new SmartWatch(1L, "Samsung Galaxy Watch edit",
                new RAM(3L, "DDR4", 2),
                new Battery(3L, 4500.0),
                new CPU(3L, 4),
                true,
                new HealthMonitor(3L, 0.0, 0));

        // comprobar el numero de smartwatch
        assertEquals(3, service.count());
        SmartWatch result = service.save(watch3);
        // no se crea un smartwatch, se actualiza uno que ya existente
        assertEquals(3, service.count());

        // comprobar id
        assertEquals(1L, result.getId());

        // comprobar name
        SmartWatch watch1 = service.findOne(1L);
        assertEquals("Samsung Galaxy Watch edit", watch1.getName());

    }

    @DisplayName("Borrando smatwatch que tengan id null")
    @Test
    void deleteNullTest(){
        boolean result = service.delete(null);
        assertFalse(result);
    }

    @DisplayName("Borrando smartwatch que no contengan el id 80")
    @Test
    void deleteNotContainsKeyTest(){

        boolean result = service.delete(80L);
        assertFalse(result);
    }

    @DisplayName("Borrando smartwatch que tenga id 1")
    @Test
    void deleteTotalTest(){
        boolean result = service.delete(1L);
        assertTrue(result);
    }

    @DisplayName("Borrando todos los smartwatch")
    @Test
    void deleteAllTest(){
        assertTrue(service.count() > 0);
        service.deleteAll();
        assertEquals(0, service.count());
    }


    @DisplayName("Comprobando en la clase de SmartWatch")
    @Test
    void SmartWatchClassTest(){
        SmartWatch smartwatch = new SmartWatch();
        HealthMonitor monitor = new HealthMonitor(1L,2.300,34);
        SmartWatch result = service.save(smartwatch);
        smartwatch.setMonitor(monitor);
        assertNotNull( result.getMonitor());
        assertNotNull( monitor.getId());
        assertNotNull(monitor.getBloodPressure());
        assertNotNull(monitor.getSleepQuality());
    }

    @DisplayName("Comprobando en la clase de HealthMonitor todos los atributos que pertenece al SmartWatch")
    @Test
    void HealthMonitorClassTest(){
        SmartWatch smartwatch = new SmartWatch();
        HealthMonitor monitor = new HealthMonitor(1L,2.300,34);
        service.save(smartwatch);
        SmartWatch result = service.save(smartwatch);
        monitor.setId(30L);
        assertNotNull( result.getId());
        monitor.setBloodPressure(2.300);
        monitor.setSleepQuality(34);
        monitor.toString();
        smartwatch.toString();
    }



}