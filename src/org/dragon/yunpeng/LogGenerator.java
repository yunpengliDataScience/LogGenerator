package org.dragon.yunpeng;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogGenerator {
	private static final String[] USER_NAMES = { "John", "Jane", "Bob" }; // replace with actual user names
	private static final String[] IP_ADDRESSES = { "10.3.4.1", "10.3.4.2", "10.3.4.3" }; // replace with actual IP
																							// addresses

	public static void main(String[] args) throws Exception {
		Logger logger = Logger.getLogger("MyLog");

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Random random = new Random();

		// create a map of user name and IP address pairs
		Map<String, String> userIPMap = new HashMap<>();
		for (int i = 0; i < USER_NAMES.length; i++) {
			userIPMap.put(USER_NAMES[i], IP_ADDRESSES[i]);
		}

		// log in and out activities indefinitely
		while (true) {

			FileHandler fh = setupLogger(logger);

			try {
				// select a random user name
				String userName = USER_NAMES[random.nextInt(USER_NAMES.length)];

				// get the IP address for the user
				String ipAddress = userIPMap.get(userName);

				// log in activity
				String logInMessage = String.format("%s %s user=%s action=\"logged in\"", sdf.format(new Date()),
						ipAddress, userName);
				logger.log(Level.INFO, logInMessage);

				// flush the log records to the file
				fh.flush();

				Thread.sleep(1000); // wait 1 second

				// log out activity
				String logOutMessage = String.format("%s %s user=%s action=\"logged out\"", sdf.format(new Date()),
						ipAddress, userName);
				logger.log(Level.INFO, logOutMessage);

				// flush the log records to the file
				fh.flush();

				Thread.sleep(1000); // wait 1 seconds
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Exception in logging activity", e);
			} finally {
				fh.close();
			}
		}
	}

	private static FileHandler setupLogger(Logger logger) throws IOException {
		FileHandler fh = new FileHandler("C:\\Projects\\LogGenerator\\activity.log", true);

		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
		logger.addHandler(fh);
		return fh;
	}
}
