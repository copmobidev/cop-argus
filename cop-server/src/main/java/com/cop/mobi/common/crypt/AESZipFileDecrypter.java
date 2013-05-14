package com.cop.mobi.common.crypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import com.cop.mobi.common.crypt.impl.AESDecrypterBC;
import com.cop.mobi.common.crypt.impl.ExtRandomAccessFile;
import com.cop.mobi.common.crypt.impl.ExtZipEntry;
import com.cop.mobi.common.crypt.impl.ExtZipOutputStream;
import com.cop.mobi.common.crypt.impl.ZipConstants;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class AESZipFileDecrypter implements ZipConstants {
	private static final Logger LOG = Logger
			.getLogger(AESZipFileDecrypter.class.getName());

	/** charset to use for filename(s) and password - defaults to iso-8859-1 */
	protected static String charset = "iso-8859-1";

	/** size of buffer to use for byte[] operations - defaults to 1024 */
	protected static int bufferSize = 1024 * 10;

	/** random access file to access the archive data */
	protected ExtRandomAccessFile raFile;

	/** where does the directory (after file data) start? */
	protected long dirOffsetPos;

	public AESZipFileDecrypter(File zipFile) throws IOException {
		raFile = new ExtRandomAccessFile(zipFile);
		dirOffsetPos = zipFile.length() - 6;
	}

	public void close() throws IOException {
		raFile.close();
	}

	public List<ExtZipEntry> getEntryList() throws IOException, ZipException {
		List<ExtZipEntry> out = new ArrayList<ExtZipEntry>();

		short totalNumberOfEntries = this.getNumberOfEntries();
		int dirOffset = raFile.readInt(this.dirOffsetPos);

		long fileOffset = dirOffset;
		for (int i = 0; i < totalNumberOfEntries; i++) {
			int censig = raFile.readInt(fileOffset);
			if (censig != CENSIG) {
				throw new ZipException("expected CENSIC not found at entry no "
						+ (i + 1) + " in central directory at end of zip file");
			}

			short fileNameLength = raFile.readShort(fileOffset + 28);
			long fileOffsetPos = fileOffset + 28 + 14;
			long fileDataOffset = raFile.readInt(fileOffsetPos);
			int locsig = raFile.readInt(fileDataOffset);
			if (locsig != LOCSIG) {
				throw new ZipException(
						"expected LOCSIC not found at alleged position of data for file no "
								+ (i + 1));
			}

			byte[] fileNameBytes = raFile.readByteArray(fileOffsetPos + 4,
					fileNameLength);
			long nextFileOffset = raFile.getFilePointer();

			String fileName = new String(fileNameBytes, charset);

			short flag = raFile.readShort(fileOffset + 8);
			boolean isEncrypted = (flag & 1) > 0;

			ExtZipEntry zipEntry = new ExtZipEntry(fileName);
			zipEntry.setOffset((int) fileDataOffset);
			zipEntry.setCompressedSize(raFile.readInt(fileOffset + 20));
			zipEntry.setSize(raFile.readInt(fileOffset + 24));
			zipEntry.setTime(System.currentTimeMillis());
			zipEntry.setTime(raFile.readInt(fileOffset + 12));
			zipEntry.setMethod(ZipEntry.DEFLATED);
			if (isEncrypted) {
				zipEntry.initEncryptedEntry();
			} else {
				zipEntry.setPrimaryCompressionMethod(ZipEntry.DEFLATED);
			}

			if (zipEntry.isEncrypted()) {
				nextFileOffset += zipEntry.getExtra().length;
			}

			out.add(zipEntry);
			fileOffset = nextFileOffset;
		}

		return out;
	}

	public ExtZipEntry getEntry(String name) throws IOException, ZipException,
			DataFormatException {
		for (ExtZipEntry zipEntry : getEntryList()) {
			if (name.equals(zipEntry.getName())) {
				return zipEntry;
			}
		}
		return null;
	}

	public void extractEntry(ExtZipEntry zipEntry, File outFile, String password)
			throws IOException, ZipException, DataFormatException {
		if (zipEntry.isEncrypted()) {
			int dataStart = zipEntry.getOffset() + 4 + 26
					+ zipEntry.getName().length() + zipEntry.getExtra().length;
			byte[] salt = raFile.readByteArray(dataStart, 16);
			byte[] pwVerification = raFile.readByteArray(dataStart + 16, 2);

			// encrypter throws ZipException for wrong password
			AESDecrypter decrypter = new AESDecrypterBC(
					password.getBytes(charset), salt, pwVerification);

			// create tmp file to contains the decrypted, but still compressed
			// data
			File tmpFile = new File(outFile.getPath() + "_TMP.zip");
			ExtZipOutputStream zos = new ExtZipOutputStream(tmpFile);
			ExtZipEntry tmpEntry = new ExtZipEntry(zipEntry);
			tmpEntry.setMethod(ZipEntry.DEFLATED);
			tmpEntry.setPrimaryCompressionMethod(ZipEntry.DEFLATED);
			zos.putNextEntry(tmpEntry);

			byte[] buffer = new byte[bufferSize];
			int remaining = (int) zipEntry.getEncryptedDataSize();
			while (remaining > 0) {
				int len = (remaining > buffer.length) ? buffer.length
						: remaining;
				int read = raFile.readByteArray(buffer, len);
				decrypter.decrypt(buffer, read);
				zos.writeBytes(buffer, 0, read);
				remaining -= len;
			}
			zos.finish();

			byte[] storedMac = new byte[10];
			raFile.readByteArray(storedMac, 10);
			byte[] calcMac = decrypter.getFinalAuthentication();
			if (LOG.isLoggable(Level.FINE)) {
				LOG.fine("storedMac=" + Arrays.toString(storedMac));
				LOG.fine("calcMac=" + Arrays.toString(calcMac));
			}

			if (!Arrays.equals(storedMac, calcMac)) {
				throw new ZipException(
						"stored authentication (mac) value does not match calculated one");
			}

			ZipFile zf = new ZipFile(tmpFile);
			ZipEntry ze = zf.entries().nextElement();
			InputStream is = zf.getInputStream(ze);
			FileOutputStream fos = new FileOutputStream(outFile.getPath());
			int read = is.read(buffer);
			while (read > 0) {
				fos.write(buffer, 0, read);
				read = is.read(buffer);
			}
			fos.close();
			is.close();
			zf.close();

			tmpFile.delete();
		} else {
			throw new ZipException("currently only extracts encrypted files");
		}
	}

	/** number of entries in file */
	public short getNumberOfEntries() throws IOException {
		return raFile.readShort(this.dirOffsetPos - 6);
	}

	/** test code + usage example */
	public static void main(String[] args) throws Exception {
		AESZipFileDecrypter zipFile = new AESZipFileDecrypter(new File(
				"doc/zipSpecificationAes.zip"));
		ExtZipEntry entry = zipFile.getEntry("zipSpecification.txt");
		zipFile.extractEntry(entry, new File("doc/zipSpecification.txt"), "foo");
	}
}
