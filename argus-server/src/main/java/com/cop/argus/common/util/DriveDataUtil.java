package com.cop.argus.common.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cop.argus.car.entity.OBDConfig;
import com.cop.argus.car.entity.TripData;
import com.cop.argus.car.entity.TripPiece;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveDataUtil {

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yy MM dd hh-mm-ss");

	/**
	 * 解析行程数据
	 * 
	 * @param data
	 */
	public static TripData parserTripData(String data, int uid, int level)
			throws Exception {
		int pieceNum = data.length() / 90 - 1;

		String minuteData = data.substring(0, pieceNum * 90);
		double score = 0.0;
		List<TripPiece> drivePieces = new ArrayList<TripPiece>();
		for (int i = 0; i < pieceNum; ++i) {
			String piece = data.substring(i * 90, (i + 1) * 90);
			TripPiece drivePiece = parserTripPiece(piece, level);
			drivePieces.add(drivePiece);
			score += drivePiece.getScore();
			System.out.println(DataFormater.format(drivePiece));
		}

		String summary = data.substring(pieceNum * 90, data.length());
		TripData td = parserTripSummary(summary);
		if (td != null) {
			td.setMinuteData(minuteData);
			td.setUid(uid);
			td.setScore(GeoUtil.format(score, 2));
			System.out.println(DataFormater.format(td));
			return td;
		}
		return null;
	}

	/**
	 * 解析行程摘要数据
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static TripData parserTripSummary(String data) throws Exception {
		TripData td = new TripData();
		int year = 0, month = 0, day = 0, hour = 0, minute = 0, second = 0;
		double lat = 0.0, lng = 0.0;
		int ele = 0, dist = 0, errDist = 0, clrDist = 0, fuel = 0, avgFuel = 0, avgRPM = 0, maxRPM = 0;
		for (int i = 0; i < data.length(); i += 2) {
			String hex = data.substring(i, i + 2);
			switch (i) {
			case 0:
			case 2:
				break;
			case 4:
				year = Integer.parseInt(hex);
				break;
			case 6:
				month = Integer.parseInt(hex);
				break;
			case 8:
				day = Integer.parseInt(hex);
				break;
			case 10:
				hour = Integer.parseInt(hex);
				break;
			case 12:
				minute = Integer.parseInt(hex);
				break;
			case 14: {
				second = Integer.parseInt(hex);
				Date date = sdf.parse(String.format("%d %d %d %d-%d-%d", year,
						month, day, hour, minute, second));
				td.setBeginTime(date.getTime());
				break;
			}
			case 16:
				lat = 0.0;
				lat = Integer.parseInt(hex);
				break;
			case 18:
				lat += Integer.parseInt(hex) / 100.0;
				break;
			case 20:
				lat += Integer.parseInt(hex) / 10000.0;
				break;
			case 22:
				lat += Integer.parseInt(hex) / 1000000.0;
				td.setBeginLat(format(lat, 6));
				break;
			case 24:
				td.setBeginDir1(hex2char(hex));
				break;
			case 26:
				lng = 0.0;
				lng += Integer.parseInt(hex) * 100.0;
				break;
			case 28:
				lng += Integer.parseInt(hex);
				break;
			case 30:
				lng += Integer.parseInt(hex) / 100.0;
				break;
			case 32:
				lng += Integer.parseInt(hex) / 10000.0;
				break;
			case 34:
				lng += Integer.parseInt(hex) / 1000000.0;
				td.setBeginLng(format(lng, 6));
				break;
			case 36:
				td.setBeginDir2(hex2char(hex));
				break;
			case 38:
				ele = 0;
				ele = Integer.parseInt(hex);
				break;
			case 40:
				ele = ele * 100 + Integer.parseInt(hex);
				td.setBeginEle(ele);
			case 42:
				year = Integer.parseInt(hex);
				break;
			case 44:
				month = Integer.parseInt(hex);
				break;
			case 46:
				day = Integer.parseInt(hex);
				break;
			case 48:
				hour = Integer.parseInt(hex);
				break;
			case 50:
				minute = Integer.parseInt(hex);
				break;
			case 52: {
				second = Integer.parseInt(hex);
				Date date = sdf.parse(String.format("%d %d %d %d-%d-%d", year,
						month, day, hour, minute, second));
				td.setEndTime(date.getTime());
				break;
			}
			case 54:
				lat = 0.0;
				lat = Integer.parseInt(hex);
				break;
			case 56:
				lat += Integer.parseInt(hex) / 100.0;
				break;
			case 58:
				lat += Integer.parseInt(hex) / 10000.0;
				break;
			case 60:
				lat += Integer.parseInt(hex) / 1000000.0;
				td.setEndLat(format(lat, 6));
				break;
			case 62:
				td.setEndDir1(hex2char(hex));
				break;
			case 64:
				lng = 0.0;
				lng += Integer.parseInt(hex) * 100.0;
				break;
			case 66:
				lng += Integer.parseInt(hex);
				break;
			case 68:
				lng += Integer.parseInt(hex) / 100.0;
				break;
			case 70:
				lng += Integer.parseInt(hex) / 10000.0;
				break;
			case 72:
				lng += Integer.parseInt(hex) / 1000000.0;
				td.setEndLng(format(lng, 6));
				break;
			case 74:
				td.setEndDir2(hex2char(hex));
				break;
			case 76:
				ele = 0;
				ele = Integer.parseInt(hex);
				break;
			case 78:
				ele = ele * 100 + Integer.parseInt(hex);
				td.setEndEle(ele);
				break;
			case 82:
				td.setAirPressure(hex2int(hex));
				break;
			case 84:
				td.setFuelLV(hex2int(hex));
				break;
			case 86:
				td.setBat(hex2int(hex));
				break;
			case 88:
				errDist = hex2int(hex);
				break;
			case 90:
				errDist = errDist * 256 + hex2int(hex);
				td.setErrDist(errDist);
				break;
			case 92:
				clrDist = hex2int(hex);
				break;
			case 94:
				clrDist = clrDist * 256 + hex2int(hex);
				td.setClrDist(clrDist);
				break;
			case 96:
				td.setTemp(hex2int(hex));
				break;
			case 98:
				dist = hex2int(hex);
				break;
			case 100:
				dist = dist * 256 + hex2int(hex);
				td.setDist(dist);
				break;
			case 102:
				td.setMaxSPD(hex2int(hex));
				break;
			case 104:
				td.setBstFuel(hex2int(hex));
				break;
			case 106:
				td.setBstSPD(hex2int(hex));
				break;
			case 108:
				fuel = hex2int(hex);
				break;
			case 110:
				fuel = fuel * 256 + hex2int(hex);
				td.setFuel(fuel);
				break;
			case 112:
				avgFuel = hex2int(hex);
				break;
			case 114:
				avgFuel = avgFuel * 256 + hex2int(hex);
				td.setAvgFuel(avgFuel);
				break;
			case 116:
				td.setLstFuelLV(hex2int(hex));
				break;
			case 118:
				td.setAvgCoolTemp(hex2int(hex));
				break;
			case 120:
				td.setMaxCoolTemp(hex2int(hex));
				break;
			case 122:
				td.setAvgPadPos(hex2int(hex));
				break;
			case 124:
				td.setMaxPadPos(hex2int(hex));
				break;
			case 126:
				td.setMinPadPos(hex2int(hex));
				break;
			case 128:
				avgRPM = hex2int(hex);
				break;
			case 130:
				avgRPM = avgRPM * 100 + hex2int(hex);
				td.setAvgRPM(avgRPM);
				break;
			case 132:
				maxRPM = hex2int(hex);
				break;
			case 134:
				maxRPM = maxRPM * 256 + hex2int(hex);
				td.setMaxRPM(maxRPM);
				break;
			case 136:
				td.setAcc(hex2int(hex));
				break;
			case 138:
				td.setBrk(hex2int(hex));
				break;
			case 140:
				td.setOverSPD(hex2int(hex));
				break;
			case 142:
				td.setIdleSPD(hex2int(hex));
				break;
			case 144:
				td.setSliding(hex2int(hex));
				break;
			case 146:
				td.setFast(hex2int(hex));
				break;
			case 148:
				td.setSlow(hex2int(hex));
				break;
			case 150:
				td.setJam(hex2int(hex));
				break;
			}
		}
		int score = 0;
		td.setScore(score);
		return td;
	}

	/**
	 * 解析行程切片数据
	 * 
	 * @param data
	 * @throws Exception
	 */
	public static TripPiece parserTripPiece(String data, int level)
			throws Exception {
		TripPiece tp = new TripPiece();
		int year = 0, month = 0, day = 0, hour = 0, minute = 0, second = 0;
		double lat = 0.0, lng = 0.0;
		int ele = 0, dist = 0, avgRPM = 0, maxRPM = 0, avgFuel = 0;
		for (int i = 0; i < data.length(); i += 2) {
			String hex = data.substring(i, i + 2);
			switch (i) {
			case 0:
			case 2:
				break;
			case 4:
				year = Integer.parseInt(hex);
				break;
			case 6:
				month = Integer.parseInt(hex);
				break;
			case 8:
				day = Integer.parseInt(hex);
				break;
			case 10:
				hour = Integer.parseInt(hex);
				break;
			case 12:
				minute = Integer.parseInt(hex);
				break;
			case 14:
				second = Integer.parseInt(hex);
				Date date = sdf.parse(String.format("%d %d %d %d-%d-%d", year,
						month, day, hour, minute, second));
				tp.setTimestamp(date.getTime());
				break;
			case 16:
				lat = Integer.parseInt(hex);
				break;
			case 18:
				lat += Integer.parseInt(hex) / 100.0;
				break;
			case 20:
				lat += Integer.parseInt(hex) / 10000.0;
				break;
			case 22:
				lat += Integer.parseInt(hex) / 1000000.0;
				tp.setLat(format(lat, 6));
				break;
			case 24:
				tp.setDir1(hex2char(hex));
				break;
			case 26:
				lng += Integer.parseInt(hex) * 100.0;
				break;
			case 28:
				lng += Integer.parseInt(hex);
				break;
			case 30:
				lng += Integer.parseInt(hex) / 100.0;
				break;
			case 32:
				lng += Integer.parseInt(hex) / 10000.0;
				break;
			case 34:
				lng += Integer.parseInt(hex) / 1000000.0;
				tp.setLng(format(lng, 6));
				break;
			case 36:
				tp.setDir2(hex2char(hex));
				break;
			case 38:
				ele = Integer.parseInt(hex);
				break;
			case 40:
				ele = ele * 100 + Integer.parseInt(hex);
				tp.setEle(ele);
				break;
			case 42:
				tp.setMaxSPD(hex2int(hex));
				break;
			case 44:
				tp.setBstFuel(hex2int(hex));
				break;
			case 46:
				tp.setBstSPD(hex2int(hex));
				break;
			case 48:
				dist = hex2int(hex);
				break;
			case 50:
				dist = dist * 256 + hex2int(hex);
				tp.setDist(dist);
				break;
			case 52:
				tp.setAvgSPD(hex2int(hex));
				break;
			case 54:
				avgRPM = hex2int(hex);
				break;
			case 56:
				avgRPM = avgRPM * 256 + hex2int(hex);
				tp.setAvgRPM(avgRPM);
				break;
			case 58:
				maxRPM = hex2int(hex);
				break;
			case 60:
				maxRPM = maxRPM * 256 + hex2int(hex);
				tp.setMaxRPM(maxRPM);
				break;
			case 62:
				tp.setTotalFuel(hex2int(hex));
				break;
			case 64:
				avgFuel = hex2int(hex);
				break;
			case 66:
				avgFuel = avgFuel * 256 + hex2int(hex);
				tp.setAvgFuel(avgFuel);
				break;
			case 68:
				tp.setAvgCalLoad(hex2int(hex));
				break;
			case 70:
				tp.setAvgCoolTemp(hex2int(hex));
				break;
			case 72:
				tp.setAvgPadPos(hex2int(hex));
				break;
			case 74:
				tp.setMaxPadPos(hex2int(hex));
				break;
			case 76:
				tp.setMinPadPos(hex2int(hex));
				break;
			case 78:
				tp.setFuelLv(hex2int(hex));
				break;
			case 80:
				tp.setAcc(hex2int(hex));
				break;
			case 82:
				tp.setBrk(hex2int(hex));
				break;
			case 84:
				tp.setOverSPD(hex2int(hex));
				break;
			case 86:
				tp.setIdleSPD(hex2int(hex));
				break;
			case 88:
				tp.setSliding(hex2int(hex));
				break;
			}
		}
		double score = score(tp, level);
		tp.setScore(score);
		return tp;
	}

	/**
	 * obd config file parser
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static OBDConfig parserOBDConfig(String data) throws Exception {
		OBDConfig obdConfig = new OBDConfig();
		char[] tmp = new char[data.length() / 2];
		String fuelStr = null;
		String mapmafStr = null;
		String fuelCalStr = null;
		String distStr = null;
		String cid = data.substring(86, 118);
		for (int i = 0; i < data.length(); i += 2) {
			tmp[i / 2] = hex2char(data.substring(i, i + 2));
			if (i > 159 && i < 162) {
				fuelStr = data.substring(i, i + 2);
			}
			if (i > 161 && i < 166) {
				mapmafStr = data.substring(i, i + 4);
				i += 4;
			}
			if (i > 165 && i < 168) {
				fuelCalStr = data.substring(i, i + 2);
			}
			if (i > 167 && i < 172) {
				distStr = data.substring(i, i + 4);
				i += 4;
			}
		}
		Object[] obdArray = new Object[10];
		Object[] vinArray = new Object[17];
		Object[] fileArray = new Object[16];
		Object[] calidArray = new Object[8];
		for (int i = 0; i < tmp.length; ++i) {
			if (i > 16 && i < 33) {
				vinArray[i - 16] = tmp[i];
			} else if (i > 33 && i < 42) {
				obdArray[i - 33] = tmp[i];
			} else if (i > 95 && i < 112) {
				fileArray[i - 96] = tmp[i];
			} else if (i > 111 && i < 120) {
				calidArray[i - 112] = tmp[i];
			}
		}
		String obd = StringUtils.join(obdArray);
		obd = StringUtils.isNotBlank(obd) ? obd : null;
		String vin = StringUtils.join(vinArray);
		vin = StringUtils.isNotBlank(vin) ? vin : null;
		String lstFile = StringUtils.join(fileArray);
		lstFile = StringUtils.isNotBlank(lstFile) ? lstFile : null;
		String calid = StringUtils.join(calidArray);
		calid = StringUtils.isNotBlank(calid) ? calid : null;
		boolean fuel = "5E".equalsIgnoreCase(fuelStr);
		boolean maf = "0B4f".equalsIgnoreCase(mapmafStr);
		boolean map = "1050".equalsIgnoreCase(mapmafStr);
		boolean fuelCal = "2F".equalsIgnoreCase(fuelCalStr);
		boolean dist = "2131".equalsIgnoreCase(distStr);
		obdConfig.setFuel(fuel);
		obdConfig.setFuelCal(fuelCal);
		obdConfig.setMaf(maf);
		obdConfig.setMap(map);
		obdConfig.setDist(dist);
		obdConfig.setCid(cid);
		obdConfig.setObd(obd);
		obdConfig.setVin(vin);
		obdConfig.setLstFileName(lstFile);
		obdConfig.setCalid(calid);
		return obdConfig;
	}

	private static int hex2int(String hex) {
		char ch1 = hex.charAt(0);
		int tmp1 = Character.digit(ch1, 16) * 16;
		char ch2 = hex.charAt(1);
		int tmp2 = Character.digit(ch2, 16);
		return tmp1 + tmp2;
	}

	private static char hex2char(String hex)
			throws UnsupportedEncodingException {
		byte[] baKeyword = new byte[1];
		baKeyword[0] = (byte) (0xff & Integer.parseInt(hex.substring(0, 2), 16));
		return new String(baKeyword, "utf-8").charAt(0);
	}

	private static double format(double in, int n) {
		double p = Math.pow(10, n);
		return Math.round(in * p) / p;
	} // format

	/**
	 * 计算一次行程得分
	 * 
	 * @param tripData
	 * @return
	 */
	private static double score(TripPiece tripPiece, int level) {
		double avgSPD = tripPiece.getAvgSPD();
		double score = 0;
		if (avgSPD < 120) {
			score = (144 - (120 - avgSPD) * (120 - avgSPD) / 100)
					* (tripPiece.getSliding() / 100)
					- (tripPiece.getAcc() + tripPiece.getBrk())
					* (Math.sqrt(level) + 1);
		} else {
			score = -(avgSPD - 120) * (avgSPD - 120) / 100
					* (tripPiece.getSliding() / 100)
					- (tripPiece.getAcc() + tripPiece.getBrk())
					* (Math.sqrt(level) + 1);
		}
		score = score > 0.0 ? score : 0.0;
		score = GeoUtil.format(score, 2);
		return score;
	}
}
