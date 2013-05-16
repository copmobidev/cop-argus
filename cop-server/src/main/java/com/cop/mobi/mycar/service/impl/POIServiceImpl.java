package com.cop.mobi.mycar.service.impl;

import java.util.List;

import com.cop.mobi.mycar.entity.GasStation;
import com.cop.mobi.mycar.service.POIService;
import com.cop.mobi.mycar.service.dao.POIDao;
import com.cop.mobi.mycar.util.GeoUtil;
import com.cop.mobi.rest.core.AbstractAction;
import com.cop.mobi.rest.core.SpringApplicationContext;

/**
 * 
 * @author chris.liu
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
	public List<GasStation> getGasStation(double lat, double lng, int range) {
		try {
			double maxLat = GeoUtil.format(lat, 3);
			double minLat = GeoUtil.format(lat, 3) + 0.001;
			double maxLng = GeoUtil.format(lng, 3);
			double minLng = GeoUtil.format(lng, 3);
			poiDao.getGasStation(maxLat, minLat, maxLng, minLng);
		} catch (Exception e) {
			log.error(String.format("%s:getGasStation(%f,%f,%d)", Tag, lat,
					lng, range), e);
		}
		return null;
	}

}
