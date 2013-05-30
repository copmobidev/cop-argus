package com.cop.mobi.poi.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cop.mobi.common.Message;
import com.cop.mobi.common.Result;
import com.cop.mobi.common.Result.ResultStatus;
import com.cop.mobi.poi.entity.FuelStation;
import com.cop.mobi.poi.entity.GeoArea;
import com.cop.mobi.poi.service.POIService;
import com.cop.mobi.poi.service.dao.POIDao;
import com.cop.mobi.poi.util.GeoUtil;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class POIServiceImpl extends AbstractAction implements POIService {
	private static final String Tag = "POIServiceImpl";
	private static POIDao poiDao;

	static {
		init();
	}

	private static void init() {
		try {
			poiDao = (POIDao) SpringApplicationContext.getBean("poiDao");

		} catch (Exception e) {
			log.error(String.format("%s", Tag, "init error"), e);
		}
	}

	@Override
	public Result geoAreaList(int parentId) {
		Result result = null;
		try {
			List<GeoArea> gas = poiDao.getGeoArea(parentId);
			if (gas == null || gas.size() == 0) {
				gas = poiDao.getGeoArea(1);
			} else {
				String tmp = String.format("[%s]", StringUtils.join(gas, ","));
				result = new Result(ResultStatus.RS_OK, tmp);
			}
		} catch (Exception e) {
			log.error(String.format("%s:geoAreaList(%d)", Tag, parentId), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public Result localFuelStation(double lat, double lng, double range) {
		Result result = null;
		try {
			List<FuelStation> fuelStations = nearbyFuelStation(lat, lng, range);
			if (fuelStations != null) {
				String tmp = String.format("[%s]",
						StringUtils.join(fuelStations, ","));
				result = new Result(ResultStatus.RS_OK, tmp);
			} else {
				result = new Result(ResultStatus.RS_FAIL, new Message("POI",
						"未发现附近加油站信息"));
			}
		} catch (Exception e) {
			log.error(String.format("%s:getGasStation(%f,%f,%f)", Tag, lat,
					lng, range), e);
			result = new Result(ResultStatus.RS_ERROR, SERVER_INNER_ERROR_MSG);
		}
		return result;
	}

	@Override
	public List<FuelStation> nearbyFuelStation(double lat, double lng,
			double range) {
		try {
			double maxLat = GeoUtil.format(lat, 3) + 0.005;
			double minLat = GeoUtil.format(lat, 3);
			double maxLng = GeoUtil.format(lng, 3) + 0.005;
			double minLng = GeoUtil.format(lng, 3);
			return poiDao.getGasStation(maxLat, minLat, maxLng, minLng);
		} catch (Exception e) {
			log.error(
					String.format("%s:getGasStation(%f,%f,%f)", Tag, lat, lng),
					e);
		}
		return null;
	}
}
