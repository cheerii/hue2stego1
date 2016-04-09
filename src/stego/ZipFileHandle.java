package stego;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import stego.InvalidPNGException;
import stego.StegoImageExtractor;

public class ZipFileHandle {

	// Buffer_SIZE kann somit ueberall im Code verwendet werden
	private static final int BUFFER_SIZE = 2048;

	public void ZipFileHandle() {

	}

	public static void ziphandler2(File instFileZip) throws InvalidZIPException, InvalidPNGException {

		String extractParentPath = instFileZip.getParent(); // Shows the
															// Directory where
															// the file is
															// extracted
		// System.out.println("Extract Path ist: " + extractParentPath);
		File deleter = new File(instFileZip.getParent());

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(instFileZip);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

		ZipInputStream zis = new ZipInputStream(fis);

		ZipEntry entry;

		System.out.println("+++Scanning of your Files has following Results+++++");

		try {
			while ((entry = zis.getNextEntry()) != null) {
				// System.out.println("entry: " + entry.getName() + ", " +
				// entry.getSize());

				// Create full path (path + name of file) - will be used with:
				// hidcontent = StegoImageExtractor.extract(extractinPath);
				String extractinPath = extractParentPath + "\\" + entry.getName();

				// Write every file in Zip file to Disk:
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(extractinPath));

				// The Max Size of image is 904 Kbyte, so the size was increased
				// to 2048 Bytes
				byte[] bytesIn = new byte[BUFFER_SIZE];

				int read = 0;

				// Solange zis (eine eingelesene Datei/Bild mario.png); -1
				// heiﬂt, wenn das Ende
				// vom Stream (also wenn mario.png ganz eingelesen wurde)
				// erreicht ist und er
				// nichts mehr zu schreiben hat.
				while ((read = zis.read(bytesIn)) != -1) {
					bos.write(bytesIn, 0, read);
				}
				bos.close();

				String extractedcontent;
				try {

					// extract need full path as parameter (here extractinPath)
					extractedcontent = StegoImageExtractor.extract(extractinPath);
					System.out.println(extractedcontent + " :: Was found in Picture :: " + entry.getName());
				} catch (InvalidPNGException e) {
					// TODO Auto-generated catch block
				}

			}
		} catch (IOException e) {
			throw new InvalidPNGException("Problems while reading Entry");
//			System.exit(1);
			// e.printStackTrace();
		}

		// Delete Files in that folder that contains ".png" as filetype
		for (File file : deleter.listFiles())
			if (file.getName().endsWith(".png")) {
				file.delete();
			}
	}
}
