package chlod.randoms.ConsoleTweet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configuration {
	
	File config = new File("ct-config.txt");
	
	public Boolean configExists() {
		if (config.exists()) {
			return true;
		} else {
			return true;
		}
	}
	
	public File getFile() {
		return config;
	}
	
	public Map<String, String> readConfig(File config) {
		Map<String, String> m = new HashMap<String, String>();
		try {
			Scanner s = new Scanner(config);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if ((line.length() < 2 ? "-" : (line.length() == 2 ? line : line.substring(0, 2))) != "//") {
					Pattern pattern = Pattern.compile(": *");
					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
					    String setting = line.substring(0, matcher.start());
					    ConsoleTweet.logAppend(ConsoleTweet.getTime() + "Read " + setting);
					    String value = line.substring(matcher.end());
					    ConsoleTweet.logAppend(ConsoleTweet.getTime() + setting + " set to " + (line.length() < 2 ? value : value.substring(0,2)) + "***...");
					    if (setting != null && value != null) {
							m.put(setting, value);
						} else {
							ConsoleTweet.logAppend(ConsoleTweet.getTime() + "Null value located...");
						}
					}
				} else {
					ConsoleTweet.logAppend(ConsoleTweet.getTime() + "Syntax error detected. Ignoring...");
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			return null;
		}
		return m;
	}
	
	public Boolean deleteConfig(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e) {
		if (!a || !b || !c || !d || !e) {
			ConsoleTweet.logAppend(ConsoleTweet.getTime() + "You have fully confirmed Configuration deletion");
			return false;
		} else {
			if (config.delete()) {
				ConsoleTweet.logAppend(ConsoleTweet.getTime() + "Configuration deletion successful");
				return true;
			} else {
				ConsoleTweet.logAppend(ConsoleTweet.getTime() + "Failure in configuration deletion.");
				return false;
			}
		}
	}
	
	public Boolean createConfig(File config, String at, String ats) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(config, true));
			bw.write("ACCESS-TOKEN: " + at);
			bw.newLine();
			bw.write("ACCESS-TOKEN-SECRET: " + ats);
			ConsoleTweet.logAppend(ConsoleTweet.getTime() + "Writing to configuration:");
			ConsoleTweet.logAppend(ConsoleTweet.getTime() + "Access Token: " + at.substring(0,2) + "***...");
			ConsoleTweet.logAppend(ConsoleTweet.getTime() + "Access Token Secret: " + ats.substring(0,2) + "***...");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.out.println("Log initialization failed.");
			e.printStackTrace();
		}
		return false;
	}
	
}
