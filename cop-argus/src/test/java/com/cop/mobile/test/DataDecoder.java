package com.cop.mobile.test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DataDecoder {
	public static Map<String, Object> parseRouteData(String data) {
		String test = "[1][2][3][4]";
		Pattern p = Pattern.compile("[[.]]+");
		String[] result = p.split(test);
		for (int i = 0; i < result.length; ++i) {
			System.out.println(result[i]);
		}
		return null;
	}

	public static Map<String, Object> parseStartData(String data) {
		try {
			int year = 0;
			int month = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			int second = 0;
			double lat = 0;
			double lng = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yy MM dd hh-mm-ss");
			Map<String, Object> start = new HashMap<String, Object>();
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
				case 0:
					start.put("type", obj);
					break;
				case 2:
					start.put("len", obj);
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
					String date = String.format("%d %d %d %d-%d-%d", year,
							month, day, hour, minute, second);
					long time = sdf.parse(date).getTime();
					start.put("date", sdf.parse(date).toString());
					break;
				case 16:
					lat = (Integer) obj * 100.0;
					break;
				case 18:
					lat = lat + (Integer) obj;
					break;
				case 20:
					lat = lat + (Integer) obj / 100.0;
					break;
				case 22:
					lat = lat + (Integer) obj / 10000.0;
					start.put("lat", lat);
					break;
				case 24:
					start.put("dir1", obj);
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
					start.put("lng", lng);
					break;
				case 34:
					break;
				case 36:
					start.put("dir2", obj);
					break;
				case 38:
					start.put("ele", obj);
					break;
				case 40:
					start.put("vin", obj);
					break;
				case 42:
					start.put("fuelLv", obj);
					break;
				case 44:
					start.put("bat", obj);
					break;
				case 46:
					start.put("dist", obj);
					break;
				case 48:
					start.put("temp", obj);
					break;
				case 50:
					start.put("cTemp", obj);
					break;
				case 52:
					start.put("dcodeNum", obj);
					break;
				}
			}
			return start;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Map<String, Object> parseDrivingData(String data) {
		try {
			int year = 0;
			int month = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			int second = 0;
			double lat = 0;
			double lng = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yy MM dd hh-mm-ss");
			Map<String, Object> driving = new HashMap<String, Object>();
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
				case 0:
					driving.put("type", obj);
					break;
				case 2:
					driving.put("len", obj);
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
					String date = String.format("%d %d %d %d-%d-%d", year,
							month, day, hour, minute, second);
					long time = sdf.parse(date).getTime();
					driving.put("date", sdf.parse(date).toString());
					break;
				case 16:
					lat = (Integer) obj * 100.0;
					break;
				case 18:
					lat = lat + (Integer) obj;
					break;
				case 20:
					lat = lat + (Integer) obj / 100.0;
					break;
				case 22:
					lat = lat + (Integer) obj / 10000.0;
					driving.put("lat", lat);
					break;
				case 24:
					driving.put("dir1", obj);
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
					driving.put("lng", lng);
					break;
				case 34:
					break;
				case 36:
					driving.put("dir2", obj);
					break;
				case 38:
					driving.put("ele", obj);
					break;
				case 40:
					driving.put("dist", obj);
					break;
				case 42:
					driving.put("avgSpeed", obj);
					break;
				case 44:
					driving.put("avgRPMH", obj);
					break;
				case 46:
					driving.put("avgRPML", obj);
					break;
				case 48:
					driving.put("avgFuelH", obj);
					break;
				case 50:
					driving.put("avgFuelL", obj);
					break;
				case 52:
					driving.put("airTemp", obj);
					break;
				case 54:
					driving.put("oilTemp", obj);
					break;
				case 56:
					driving.put("avgWTemp", obj);
					break;
				case 58:
					driving.put("accBreak", obj);
					break;
				case 60:
					driving.put("overSpeed", obj);
					break;
				case 62:
					driving.put("shift", obj);
					break;
				}
			}
			return driving;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Map<String, Object> parseEndData(String data) {
		try {
			int year = 0;
			int month = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			int second = 0;
			double lat = 0;
			double lng = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yy MM dd hh-mm-ss");
			Map<String, Object> end = new HashMap<String, Object>();
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
				case 0:
					end.put("type", obj);
					break;
				case 2:
					end.put("len", obj);
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
					String date = String.format("%d %d %d %d-%d-%d", year,
							month, day, hour, minute, second);
					long time = sdf.parse(date).getTime();
					end.put("date", sdf.parse(date).toString());
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
					end.put("lat", lat);
					break;
				case 24:
					end.put("dir1", obj);
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
					end.put("lng", lng);
					break;
				case 34:
					break;
				case 36:
					end.put("dir2", obj);
					break;
				case 38:
					end.put("ele", obj);
					break;
				case 40:
					end.put("bat", obj);
					break;
				}
			}
			return end;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		String start = "011C0D04101330301F0D38204E01151C0862450F09503063190F03010203";
		String driving = "021E0D04101301301F0D38204E01151C0862450F0A3C1E000A001E2D19030A03";
		String end = "03120D04101330301F0D38204E01151C0862450F";
		Map<String, Object> startData = parseStartData(start);
		Map<String, Object> drivingMap = parseDrivingData(driving);
		Map<String, Object> endData = parseEndData(end);
		System.out.println(startData);
		System.out.println(drivingMap);
		System.out.println(endData);
	}
}
