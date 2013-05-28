package com.cop.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import com.cop.entity.DrivingPiece;
import com.cop.entity.DrivingSummary;
import com.cop.entity.OBDConfig;

/**
 * 
 * @author chris.liu
 * 
 */
public class DataParser {

	public static DrivingPiece parseDrivingPiece(String data) throws Exception {
		DrivingPiece drivingPiece = new DrivingPiece();
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yy MM dd hh-mm-ss");
		for (int i = 0; i < 80; i += 2) {
			String hex = data.substring(i, i + 2);
			switch (i) {
			case 0:
			case 2:
				break;
			case 4:
				year = hex2int(hex);
				break;
			case 6:
				month = hex2int(hex);
				break;
			case 8:
				day = hex2int(hex);
				break;
			case 10:
				hour = hex2int(hex);
				break;
			case 12:
				minute = hex2int(hex);
				break;
			case 14:
				second = hex2int(hex);
				String date = String.format("%d %d %d %d-%d-%d", year, month,
						day, hour, minute, second);
				drivingPiece.timestamp = sdf.parse(date).getTime();
				break;
			case 16:
				drivingPiece.lat = hex2int(hex);
				break;
			case 18:
				drivingPiece.lat += hex2int(hex) / 100.0;
				break;
			case 20:
				drivingPiece.lat += hex2int(hex) / 10000.0;
				break;
			case 22:
				drivingPiece.lat += hex2int(hex) / 1000000.0;
				drivingPiece.lat = format(drivingPiece.lat, 6);
				break;
			case 24:
				drivingPiece.dir1 = hex2char(hex);
				break;
			case 26:
				drivingPiece.lng += hex2int(hex) * 100.0;
				break;
			case 28:
				drivingPiece.lng += hex2int(hex);
				break;
			case 30:
				drivingPiece.lng += hex2int(hex) / 100.0;
				break;
			case 32:
				drivingPiece.lng += hex2int(hex) / 10000.0;
				break;
			case 34:
				drivingPiece.lng += hex2int(hex) / 1000000.0;
				drivingPiece.lng = format(drivingPiece.lng, 6);
				break;
			case 36:
				drivingPiece.dir2 = hex2char(hex);
				break;
			case 38:
				drivingPiece.ele = hex2int(hex);
				break;
			case 40:
				drivingPiece.maxSPD = hex2int(hex);
				break;
			case 42:
				drivingPiece.bstFuel = hex2int(hex);
				break;
			case 44:
				drivingPiece.distH = hex2int(hex);
				break;
			case 46:
				drivingPiece.distL += hex2int(hex);
				break;
			case 48:
				drivingPiece.avgSPD += hex2int(hex);
				break;
			case 50:
				drivingPiece.avgRPMH += hex2int(hex);
				break;
			case 52:
				drivingPiece.avgRPML += hex2int(hex);
				break;
			case 54:
				drivingPiece.avgFuel = hex2int(hex);
				break;
			case 56:
				drivingPiece.totalFuel = hex2int(hex);
				break;
			case 58:
				drivingPiece.calLoad = hex2int(hex);
				break;
			case 60:
				drivingPiece.coolTemp += hex2int(hex);
				break;
			case 62:
				drivingPiece.avgPadPos += hex2int(hex);
				break;
			case 64:
				drivingPiece.maxPadPos += hex2int(hex);
				break;
			case 66:
				drivingPiece.minPadPos = hex2int(hex);
				break;
			case 68:
				drivingPiece.fuelLV = hex2int(hex);
				break;
			case 70:
				drivingPiece.acc = hex2int(hex);
				break;
			case 72:
				drivingPiece.brk = hex2int(hex);
				break;
			case 74:
				drivingPiece.overSPD = hex2int(hex);
				break;
			case 76:
				drivingPiece.idleSPD = hex2int(hex);
				break;
			case 78:
				drivingPiece.sliding = hex2int(hex);
				break;
			}
		}
		return drivingPiece;
	}

	public static DrivingSummary parseDrivingSummary(String data)
			throws Exception {
		DrivingSummary drivingSummary = new DrivingSummary();
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yy MM dd hh-mm-ss");
		for (int i = 0; i < data.length(); i += 2) {
			String hex = data.substring(i, i + 2);
			switch (i) {
			case 0:
			case 2:
				break;
			case 4:
				year = hex2int(hex);
				break;
			case 6:
				month = hex2int(hex);
				break;
			case 8:
				day = hex2int(hex);
				break;
			case 10:
				hour = hex2int(hex);
				break;
			case 12:
				minute = hex2int(hex);
				break;
			case 14:
				second = hex2int(hex);
				String date = String.format("%d %d %d %d-%d-%d", year, month,
						day, hour, minute, second);
				drivingSummary.startTime = sdf.parse(date).getTime();
				break;
			case 16:
				drivingSummary.startLat = hex2int(hex);
				break;
			case 18:
				drivingSummary.startLat += hex2int(hex) / 100.0;
				break;
			case 20:
				drivingSummary.startLat += hex2int(hex) / 10000.0;
				break;
			case 22:
				drivingSummary.startLat += hex2int(hex) / 1000000.0;
				drivingSummary.startLat = format(drivingSummary.startLat, 6);
				break;
			case 24:
				drivingSummary.startDir1 = hex2char(hex);
				break;
			case 26:
				drivingSummary.startLng += hex2int(hex) * 100.0;
				break;
			case 28:
				drivingSummary.startLng += hex2int(hex);
				break;
			case 30:
				drivingSummary.startLng += hex2int(hex) / 100.0;
				break;
			case 32:
				drivingSummary.startLng += hex2int(hex) / 10000.0;
				break;
			case 34:
				drivingSummary.startLng += hex2int(hex) / 1000000.0;
				drivingSummary.startLng = format(drivingSummary.startLng, 6);
				break;
			case 36:
				drivingSummary.startDir2 = hex2char(hex);
				break;
			case 38:
				drivingSummary.startEle = hex2int(hex);
				break;
			case 40:
				year = hex2int(hex);
				break;
			case 42:
				month = hex2int(hex);
				break;
			case 44:
				day = hex2int(hex);
				break;
			case 46:
				hour = hex2int(hex);
				break;
			case 48:
				minute = hex2int(hex);
				break;
			case 50:
				second = hex2int(hex);
				drivingSummary.endTime = sdf.parse(
						String.format("%d %d %d %d-%d-%d", year, month, day,
								hour, minute, second)).getTime();
				break;
			case 52:
				drivingSummary.endLat = hex2int(hex);
				break;
			case 54:
				drivingSummary.endLat += hex2int(hex) / 100.0;
				break;
			case 56:
				drivingSummary.endLat += hex2int(hex) / 10000.0;
				break;
			case 58:
				drivingSummary.endLat += hex2int(hex) / 1000000.0;
				drivingSummary.endLat = format(drivingSummary.endLat, 6);
				break;
			case 60:
				drivingSummary.endDir1 = hex2char(hex);
				break;
			case 62:
				drivingSummary.endLng += hex2int(hex) * 100.0;
				break;
			case 64:
				drivingSummary.endLng += hex2int(hex);
				break;
			case 66:
				drivingSummary.endLng += hex2int(hex) / 100.0;
				break;
			case 68:
				drivingSummary.endLng += hex2int(hex) / 10000.0;
				break;
			case 70:
				drivingSummary.endLng += hex2int(hex) / 1000000.0;
				drivingSummary.endLng = format(drivingSummary.endLng, 6);
				break;
			case 72:
				drivingSummary.endDir2 = hex2char(hex);
				break;
			case 74:
				drivingSummary.endEle = hex2int(hex);
				break;
			case 76:
				drivingSummary.time = hex2int(hex);
				break;
			case 78:
				drivingSummary.airPressure = hex2int(hex);
				break;
			case 80:
				drivingSummary.fuelLV = hex2int(hex);
				break;
			case 82:
				drivingSummary.bat = hex2int(hex);
				break;
			case 84:
				break;
			case 86:
				break;
			case 88:
				break;
			case 90:
				break;
			case 92:
				drivingSummary.temp = hex2int(hex);
				break;
			case 94:
				drivingSummary.distH = hex2int(hex);
				break;
			case 96:
				drivingSummary.distL = hex2int(hex);
				break;
			case 98:
				drivingSummary.maxSPD = hex2int(hex);
				break;
			case 100:
				drivingSummary.bstFuel = hex2int(hex);
				break;
			case 102:
				drivingSummary.avgSPD = hex2int(hex);
				break;
			case 104:
				drivingSummary.avgFuel = hex2int(hex);
				break;
			case 106:
				break;
			case 108:
				break;
			case 110:
				drivingSummary.fuelLV = hex2int(hex);
				break;
			case 112:
				drivingSummary.acc = hex2int(hex);
				break;
			case 114:
				drivingSummary.brk = hex2int(hex);
				break;
			case 116:
				drivingSummary.overSPD = hex2int(hex);
				break;
			case 118:
				drivingSummary.idleSPD = hex2int(hex);
				break;
			case 120:
				drivingSummary.sliding = hex2int(hex);
				break;
			case 122:
				drivingSummary.fastRate = hex2int(hex);
				break;
			case 124:
				drivingSummary.slowRate = hex2int(hex);
				break;
			case 126:
				drivingSummary.jamRate = hex2int(hex);
				break;
			}
		}
		return drivingSummary;
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

	public static double format(double in, int n) {
		double p = Math.pow(10, n);
		return Math.round(in * p) / p;
	} // format

	public static void parseDrivingData(String fileName) throws Exception {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			// 一次读入一行，直到读入null为文件结束
			while ((line = reader.readLine()) != null) {
				// 显示行号
				System.out.println(line.length());
				int pieceNum = line.length() / 80 - 1;
				System.out.println(pieceNum);
				for (int i = 0; i < pieceNum; ++i) {
					String piece = line.substring(i * 80, (i + 1) * 80);
					DrivingPiece drivingPiece = parseDrivingPiece(piece);
					System.out.println(drivingPiece);
				}
				String summary = line.substring(pieceNum * 80, line.length());
				DrivingSummary drivingSummary = parseDrivingSummary(summary);
				System.out.println(drivingSummary);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static OBDConfig parseOBDConfig(String data) throws Exception {
		OBDConfig obdConfig = new OBDConfig();
		char[] tmp = new char[data.length() / 2];
		for (int i = 0; i < data.length(); i += 2) {
			tmp[i / 2] = hex2char(data.substring(i, i + 2));
		}
		Object[] obdProtocolArray = new Object[16];
		Object[] obdArray = new Object[10];
		Object[] vinArray = new Object[17];
		Object[] cidArray = new Object[16];
		for (int i = 0; i < tmp.length; ++i) {
			if (i < 16) {
				obdProtocolArray[i] = tmp[i];
			} else if (i < 33) {
				vinArray[i - 16] = tmp[i];
			} else if (i > 33 && i < 42) {
				obdArray[i - 33] = tmp[i];
			} else if (i > 42 && i < 59) { //
				cidArray[i - 43] = tmp[i];
			}
		}
		obdConfig.cid = StringUtils.join(cidArray);
		obdConfig.obdProtocol = StringUtils.join(obdProtocolArray);
		obdConfig.obdSerialId = StringUtils.join(obdArray);
		obdConfig.vin = StringUtils.join(vinArray);
		return obdConfig;
	}

	public static void main(String[] args) throws Exception {
		// obd 4A53435330303031
		// cid 16F815601260120021121AB2225E2188
		// vin 4C475746463341353241423030363634
		String config = "054B57502D666173742D696E697400004C47574646334135324142303036363432204A534353303030312016F815601260120021121AB2225E21880000000000334455660000000000000000030000005E10502F2131000000000000000000000100000000000000000000000000000042363030303230370000000000000000";
		OBDConfig obdConfig = parseOBDConfig(config);
		System.out.println(obdConfig);
		System.out.println(hex2char("00"));

		parseDrivingData("data/1.dat");

		// try {
		// AESZipFileDecrypter zipFile = new AESZipFileDecrypter(new File(
		// "data/1.zip"));
		// ExtZipEntry entry = zipFile.getEntry("1.dat");
		// zipFile.extractEntry(entry, new File("data/1.txt"),
		// "2482196189618033182617834943313600");
		// } catch (Exception e) {
		// System.err.println(e.getMessage());
		// }
		// String piece1 =
		// "01260D05110D220A1F1133554E0114223E604505500D04B04905DC0B85153C19230CFF0000000202";
		// String summary =
		// "023F0D05110D0A0A1F1300004E011425000045050D07110E20001F0E00004E01141D0000450400FFFF7CFFFFFFFF4100788700580A2A1CFF0A1E01030146190500";
		// try {
		// DrivingPiece drivingPiece = parseDrivingPiece(piece1);
		// System.out.println(drivingPiece);
		// DrivingSummary drivingSummary = parseDrivingSummary(summary);
		// System.out.println(drivingSummary);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
