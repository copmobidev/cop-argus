package com.cop.argus.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cop.argus.car.entity.CarBrand;
import com.cop.argus.car.service.CarBrandService;
import com.cop.argus.car.service.dao.CarBrandDao;
import com.cop.argus.car.service.entity.CarBrandPo;
import com.cop.argus.service.common.BasicService;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("carBrandService")
public class CarBrandServiceImpl extends BasicService implements
		CarBrandService {

	@Autowired
	private CarBrandDao brandDao;

	private static final Map<Integer, CarBrand> CAR_BRANDS = new HashMap<Integer, CarBrand>();

	@PostConstruct
	protected void init() {
		try {
			List<CarBrandPo> brandPos = brandDao.getAllCarBrands();
			if (brandPos != null && brandPos.size() > 0) {
				for (CarBrandPo brandPo : brandPos) {
					CarBrand brand = new CarBrand(brandPo.getId(),
							brandPo.getManufacturer(), brandPo.getBrand(),
							brandPo.getModel(), brandPo.getEngine(),
							brandPo.getColor(), brandPo.getConfigParam());
					CAR_BRANDS.put(brand.getId(), brand);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("brand init error");
		}
	}

	@Override
	public CarBrand getCarBrandById(int bid) {
		CarBrand brand = null;
		try {
			brand = CAR_BRANDS.get(bid);
		} catch (Exception e) {
			brand = null;
		}
		return brand;
	}
}
