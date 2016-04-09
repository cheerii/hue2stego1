package stego;

import stego.InvalidPNGException;
import stego.StegoImageExtractor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class StegoSearcher {

	public static void main(String[] args) throws InvalidZIPException, InvalidPNGException{
		// TODO Auto-generated method stub

		if (args.length != 1) {
			System.out.println("No Arguments were entered");
			System.exit(1);
		}

		File instFileZip = new File(args[0]);

		if (!(instFileZip.getName().contains(".zip"))) {
			System.out.println("No valid File detected!!");
			System.exit(1);
		}

		if (!(instFileZip.exists())) {
			System.err.println("File not Found");
			System.exit(1);
		}

		System.out.println("Ihr File: " + instFileZip.getName());
		System.out.println("AbsoluteFile:" + instFileZip.getAbsoluteFile());
		System.out.println("--------------------------------------------------------------");

		ZipFileHandle instZipFileHandle = new ZipFileHandle();
		
		ZipFileHandle.ziphandler2(instFileZip);
	}
} // END Programm
