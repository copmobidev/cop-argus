package com.cop.argus.car.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cop.argus.car.entity.FuelBill;
import com.cop.argus.car.entity.TimeSpan;
import com.cop.argus.car.service.FuelBillService;
import com.cop.argus.car.service.dao.FuelBillDao;
import com.cop.argus.service.common.BasicService;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("fuelBillService")
public class FuelBillServiceImpl extends BasicService implements
		FuelBillService {

	@Autowired
	private FuelBillDao fuelBillDao;

	@PostConstruct
	private void init() {

	}

	@Override
	public List<FuelBill> get(Integer uid, TimeSpan timeSpan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FuelBill add(Integer uid, Double fuel, Double unitprice,
			Integer fuelType, Integer pid, Long addtime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FuelBill update(Integer id, Double fuel, Double unitprice,
			Integer fuelType, Integer pid, Long addtime) {
		// TODO Auto-generated method stub
		return null;
	}

}
