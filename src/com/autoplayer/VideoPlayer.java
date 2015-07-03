package com.autoplayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Queue;

/**
 * Class responsible for monitoring the playlist queue. 
 * Whenever a new URL is added to the playlist, the VideoPlayer will download the video
 * (if not downloaded yet) and execute it using the configured player command line.
 * 
 * @author Rodrigo Vargas
 */
public class VideoPlayer {

	/** Holds the playlist containing URLs of the videos that must be displayed. */
	private Queue<String> playlist;
	
	/** Holds the path to where the videos will be downloaded before being displayed. */
	private String localCachePath;
	
	/** Holds the default command line that executes the video player to display the video.	 */
	private String playerCmd = "vlc  -f --play-and-exit <filename>";

	/**
	 * Constructor that receives the queue playlist and the path for the local cache.
	 * @param playlist Queue of URLs of videos that must be executed.
	 * @param localCachePath The path of where the videos are saved locally before being displayed.
	 */
	public VideoPlayer(Queue<String> playlist, String localCachePath) {
		this.playlist = playlist;
		this.localCachePath = localCachePath;
	}

	/**
	 * Main method of VideoPlayer class responsible to monitor the playlist queue for
	 * URLs of new videos that must be displayed.
	 * @throws InterruptedException 
	 */
	public void play() throws InterruptedException {

		while (true) {
			if (playlist.isEmpty()) {
				Thread.sleep(2000); // Sleep 2 seconds.
			} else {
				
				//Retrieve and remove the first URL from the playlist queue.
				String urlStr = playlist.poll();
				
				URL url;
				try {
					//Verifies if the URL is valid, otherwise thow a exception.
					url = new URL(urlStr);
				} catch (MalformedURLException e) {
					
					//If the URL is invalid, log an error and skip this URL.
					System.out.println("Invalid URL. Skip this video in the playlist.");
					e.printStackTrace();
					continue; //Skip the current URL.
				}

				// Extract the filename from the URL.
				String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1,	urlStr.length());

				// Define the full path to where the video file must be saved locally.
				String localVideoPath = localCachePath + File.separator	+ fileName;
				
				File destPathFile = new File(localVideoPath);
				
				// Download the file only if it doesn't already exists locally.
				if (!destPathFile.exists()) { 
					//Log the full path to where the video file will be saved.
					System.out.println("Downloading video to: " + localVideoPath);
					
					try {
						//Create a readable channel to download the movie.
						ReadableByteChannel rbc = Channels.newChannel(url.openStream());
						
						//Create a stream to the file that will be created locally.
						FileOutputStream fos = new FileOutputStream(localVideoPath);
						
						//Transfer the data from the channel (URL) to the local file.
						fos.getChannel().transferFrom(rbc, 0, Integer.MAX_VALUE);
					} catch (Exception e) {
						//If any exception occur while downloading the file, log the error and skip this URL.
						System.out.println("Error while downloading video from URL: " + url.toString());
						e.printStackTrace();
						continue; //Skip the current URL.
					}
				}

				// Add the saved file the player command line.
				String command = playerCmd.replace("<filename>", localVideoPath);
				
				// Log that the movie file is being executed.
				System.out.println("Executing player: " + command);

				// Execute the configured player in full screen with the downloaded file.
				try {
					Process p = Runtime.getRuntime().exec(command); //Execute the player.
					p.waitFor(); //Wait for the player to exit before loading the next video.
					
				} catch (IOException e) {
					
					//If a exception occur executing the player, log the error and skip this URL.
					System.out.println("Error executing video: " + localVideoPath);
					e.printStackTrace(); 
					continue; //Skip the current URL.
				}
			}
		}

	}

	/**
	 * Setter method that receives the configuration of the player command line.
	 * @param playerCmd The command line that executes the video player to display the video.
	 * The command line must contains all switches required to display the movie in full screen,
	 * and make the player exit after the video finishes.
	 * The command line must contain &lt;filename&gt; place holder to indicate where in the command line
	 * the path to the file to be executed must be inserted.
	 * It's default value will execute the VLC video player if present in the 
	 * environment (vlc  -f --play-and-exit &lt;filename&gt;)
	 */
	public void setPlayerCmd(String playerCmd) {
		this.playerCmd = playerCmd;
	}
}
