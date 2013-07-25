package com.cop.argus.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cop.argus.account.service.dao.AccountDao;
import com.cop.argus.account.service.entity.UserPo;
import com.cop.argus.car.entity.DriveData;
import com.cop.argus.car.entity.DriveDataServiceException;
import com.cop.argus.car.entity.TimeSpan;
import com.cop.argus.car.entity.TripData;
import com.cop.argus.car.service.DriveDataService;
import com.cop.argus.car.service.dao.DriveDataDao;
import com.cop.argus.common.entity.Message;
import com.cop.argus.common.util.DriveDataUtil;
import com.cop.argus.service.common.BasicService;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("driveDataService")
public class DriveDataServiceImpl extends BasicService implements
		DriveDataService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private DriveDataDao driveDataDao;

	@Override
	public Message uploadDriveData(int uid, List<String> datas)
			throws DriveDataServiceException {
		try {
			UserPo userPo = accountDao.getUserById(uid);
			if (userPo == null) {
				throw new DriveDataServiceException(
						DriveDataServiceException.DATA_ERROR);
			}
			int score = userPo.getScore();
			int level = userPo.getLevel();
			for (String data : datas) {
				TripData td = DriveDataUtil.parserTripData(data, uid,
						userPo.getLevel());
				driveDataDao.addDriveData(td.toDBString());
				score += td.getScore();
				if (score > 50) {
					level = (score - 50) / 20 + 1;
				} else {
					level = 1;
				}
			}
			String vals = String.format("score=%s,level=%s", score, level);
			accountDao.updateUserInfo(uid, vals);

			return new Message("恭喜", "成功上次行程数据");
		} catch (Exception e) {
			throw new DriveDataServiceException(
					DriveDataServiceException.UNKNOW_ERROR);
		}
	}

	@Override
	public List<DriveData> getDriveData(int uid, TimeSpan span) {
		// TODO Auto-generated method stub
		return null;
	}
}
