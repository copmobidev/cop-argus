package com.cop.argus.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cop.argus.car.entity.GasStationPoi;
import com.cop.argus.car.service.PoiService;
import com.cop.argus.car.service.dao.PoiDao;
import com.cop.argus.car.service.entity.GasStationPoiPo;
import com.cop.argus.test.BasicTest;

/**
 * @author chris.liu
 */
public class PoiServiceTest extends BasicTest {

    @Autowired
    private PoiService poiService;

    @Autowired
    private PoiDao poiDao;

    @Test
    public void poiDaoTest() {
        try {
            List<Integer> ids = new ArrayList<Integer>();
            ids.add(1);
            ids.add(2);
            ids.add(3);
            List<GasStationPoiPo> gsPoiPos = poiDao.getPoiByIds(ids);
            if (gsPoiPos != null && gsPoiPos.size() > 0) {
                for (GasStationPoiPo gsPoiPo : gsPoiPos) {
                    System.out.println(gsPoiPo.getAddress());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gasStationPOITest() {
        try {
            double lat = 31.741914;
            double lng = 120.798392;
            double range = 500.0;
            List<GasStationPoi> gasStations = poiService.gasStationNearby(lat,
                    lng, range);
            if (gasStations != null && gasStations.size() > 0) {
                for (GasStationPoi gasStation : gasStations) {
                    System.out.println(gasStation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
