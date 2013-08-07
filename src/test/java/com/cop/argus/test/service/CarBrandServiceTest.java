package com.cop.argus.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.cop.argus.car.entity.CarBrand;
import com.cop.argus.car.service.CarBrandService;
import com.cop.argus.test.BasicTest;

/**
 * @author chris.liu
 */
public class CarBrandServiceTest extends BasicTest {

    @Resource(name = "carBrandService")
    private CarBrandService carBrandService;

    @Test
    public void brandTest() {
        CarBrand brand = carBrandService.getCarBrandById(2);
        if (brand != null) {
            System.out.println(brand);
        } else {
            System.out.println("brand not found");
        }
    }

}
