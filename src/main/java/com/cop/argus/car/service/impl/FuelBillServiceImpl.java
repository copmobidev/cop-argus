package com.cop.argus.car.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cop.argus.car.entity.FuelBill;
import com.cop.argus.car.entity.FuelBillServiceException;
import com.cop.argus.car.entity.TimeSpan;
import com.cop.argus.car.service.FuelBillService;
import com.cop.argus.car.service.dao.FuelBillDao;
import com.cop.argus.car.service.entity.FuelBillPo;
import com.cop.argus.common.entity.Message;
import com.cop.argus.service.common.BasicService;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("fuelBillService")
public class FuelBillServiceImpl extends BasicService implements
		FuelBillService {

	private static long ONE_DAY = 24 * 60 * 60 * 1000;

	@Autowired
	private FuelBillDao fuelBillDao;

	@PostConstruct
	private void init() {

	}

	@Override
	public List<FuelBill> get(Integer uid, TimeSpan timeSpan)
			throws FuelBillServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FuelBill add(Integer uid, Integer pid, Double fuel,
			Double unitprice, Integer fuelType, Long addtime)
			throws FuelBillServiceException {
		try {
			// 查找前后一天内的加油记录
			List<FuelBillPo> fbPos = fuelBillDao.getFuelBill(uid, addtime
					- ONE_DAY, addtime + ONE_DAY);
			if (fbPos != null && fbPos.size() > 0) {
				throw new FuelBillServiceException(
						FuelBillServiceException.BILL_EXISTED);
			}

			int opt = fuelBillDao.addFuelBill(uid, pid, fuel, unitprice,
					fuelType, addtime);
			if (opt == 1) {
				// 插入操作成功
				
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public FuelBill update(Integer id, Double fuel, Double unitprice,
			Integer fuelType, Integer pid, Long addtime)
			throws FuelBillServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message delete(Integer id) throws FuelBillServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
