package com.cop.mobi.mycar.util;

import java.text.SimpleDateFormat;

import com.cop.mobi.mycar.entity.RouteBasePiece;

/**
 * 
 * @author chris.liu
 * 
 */
public class DriveRouteDataDecoder {

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
}
