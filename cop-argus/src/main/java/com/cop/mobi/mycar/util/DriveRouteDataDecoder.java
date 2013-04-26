package com.cop.mobi.mycar.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cop.mobi.mycar.entity.DriveRoute;
import com.cop.mobi.mycar.entity.RouteBasePiece;
import com.cop.mobi.mycar.entity.RouteDrivingPiece;
import com.cop.mobi.mycar.entity.RouteEndPiece;
import com.cop.mobi.mycar.entity.RouteStartPiece;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveRouteDataDecoder {

	public static DriveRoute parseRouteData(String data) throws Exception {
		String[] result = data.split("\\|");
		RouteStartPiece startPiece = null;
		List<RouteDrivingPiece> drivingPieces = new ArrayList<RouteDrivingPiece>();
		RouteEndPiece endPiece = null;
		for (int i = 0; i < result.length; ++i) {
			RouteBasePiece basePiece = parseBaseData(result[i]);
			switch (basePiece.getType()) {
			case 1: // start
				startPiece = parseStartData(basePiece, result[i]);
				break;
			case 2: // driving
				drivingPieces.add(parseDrivingData(basePiece, result[i]));
				break;
			case 3: // end
				endPiece = parseEndData(basePiece, result[i]);
				break;
			default:
				throw new Exception("drive route data format error");
			}
		}

		if (startPiece != null && endPiece != null && drivingPieces.size() > 0) {
			DriveRoute dr = new DriveRoute();
			dr.setStartPiece(startPiece);
			dr.setDrivingPieces(drivingPieces);
			dr.setEndPiece(endPiece);
			return dr;
		} else {
			return null;
		}
	}

	public static RouteBasePiece parseBaseData(String data) throws Exception {
		int type = 0;
		int len = 0;
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		long time = 0;
		double lat = 0;
		double lng = 0;
		String dir1 = null;
		String dir2 = null;
		int ele = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yy MM dd hh-mm-ss");
		for (int i = 0; i < 40; i += 2) {
			Object obj = null;
			if (i == 24 || i == 36) {
				byte[] baKeyword = new byte[1];
				baKeyword[0] = (byte) (0xff & Integer.parseInt(
						data.substring(i, i + 2), 16));
				obj = new String(baKeyword, "utf-8");
			} else {
				char ch1 = data.charAt(i);
				int tmp1 = Character.digit(ch1, 16) * 16;
				char ch2 = data.charAt(i + 1);
				int tmp2 = Character.digit(ch2, 16);
				obj = tmp1 + tmp2;
			}

			switch (i) {
			case 0:
				type = (Integer) obj;
				break;
			case 2:
				len = (Integer) obj;
				break;
			case 4:
				year = (Integer) obj;
				break;
			case 6:
				month = (Integer) obj;
				break;
			case 8:
				day = (Integer) obj;
				break;
			case 10:
				hour = (Integer) obj;
				break;
			case 12:
				minute = (Integer) obj;
				break;
			case 14:
				second = (Integer) obj;
				String date = String.format("%d %d %d %d-%d-%d", year, month,
						day, hour, minute, second);
				time = sdf.parse(date).getTime();
				break;
			case 16:
				lat = (Integer) obj;
				break;
			case 18:
				lat = lat + (Integer) obj / 100.0;
				break;
			case 20:
				lat = lat + (Integer) obj / 10000.0;
				break;
			case 22:
				lat = lat + (Integer) obj / 1000000.0;
				break;
			case 24:
				dir1 = obj.toString();
				break;
			case 26:
				lng = (Integer) obj * 100.0;
				break;
			case 28:
				lng += (Integer) obj;
				break;
			case 30:
				lng += (Integer) obj / 100.0;
				break;
			case 32:
				lng += (Integer) obj / 10000.0;
				break;
			case 34:
				break;
			case 36:
				dir2 = obj.toString();
				break;
			case 38:
				ele = (Integer) obj;
				break;
			}
		}
		RouteBasePiece basePiece = new RouteBasePiece(type, time, lat, lng,
				dir1, dir2, ele);
		return basePiece;
	}

	public static RouteStartPiece parseStartData(RouteBasePiece basePiece,
			String data) throws Exception {
		String vin = null;
		int fuel = 0;
		int bat = 0;
		int dist = 0;
		int temp = 0;
		int cTemp = 0;
		int decodeNum = 0;
		for (int i = 0; i < data.length(); i += 2) {
			Object obj = null;
			if (i == 24 || i == 36) {
				byte[] baKeyword = new byte[1];
				baKeyword[0] = (byte) (0xff & Integer.parseInt(
						data.substring(i, i + 2), 16));
				obj = new String(baKeyword, "utf-8");
			} else {
				char ch1 = data.charAt(i);
				int tmp1 = Character.digit(ch1, 16) * 16;
				char ch2 = data.charAt(i + 1);
				int tmp2 = Character.digit(ch2, 16);
				obj = tmp1 + tmp2;
			}

			switch (i) {
			case 40:
				vin = obj.toString();
				break;
			case 42:
				fuel = (Integer) obj;
				break;
			case 44:
				bat = (Integer) obj;
				break;
			case 46:
				dist = (Integer) obj;
				break;
			case 48:
				temp = (Integer) obj;
				break;
			case 50:
				cTemp = (Integer) obj;
				break;
			case 52:
				decodeNum = (Integer) obj;
				break;
			}
		}
		RouteStartPiece startPiece = new RouteStartPiece(basePiece, vin, fuel,
				bat, dist, temp, cTemp, decodeNum);
		return startPiece;
	}

	public static RouteDrivingPiece parseDrivingData(RouteBasePiece basePiece,
			String data) throws Exception {
		int dist = 0;
		int avgSpeed = 0;
		int avgRPMH = 0;
		int avgRPML = 0;
		int avgFuelH = 0;
		int avgFuelL = 0;
		int airTemp = 0;
		int oilTemp = 0;
		int avgWTemp = 0;
		int accBrk = 0;
		int overSpeed = 0;
		int shift = 0;
		for (int i = 40; i < data.length(); i += 2) {
			Object obj = null;
			if (i == 24 || i == 36) {
				byte[] baKeyword = new byte[1];
				baKeyword[0] = (byte) (0xff & Integer.parseInt(
						data.substring(i, i + 2), 16));
				obj = new String(baKeyword, "utf-8");
			} else {
				char ch1 = data.charAt(i);
				int tmp1 = Character.digit(ch1, 16) * 16;
				char ch2 = data.charAt(i + 1);
				int tmp2 = Character.digit(ch2, 16);
				obj = tmp1 + tmp2;
			}

			switch (i) {
			case 40:
				dist = (Integer) obj;
				break;
			case 42:
				avgSpeed = (Integer) obj;
				break;
			case 44:
				avgRPMH = (Integer) obj;
				break;
			case 46:
				avgRPML = (Integer) obj;
				break;
			case 48:
				avgFuelH = (Integer) obj;
				break;
			case 50:
				avgFuelL = (Integer) obj;
				break;
			case 52:
				airTemp = (Integer) obj;
				break;
			case 54:
				oilTemp = (Integer) obj;
				break;
			case 56:
				avgWTemp = (Integer) obj;
				break;
			case 58:
				accBrk = (Integer) obj;
				break;
			case 60:
				overSpeed = (Integer) obj;
				break;
			case 62:
				shift = (Integer) obj;
				break;
			}
		}

		RouteDrivingPiece drivingPiece = new RouteDrivingPiece(basePiece, dist,
				avgSpeed, avgRPMH, avgRPML, avgFuelH, avgFuelL, airTemp,
				oilTemp, avgWTemp, accBrk, overSpeed, shift);
		return drivingPiece;
	}

	public static RouteEndPiece parseEndData(RouteBasePiece basePiece,
			String data) throws Exception {
		int bat = 0;
		for (int i = 40; i < data.length(); i += 2) {
			Object obj = null;
			if (i == 24 || i == 36) {
				byte[] baKeyword = new byte[1];
				baKeyword[0] = (byte) (0xff & Integer.parseInt(
						data.substring(i, i + 2), 16));
				obj = new String(baKeyword, "utf-8");
			} else {
				char ch1 = data.charAt(i);
				int tmp1 = Character.digit(ch1, 16) * 16;
				char ch2 = data.charAt(i + 1);
				int tmp2 = Character.digit(ch2, 16);
				obj = tmp1 + tmp2;
			}

			switch (i) {
			case 38:
				bat = (Integer) obj;
				break;
			}
		}
		RouteEndPiece endPiece = new RouteEndPiece(basePiece, bat);
		return endPiece;
	}

	public static void main(String[] args) throws Exception {
		String start = "011C0D04101330301F0D38204E01151C0862450F09503063190F03010203";
		String driving = "021E0D04101301301F0D38204E01151C0862450F0A3C1E000A001E2D19030A03";
		String end = "03120D04101330301F0D38204E01151C0862450F";
		RouteBasePiece basePiece = parseBaseData(start);
		RouteStartPiece startData = parseStartData(basePiece, start);
		basePiece = parseBaseData(end);
		RouteDrivingPiece drivingMap = parseDrivingData(basePiece, driving);
		basePiece = parseBaseData(end);
		RouteEndPiece endData = parseEndData(basePiece, end);
		System.out.println(startData);
		System.out.println(drivingMap);
		System.out.println(endData);
	}
}
