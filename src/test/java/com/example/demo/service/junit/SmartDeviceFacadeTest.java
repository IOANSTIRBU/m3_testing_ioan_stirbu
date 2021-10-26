package com.example.demo.service.junit;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartDeviceFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Clase que crea un Smarthphone y un SmartWatch y mas")
class SmartDeviceFacadeTest {


    @DisplayName("Creando Smarthphone")
    @Test
    void createSmarthPhoneTest() {

        SmartDevice result = SmartDeviceFacade.createSmartPhone();
        assertNotNull(result);
        assertNotNull(result.getCpu());
        assertTrue(result.getCpu().getOn());
        assertNotNull(result.getRam());
        assertNotNull(result.getBattery());


        //smarthphone
        //instanceof permite saber si un objeto es una instancia de una clase
        assertTrue(result instanceof SmartPhone);
        SmartPhone smartphone = (SmartPhone) result;
        assertNotNull(smartphone.getCamera());
    }

    @DisplayName("Comprobando que se ejecutan las demas propiedades de la clase SmartDeviceFacade")
    @Test
    void SmartDeviceClassTest(){
        SmartDevice result = SmartDeviceFacade.createSmartPhone();
        result.setName("Tamgochi");
        RAM ram = new RAM(1L,"DDR3",6);
        Battery battery = new Battery(1L,4.500);

        CPU cpu = new CPU(1L,4);
        result.setRam(ram);
        result.setBattery(battery);
        result.setCpu(cpu);
        result.setWifi(true);
        assertNotNull(result.toString());


    }

    @DisplayName("Creando SmartWatch")
    @Test
    void createSmartWatchTest() {
        SmartDevice result = SmartDeviceFacade.createSmartWatch();


        assertNotNull(result);
        assertNotNull(result.getCpu());
        assertFalse(result.getCpu().getOn());
        assertNotNull(result.getRam());
        assertNotNull(result.getBattery());

        SmartWatch watch = (SmartWatch) result;
        assertNotNull(watch.getMonitor());


    }

}