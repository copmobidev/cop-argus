package com.cop.mobi.mycar.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import com.cop.mobi.mycar.entity.DrivingPiece;
import com.cop.mobi.mycar.entity.DrivingSummary;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveDataParser {

	public static DrivingPiece parseDrivingPiece(String piece) throws Exception {
		DrivingPiece drivingPiece = new DrivingPiece();
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yy MM dd hh-mm-ss");
		for (int i = 0; i < 80; i += 2) {
			String hex = piece.substring(i, i + 2);
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

	public static DrivingSummary parseDrivingSummary(String summary)
			throws Exception {
		DrivingSummary drivingSummary = new DrivingSummary();
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yy MM dd hh-mm-ss");
		for (int i = 0; i < summary.length(); i += 2) {
			String hex = summary.substring(i, i + 2);
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

	public static void main(String[] args) {
		String piece = "01260D05110D0A0A1F1300004E01142500004505500D04B04905DC0B85153C19230CFF0103000202";
		String piece1 = "01260D05110D220A1F1133554E0114223E604505500D04B04905DC0B85153C19230CFF0000000202";
		String summary = "023F0D05110D0A0A1F1300004E011425000045050D07110E20001F0E00004E01141D0000450400FFFF7CFFFFFFFF4100788700580A2A1CFF0A1E01030146190500";
		try {
			DrivingPiece drivingPiece = parseDrivingPiece(piece1);
			System.out.println(drivingPiece);
			DrivingSummary drivingSummary = parseDrivingSummary(summary);
			System.out.println(drivingSummary);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
