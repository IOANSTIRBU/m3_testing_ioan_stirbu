package com.example.demo.service.junit;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.domain.pieces.*;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operaciones CRUD sobre la entidad de SmartWatch y mas")
class SmartPhoneServiceImplTest {

    SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

    @BeforeEach
    void setUp() {
        System.out.println("Iniciando Test De SmartPhone");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando Test De SmartPhone");
    }

    @DisplayName("Contar el numero de SmartPhone")
    @Test
    void countSmartPhoneTest() {
        Integer num = service.count();
        assertNotNull(num);
        assertEquals(3, num);

    }


    @DisplayName("Buscar todos los smartwatch")
    @Test
    void findAllTest() {

        List<SmartPhone> smartphones = service.findAll();
        assertNotNull(smartphones);
        assertEquals(3, smartphones.size());

    }

    @DisplayName("Buscar todos los smartwatch que tengan un id null")
    @Test
    void findAllNullTest() {

        List<SmartPhone> smartwatches = service.findAll();

        assertNotNull(smartwatches);
        assertEquals(3, smartwatches.size());


    }


    @DisplayName("Buscar un smartwatch con id 1")
    @Test
    void findOneTest() {

        SmartPhone p1 = service.findOne(1L);
        assertNotNull(p1);
        assertEquals(1L, p1.getId());


    }

    @DisplayName("Comprobando excepción al buscar un smartphone nulo")
    @Test
    void findOneExceptionTest() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.findOne(null)
        );
    }

    @DisplayName("Buscando Smartphone por el id 90")
    @Test
    void findId44Test() {
        SmartPhone p1 = service.findOne(90L);
        assertNull(p1);

    }

    @DisplayName("Comprobando que ocurre si le pasamos un id que es null al smartphone")
    @Test
    void saveNullIdTest() {
        SmartPhone phone2 = new SmartPhone(null, "IPhone X",
                new RAM(2L, "DDR3", 4),
                new Battery(2L, 3500.0),
                new CPU(2L, 2),
                true,
                new Camera(2L, "front camera", 8.5));

        SmartPhone result = service.save(phone2);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());


    }

    @DisplayName("Comprobando que ocurre si le pasamos un id que es cero")
    @Test
    void saveCeroIdTest() {
        SmartPhone phone2 = new SmartPhone(0L, "IPhone X",
                new RAM(2L, "DDR3", 4),
                new Battery(2L, 3500.0),
                new CPU(2L, 2),
                true,
                new Camera(2L, "front camera", 8.5));


        assertEquals(3, service.count());

        SmartPhone result = service.save(phone2);

        assertEquals(4, service.count());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());


    }

    @DisplayName("Comprobar id negativo, no se debería añadir un smartwatch")
    @Test
    void saveIdNegativeTest() {
        SmartPhone phone2 = new SmartPhone(-2L, "IPhone X",
                new RAM(2L, "DDR3", 4),
                new Battery(2L, 3500.0),
                new CPU(2L, 2),
                true,
                new Camera(2L, "front camera", 8.5));

        SmartPhone result = service.save(phone2);
        assertEquals(4, service.count());


    }

    @DisplayName("Comprobar que se actualiza un smartphone que ya es existente")
    @Test
    void saveUpdateTest() {

        SmartPhone phone2 = new SmartPhone(1L, "IPhone X edit",
                new RAM(2L, "DDR3", 4),
                new Battery(2L, 3500.0),
                new CPU(2L, 2),
                true,
                new Camera(2L, "front camera", 8.5));


        // comprobar el numero de smartwatch
        assertEquals(3, service.count());
        SmartPhone result = service.save(phone2);
        // no se crea un smartwatch, se actualiza uno que ya existente
        assertEquals(3, service.count());

        // comprobar id
        assertEquals(1L, result.getId());

        // comprobar name
        SmartPhone p1 = service.findOne(1L);
        assertEquals("IPhone X edit", p1.getName());

    }

    @DisplayName("Obteniendo el maximo de Smartphone por id")
    @Test
    void getMaxSmartPhoneIdTest() {
        SmartPhone p1 = new SmartPhone();
        service.deleteAll();
        SmartPhone phone2 = new SmartPhone(0L, "IPhone X edit",
                new RAM(2L, "DDR3", 4),
                new Battery(2L, 3500.0),
                new CPU(2L, 2),
                true,
                new Camera(2L, "front camera", 8.5));

        SmartPhone result = service.save(phone2);
        assertEquals(1, result.getId());

        SmartPhone smartphone = (SmartPhone) result;
        assertNotNull(smartphone.getCamera());
        Camera camera = new Camera(1L, "Canon", 34D);
        smartphone.setCamera(camera);



    }


    @DisplayName("Borrando smatphone que tengan id null")
    @Test
    void deleteNullTest() {
        boolean result = service.delete(null);
        assertFalse(result);
    }

    @DisplayName("Borrando smartwatch que no contengan el id 60")
    @Test
    void deleteNotContainsKeyTest() {

        boolean result = service.delete(60L);
        assertFalse(result);
    }

    @DisplayName("Borrando smartphone que tenga el id 1")
    @Test
    void deleteTotalTest() {
        boolean result = service.delete(1L);
        assertTrue(result);
    }

    @DisplayName("Borrando todos los smartphones")
    @Test
    void deleteAllTotalTest() {
        assertTrue(service.count() > 0);
        service.deleteAll();
        assertEquals(0, service.count());
    }


    @DisplayName("Buscando por wifi una lista de Smartphones")
    @Test
    void findByWifiTest() {
        List<SmartPhone> smartphones = service.findByWifi(true);

        assertEquals(2, smartphones.size());
        assertTrue(smartphones.get(0).getWifi());
        for (SmartPhone smartphone : smartphones){
            assertTrue(smartphone.getWifi());
        }

    }




}