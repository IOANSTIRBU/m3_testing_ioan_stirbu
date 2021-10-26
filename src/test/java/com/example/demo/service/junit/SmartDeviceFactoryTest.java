package com.example.demo.service.junit;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.service.SmartDeviceFacade;
import com.example.demo.service.SmartDeviceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Comprobando si se pueden crear tipos de SmartPhones y SmartWatch")
class SmartDeviceFactoryTest {

    /**
     * 1)	return SmartDeviceFacade.createSmartPhone();
     * 3)return SmartDeviceFacade.createSmartWatch();
     * 4)throw new IllegalArgumentException("Unexpected value: " + type);
     */


    @DisplayName("CreatByType de Smartphone")
    @Test
    void createByTypeSmartphone() {

        SmartDevice result = SmartDeviceFactory.createByType("phone");

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


    @DisplayName("CreatByType de Smartwatch")
    @Test
    void createByTypeSmartWatch() {

        SmartDevice result = SmartDeviceFactory.createByType("watch");

        assertNotNull(result);
        assertNotNull(result.getCpu());
        assertFalse(result.getCpu().getOn());
        assertNotNull(result.getRam());
        assertNotNull(result.getBattery());

        SmartWatch watch = (SmartWatch) result;
        assertNotNull(watch.getMonitor());

    }

    @DisplayName("Comprobando si podria saltar una excepcion")
    @Test
    void creatByTypeException(){

        assertThrows(IllegalArgumentException.class,()->SmartDeviceFactory.createByType("patata"));



    }

}
