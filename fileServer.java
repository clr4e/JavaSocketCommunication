// Author:	Chelsea Rath & Terry Payne
// Program:	Network Programming Assignment
// File:	fileServer.java
// Due Date:	12/6/2011
// Description:	This file manages the server side of the socket communication.

import java.net.*;
import java.io.*;

public class fileServer 
{
	private static int	port = 13267;
	private static String 	path = "textFiles/";
	private static String 	file1 = "file1.txt";
	private static String 	file2 = "file2.txt";
	private static String 	file3 = "file3.txt";

	public static void main (String [] args ) throws IOException 
	{
		String filename;

		// create socket
		ServerSocket servsock = new ServerSocket( port );
		while (true) {
			System.out.println("Waiting...");

			Socket sock = servsock.accept();
			System.out.println("Accepted connection : " + sock);

			BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
			PrintWriter p_out = new PrintWriter( sock.getOutputStream(), true );
		
			String temp = in.readLine();	
			filename = path + temp;
			File myFile = new File (filename);

			// sendfile
			byte [] mybytearray  = new byte [(int)myFile.length()];
			FileInputStream fis = new FileInputStream(myFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(mybytearray,0,mybytearray.length);
			OutputStream os = sock.getOutputStream();
			System.out.println("Sending...");
			os.write(mybytearray,0,mybytearray.length);
			os.flush();
			sock.close();
		}
	}
}
