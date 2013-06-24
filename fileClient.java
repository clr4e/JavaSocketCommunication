// Author:	Chelsea Rath & Terry Payne
// Program:	Network Programming Assignment
// File:	fileClient.java
// Due Date:	12/6/2011
// Description:	This file manages the client side of the socket communication.

import java.net.*;
import java.io.*;

public class fileClient
{
	private static int	port = 13267;

	public static void main (String [] args ) throws IOException 
	{
		int 	filesize = 6022386; // filesize temporary hardcoded
		long 	start = System.currentTimeMillis();
		int	bytesRead;
		int 	current = 0;


		// localhost for testing
		Socket sock = new Socket("127.0.0.1", port);
		System.out.println("Connecting...");
	
		BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
		PrintWriter p_out = new PrintWriter( sock.getOutputStream(), true );
 		
		p_out.println(args[0]);
		String temp = args[0].substring(args[0].length()-3);

		// receive file
		byte [] mybytearray  = new byte [filesize];
		InputStream is = sock.getInputStream();
		FileOutputStream fos = new FileOutputStream("textFiles/copy." + temp);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bytesRead = is.read(mybytearray,0,mybytearray.length);
		current = bytesRead;

		// continue reading until an EOF is read
		do {
			bytesRead =
			  is.read(mybytearray, current, (mybytearray.length-current));
			if(bytesRead >= 0) current += bytesRead;
		} while(bytesRead > -1);
		
		// write to new file
		bos.write(mybytearray, 0 , current);
		bos.flush();

		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println( "Time taken : " + time + " msec" );

		bos.close();
		sock.close();
	}
}
