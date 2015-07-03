package com.autoplayer;

/**
 * Main class responsible to start the PlayerResquestListener socket server that listen on port 8080 to receive
 * the for the URLs of the videos that must be exhibited. Once a URL is received by the server, it is enqueued
 * to be played later by the VideoPlayer class.
 * The VideoPlayer class the instantiated and loops infinitely checking for new enqueued videos to be played.
 * Once a new video URL is enqueued, the VideoPlayer will download it (if not downloaded yet) to the path 
 * defined in MEMORY_CARD_PATH and exhibit it using the video player command line configured in PLAYER_CMD.
 * 
 * @author Rodrigo Vargas
 */
public class AutoPlayer {

	/** Holds the default port used by PlayerRequestListener socket server, responsible
	 *  to receive requests of that must be displayed. */
	private static int DEFAULT_LISTEN_PORT = 8080;
	
	/** Holds the default path where the internal memory card is mounted. 
	 * It is used to save the videos locally prior displaying it. */
	private static String DEFAULT_MEMORY_CARD_PATH = ".";
	
	/** Holds the default command line that executes the video player to display the video.
	 * The command line must contains all switches required to display the movie in full screen,
	 * and make the player exit after the video finishes.
	 * The command line must contain &lt;filename&gt; place holder to indicate where in the command line
	 * the path to the file to be executed must be inserted.
	 */
	private static String DEFAULT_PLAYER_CMD = "vlc -f --play-and-exit <filename>"; //Works for Linux.
	
	//Command lines to test on Windows
	//private static String DEFAULT_PLAYER_CMD = "C:\\Program Files (x86)\\K-Lite Codec Pack\\Media Player Classic\\mpc-hc.exe <filename> /play /close";
	//private static String DEFAULT_PLAYER_CMD = "C:\\Program Files\\VideoLAN\\VLC\\vlc.exe -f --play-and-exit <filename>";
	

	/**
	 * Starts a new thread for PlayerRequestListener, in order to wait for new requests of movies to 
	 * be displayed and starts the VideoPlayer to download and play any video that is enqueued.
	 * 
	 * @param args Input arguments of the program. Any argument is ignored.
	 */
	public static void main(String[] args) throws InterruptedException {
		
		//Set the default values.
		int listenPort = DEFAULT_LISTEN_PORT;
		String memoryCardPath = DEFAULT_MEMORY_CARD_PATH;
		String playerCmd = DEFAULT_PLAYER_CMD;
		
		//Retrieve the program arguments.
		if (args.length>=1) listenPort = Integer.parseInt(args[0]);
		if (args.length>=2) memoryCardPath = args[1];
		if (args.length>=3) playerCmd = args[2];

		/* Start the socket server responsible to receive the requests for video exhibition.
		 * The server will enqueue any movie requested and make it available via
		 * getPlaylist() method. */
		PlayerRequestListener ws = new PlayerRequestListener(listenPort);
		ws.start();

		/* Instantiate and play the VideoPlayer to download and exhibit
		 * the videos in the PlayList queue. 
		 * It will exhibit the videos using the player configured in the player command line 
		 * defined via setPlayerCmd method.*/
		VideoPlayer vp = new VideoPlayer(ws.getPlaylist(), memoryCardPath);
		vp.setPlayerCmd(playerCmd);
		vp.play();
	}
}
