package chlod.randoms.ConsoleTweet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * ConsoleTweet gives any Java command line prompt the ability to
 * automatically tweet without any other permissions.
 * 
 * ConsoleTweet and ConsoleTweeter are the same thing
 * 
 * @author Chlod Aidan Alejandro
 * @version 0.1
 */
public class ConsoleTweet {

	/**
	 * TODO Emoji support TODO Better time date functioning system TODO V1
	 */

	public static final String VERSION = "v0.4";
	public static final int VERSION_CODE = 4;
	static Configuration c = new Configuration();
	private static Twitter t;
	
	private static String ACCESS_TOKEN = null;
	private static String ACCESS_TOKEN_SECRET = null;

	public static void main(String[] args) {
		// verification
		Boolean proceed = true;
		// run statement
		logAppend("RUN " + getTime());
		// logger initialization
		logAppend(getTime() + "Logger Initialized.");
		// initialize Twitter module
		Boolean authOK = false;
		while (!authOK) {
			logAppend(getTime() + "Reading configuration...");
			Map<String, String> cf = c.readConfig(c.getFile());
			if ((cf) == null) {
				logAppend(getTime() + "Config does not exist. Running first setup.");
				try {
					firstSetup();
				} catch (TwitterException e) {
					e.printStackTrace();
				}
			} else {
				logAppend(getTime() + "Config found. Verifying values...");
				if ((cf.get("ACCESS-TOKEN")) == null || (cf.get("ACCESS-TOKEN-SECRET")) == null) {
					logAppend(getTime() + "Your config file is broken. Please delete 'cat-config.txt'.");
					logAppend(getTime() + "Afterwards, rerun the program to redo setup.");
					proceed = false;
					authOK = true;
					logAppend("END " + getTime());
					System.exit(1);
				} else {
					logAppend(getTime() + "Loading configuration values...");
					ACCESS_TOKEN = cf.get("ACCESS-TOKEN");
					ACCESS_TOKEN_SECRET = cf.get("ACCESS-TOKEN-SECRET");
					logAppend(getTime() + "Configuration values loaded.");
					authOK = true;
					break;
				}
			}
		}
		logAppend(getTime() + "Initializing twitter module...");
		Twitter t = initTwitter(ACCESS_TOKEN,ACCESS_TOKEN_SECRET);
		logAppend(getTime() + "Twitter module initialized");
		logAppend(getTime() + "Confirming settings...");
		try {
			t.verifyCredentials();
		} catch (TwitterException e) {
			if (e.getStatusCode() == 401) {
				logAppend(getTime() + "Supplied credentials incorrect. Please retry.");
				c.deleteConfig(true, true, true, true, true);
			} else {
				logAppend(getTime() + "Error reaching Twitter.");
			}
			proceed = false;
		} catch (IllegalStateException e) {
			logAppend(getTime() + "Credentials are missing? This is a program-side problem.");
			logAppend(getTime() + "Report to the developer for fixing.");
			proceed = false;
		}
		// set buildup strings
		String a = "";
		String b = "";
		// piece tweet together
		logAppend(getTime() + "Starting tweet piecing...");
		for (int i = 1; i <= args.length; i++) {
			a = a + TweetStringHandler.handleTweetString(args[i - 1]) + " ";
			b = b + args[i - 1] + " ";
		}
		logAppend(getTime() + "Tweet pieced together. Locating errors...");
		// locate errors
		// twitter exception from syntax tag
		if (a.contains("--ERR:twe:break:now:hndlfl--")) {
			logAppend(getTime() + "An error occured during tweet string handling.");
			logAppend(getTime() + "A Twitter Excpetion occured on a Twitter-related syntax tag");
			proceed = false;
		} else {
			// successful piecing
			logAppend(getTime() + "Tweet piecing successful.");
			logAppend(getTime() + "Raw input: " + b);
			logAppend(getTime() + "Syntaxed input: " + a);
		}
		// if tweet size is 0
		logAppend(getTime() + "All error checks complete. Preparing tweet...");
		if (a.length() == 0) {
			logAppend(getTime() + "No arguments input. No tweet output.");
			proceed = false;
		}
		// if tweet exceeds T4J limit
		if (a.length() > 140) {
			logAppend(getTime() + "Tweet must not exceed 140 characters (tweet has " + a.length() + ")");
			proceed = false;
		}
		// check if proceed
		if (proceed) {
			logAppend(getTime() + "Tweet is a go. Sending tweet...");
			try {
				// update status and put result into local variable
				Status s = t.updateStatus(a);
				// confirm result
				logAppend(getTime() + "Tweeted \"" + s.getText() + "\" sucessfully at " + s.getCreatedAt());
				logAppend(getTime() + "ID: " + s.getId());
			} catch (TwitterException e) {
				// catch duplicates, errors, etc.
				logAppend(getTime() + "Tweeting failed");
				e.printStackTrace();
			}
		} else {
			logAppend(getTime() + "Tweet is a no-go. Exiting program...");
		}
		// program end
		logAppend("END " + getTime());
	}

	public static Twitter initTwitter(String at, String ats) {
		TwitterFactory factory = new TwitterFactory();
	    AccessToken accessToken = new AccessToken(at, ats);
	    Twitter twitter = factory.getInstance();
	    twitter.setOAuthConsumer("75lLCQmC639pNqo7AkL5qZMOV", "T7rLB21a8OTFIaY2amKe20fkIxW2v1510KP2kBpym8Uu99wyqO");
	    twitter.setOAuthAccessToken(accessToken);
		return twitter;
	}
	
	public static void firstSetup() throws TwitterException  {
	    t = TwitterFactory.getSingleton();
	    t.setOAuthConsumer("75lLCQmC639pNqo7AkL5qZMOV", "T7rLB21a8OTFIaY2amKe20fkIxW2v1510KP2kBpym8Uu99wyqO");
	    RequestToken requestToken = t.getOAuthRequestToken();
	    AccessToken accessToken = null;
	    Scanner s = new Scanner(System.in);
	    while (null == accessToken) {
	    	logAppend(getTime() + "-----------------First Setup--------------");
			logAppend(getTime() + "Please visit the link below to give access");
			logAppend(getTime() + "to your Twitter account.");
			logAppend(getTime() + "");
			logAppend(getTime() + requestToken.getAuthorizationURL());
			logAppend(getTime() + "");
			logAppend(getTime() + "Please input the PIN indicated below:");
			String pin = s.nextLine();
			logAppend(getTime() + "Processing...");
			s.close();
	      try{
	         if(pin.length() > 0){
	           accessToken = t.getOAuthAccessToken(requestToken, pin);
	         }else{
	           accessToken = t.getOAuthAccessToken();
	         }
	      } catch (TwitterException te) {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	    }
		c.createConfig(c.getFile(), accessToken.getToken(), accessToken.getTokenSecret());
	}
	
	public static void logAppend(String s) {
		// start writer
		BufferedWriter bw = null;
		try {
			// create log
			bw = new BufferedWriter(new FileWriter("ct-log.txt", true));
		} catch (IOException e) {
			// fail log init
			System.out.println("Log initialization failed.");
			e.printStackTrace();
		}
		// try writing log
		try {
			bw.write(s);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			// fail writing log
			ioe.printStackTrace();
		} finally {
			// close writer
			if (bw != null)
				try {
					// close writer
					bw.close();
				} catch (IOException ioe2) {
					// writer close failed
				}
		}
		// output to bash/prompt/terminal
		System.out.println(s);
	}

	public static String getTime() {
		// create format
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// get date in unix timestamp
		Date date = new Date();
		// format date in log mode
		return "[" + dateFormat.format(date) + "] ";
	}

	public static Boolean last(int a, int b) {
		// check if a equal to b
		if (a == b) {
			return true;
		} else {
			return false;
		}
	}

	public static Twitter getTwitter() {
		// return twitter of CAT
		return t;
	}

}
