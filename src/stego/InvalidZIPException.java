//commend test from mee
package stego;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import stego.*;

public class InvalidZIPException extends Exception {

	public InvalidZIPException(){
		
		System.out.println("ERROR - in Exception");
	}
	
	public InvalidZIPException(String errmsgs){
		
		System.out.println("ERROR - in Exception");
		
	}
}
