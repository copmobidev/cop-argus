package com.cop.mobile.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 
 * @author chris.liu
 * 
 */
public class CommonUtil {

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 */
	public static void readFileByBytes(String fileName) {
		File file = new File(fileName);
		InputStream in = null;
		try {
			System.out.println("以字节为单位读取文件内容，一次读一个字节：");
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				System.out.write(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			System.out.println("以字节为单位读取文件内容，一次读多个字节：");
			// 一次读多个字节
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			in = new FileInputStream(fileName);
			showAvailableBytes(in);
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((byteread = in.read(tempbytes)) != -1) {
				System.out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 */
	public static void readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				if (((char) tempchar) != '\r') {
					System.out.print((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("以字符为单位读取文件内容，一次读多个字节：");
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(fileName));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉\r不显示
				if ((charread == tempchars.length)
						&& (tempchars[tempchars.length - 1] != '\r')) {
					System.out.print(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							System.out.print(tempchars[i]);
						}
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
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

	/**
	 * 随机读取文件内容
	 */
	public static void readFileByRandomAccess(String fileName) {
		RandomAccessFile randomFile = null;
		try {
			System.out.println("随机读取一段文件内容：");
			// 打开一个随机访问文件流，按只读方式
			randomFile = new RandomAccessFile(fileName, "r");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 读文件的起始位置
			int beginIndex = (fileLength > 4) ? 4 : 0;
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[10];
			int byteread = 0;
			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
			// 将一次读取的字节数赋给byteread
			while ((byteread = randomFile.read(bytes)) != -1) {
				System.out.write(bytes, 0, byteread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 显示输入流中还剩的字节数
	 */
	private static void showAvailableBytes(InputStream in) {
		try {
			System.out.println("当前字节输入流中的字节数为:" + in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A方法追加文件：使用RandomAccessFile
	 */
	public static void appendMethodA(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * B方法追加文件：使用FileWriter
	 */
	public static void appendMethodB(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		/*
		 * String inFile = "data/obd_code.txt"; String outFile = "data/obd.txt";
		 * 
		 * File file = new File(inFile); BufferedReader reader = null;
		 * FileWriter writer = null;
		 * 
		 * try { reader = new BufferedReader(new FileReader(file)); writer = new
		 * FileWriter(outFile, true); String line = null; //
		 * 一次读入一行，直到读入null为文件结束 while ((line = reader.readLine()) != null) { //
		 * 显示行号 try { String code = line.substring(0, 5); String content =
		 * line.substring(6, line.length()); writer.write(code + "\t" + content
		 * + "\n"); writer.flush(); } catch (Exception e) {
		 * System.out.println("error: " + line); } } writer.close();
		 * reader.close(); } catch (IOException e) { e.printStackTrace(); }
		 * finally { if (reader != null) { try { reader.close(); } catch
		 * (IOException e1) { } } }
		 */
		String header = "011C0D04101330301F0D38204E01151C0862450F09503063190F03010203";

		for (int i = 0; i < header.length(); i += 2) {
			if (i == 24 || i == 36) {
				byte[] baKeyword = new byte[1];
				baKeyword[0] = (byte) (0xff & Integer.parseInt(
						header.substring(i, i + 2), 16));
				String direct = new String(baKeyword, "utf-8");
				System.out.print(direct);
			} else {
				char ch1 = header.charAt(i);
				int tmp1 = Character.digit(ch1, 16) * 16;
				char ch2 = header.charAt(i + 1);
				int tmp2 = Character.digit(ch2, 16);
				System.out.print(tmp1 + tmp2);
			}
		}
		Map<String, Object> start = new HashMap<String, Object>();
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
			for (int i = 0; i < header.length(); i += 2) {
				Object obj = null;
				if (i == 24 || i == 36) {
					byte[] baKeyword = new byte[1];
					baKeyword[0] = (byte) (0xff & Integer.parseInt(
							header.substring(i, i + 2), 16));
					obj = new String(baKeyword, "utf-8");
				} else {
					char ch1 = header.charAt(i);
					int tmp1 = Character.digit(ch1, 16) * 16;
					char ch2 = header.charAt(i + 1);
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
					year = (Integer)obj;
					break;
				case 6:
					month = (Integer)obj;
					break;
				case 8:
					day = (Integer)obj;
					break;
				case 10:
					hour = (Integer)obj;
					break;
				case 12:
					minute = (Integer)obj;
					break;
				case 14:
					second = (Integer)obj;
					String date = String.format("%d %d %d %d-%d-%d", year, month, day, hour, minute, second);
					long time = sdf.parse(date).getTime();
					start.put("second", time);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		parseRouteData("");
		System.out.println(start);
	}

	public static Map<String, Object> parseRouteData(String data) {
		String test = "[1][2][3][4]";
		Pattern p = Pattern.compile("[/]]+"); 
		String[] result = p.split(test);
		for(int i=0; i<result.length; ++i) {
			System.out.println(result[i]);
		}
		return null;
	}
	
	public static Map<String, Object> parseStartData(String data) {
		return null;
	}
	
	public static Map<String, Object> parseDrivingData(String data) {
		return null;
	}
	
	public static Map<String, Object> parseEndData(String data) {
		return null;
	}
}
