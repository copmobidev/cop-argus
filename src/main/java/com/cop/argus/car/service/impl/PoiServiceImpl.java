package com.cop.argus.car.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cop.argus.car.entity.GasStationPoi;
import com.cop.argus.car.service.PoiService;
import com.cop.argus.car.service.dao.PoiDao;
import com.cop.argus.car.service.entity.GasStationPoiPo;
import com.cop.argus.car.service.entity.GeoAreaPo;
import com.cop.argus.car.service.entity.IdxPoiPo;
import com.cop.argus.common.util.GeoUtil;
import com.cop.argus.service.common.BasicService;

/**
 * 
 * @author chris.liu
 * 
 */
@Service("poiService")
public class PoiServiceImpl extends BasicService implements PoiService {

	@Autowired
	private PoiDao poiDao;

	private static List<GeoAreaPo> GEO_AREAS = null;
	private static List<IdxPoiPo> POI_IDXs = null;

	@PostConstruct
	public void init() {

		try {
			GEO_AREAS = poiDao.getAllGeoArea();
			if (GEO_AREAS != null && GEO_AREAS.size() > 0) {
				log.info("poi service init geo area data successfully!");
			} else {
				GEO_AREAS = null;
			}
		} catch (Exception e) {
			log.error("poi service init error", e);
		}

		try {
			POI_IDXs = poiDao.getAllPoiIdx();
			if (GEO_AREAS != null && GEO_AREAS.size() > 0) {
				log.info("poi service init poi idx data successfully!");
			} else {
				GEO_AREAS = null;
			}
		} catch (Exception e) {
			log.error("poi service init error", e);
		}
	}

	@Override
	public List<GasStationPoi> gasStationNearby(double lat, double lng,
			double range) {
		List<Integer> ids = new ArrayList<Integer>();
		for (IdxPoiPo idx : POI_IDXs) {
			if (GeoUtil.distanceTo(lat, lng, idx.getLat(), idx.getLng()) < 50) {
				ids.add(idx.getId());
			}
		}
		if (ids.size() > 0) {
			List<GasStationPoiPo> gasStations = poiDao.getPoiByIds(ids);

			if (gasStations != null && gasStations.size() > 0) {

			}
		}

		return null;
	}
}
