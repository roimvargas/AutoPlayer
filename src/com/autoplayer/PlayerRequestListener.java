package com.autoplayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class responsible to listen for requests sent via socket on port 8080.
 * An single socket connection may send several movie URLs. Each line of the request must contain one 
 * single URL including its file name.
 * Each video URL is enqueued and the entire queue available via getPlaylist() method.
 * 
 * @author Rodrigo Vargas
 */
public class PlayerRequestListener extends Thread {

	/** Holds the port to which the socket server listen to */
	private int listenPort;
	
	/** Holds the queue containing URLs sent in the requests by the clients */
	private Queue<String> queue = new LinkedList<String>();

	/**
	 * Constructor that receives the port to which the socket server listen to.
	 * @param listenPort The port of the socket server.
	 */
	public PlayerRequestListener(int listenPort) {
		this.listenPort = listenPort;
	}

	public void run() {
		try {

			// Create the ServerSocket to listen on the listenPort.
			ServerSocket ss = new ServerSocket(listenPort);

			// Start a infinity loop to wait for connections.
			while (true) {

				// Wait for a client to connection.
				Socket client = ss.accept();

				// Get the input sent by the client.
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

				/* Read as many line as the client wants to send.
				 * Each line of the request is a URL for a video file that must
				 * be enqueued.
				 * The client may send multiple URLs per request. Each URL will
				 * be enqueued for later exhibition of the video file.
				 * An empty line indicates the end of the list.	 */
				String line;
				while ((line = in.readLine()) != null) {

					// Stop reading the request when a empty line is found.
					if (line.length() == 0)
						break;

					// Enqueue the movie URL for later exhibition.
					queue.add(line); 
					
					// Log the URL received in the request.
					System.out.println("Request received for video: " + line);
				}

				// Prepare a acknowledge answer for the client.
				PrintWriter out = new PrintWriter(client.getOutputStream());
				out.print("OK\r\n\r\n");

				// Break the connection with client by closing the input and output streams.
				out.close(); // Flush and close the output stream.
				in.close(); // Close the input stream.
				client.close(); // Close the socket.
				
			}
		}
		catch (Exception e) {
			// If any exception occurs print the error message.
			System.err.println(e);
		}
	}

	/**
	 * Getter method that make available the queue containing the URLs sent by clients.
	 * @return The queue of URLs of the playlist.
	 */
	public Queue<String> getPlaylist() {
		return queue;
	}
}
