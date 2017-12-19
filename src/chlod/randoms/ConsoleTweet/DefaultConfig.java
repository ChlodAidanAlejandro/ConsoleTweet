package chlod.randoms.ConsoleTweet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultConfig {

	public List<String> deflines = new ArrayList<String>();
	
	public DefaultConfig() {
		deflines.add("// -------------------SYNTAX INTRODUCTION--------------------");
		deflines.add("// This is the default configuration of ConsoleTweeter");
		deflines.add("// ");
		deflines.add("// All lines starting with \"//\" are considered comments.");
		deflines.add("// The system is case sensitive, and the syntax is as follows:");
		deflines.add("// ");
		deflines.add("// setting: value");
		deflines.add("// ");
		deflines.add("// Whereas 'setting' is the key and 'value' is its content");
		deflines.add("// 'ACCESS-KEY: ABC1234' will be read as 'ACCESS-KEY' being the");
		deflines.add("// key and 'ABC1234' being the value of the key");
		deflines.add("// ");
		deflines.add("// The system is bound to break. Please watch out for correct");
		deflines.add("// syntax");
		deflines.add("// ");
		deflines.add("// ------------------SETTING UP THE SYSTEM-------------------");
		deflines.add("// METHOD 1:");
		deflines.add("// Run the program and set up through PIN identification.");
		deflines.add("// METHOD 2:");
		deflines.add("// 1. Go to apps.twitter.com (make sure to have confirmed");
		deflines.add("// your mobile phone number) and create a new application.");
		deflines.add("// 2. Set the name to your liking and set the description");
		deflines.add("// to your liking. It may also be the preset description");
		deflines.add("// below:");
		deflines.add("// ");
		deflines.add("// Handle tweets created by ConsoleTweet, a Java program that");
		deflines.add("// works in Bash, Terminal or the Windows Command Prompt");
		deflines.add("// ");
		deflines.add("// Set the website to a placeholder site (http://example.com)");
		deflines.add("// and finally, agree to the Developer Terms and Conditions");
		deflines.add("// 3. Go to the 'Keys and Access Tokens' tab and copy your");
		deflines.add("// Consumer Key (API Key) into the CONSUMER-KEY slot below.");
		deflines.add("// Then, copy your Consumer Secret (API Secret) into the");
		deflines.add("// CONSUMER-SECRET slot below. Then, press the 'Create my");
		deflines.add("// access token' button.");
		deflines.add("// 4. Copy your Access Token (the whole number-string");
		deflines.add("// combination) into the ACCESS-TOKEN slot below. Then,");
		deflines.add("// copy your Access Token Secret into the ACCESS-TOKEN-");
		deflines.add("// SECRET slot below.");
		deflines.add("// 5. Run the program once again to confirm if your");
		deflines.add("// configuration entries were correct.");
		deflines.add("// ");
		deflines.add("// !WARNING!: Make sure you inserted a space after the");
		deflines.add("// colon to make sure syntax is correct, like so:");
		deflines.add("// ");
		deflines.add("// ACCESS-TOKEN: 1928366412-AmsIOsdajNWkqAmdziSAhwwNqqas");
		deflines.add("// ");
		deflines.add("// Disclaimer: Access token above is not a real access");
		deflines.add("// token.");
		deflines.add("// ");
		deflines.add("// !WARNING!: DO NOT SHOW THIS FILE TO ANYONE AT ALL COSTS");
		deflines.add("// THIS MAY GIVE THEM FULL ACCESS TO YOUR TWITTER ACC.");
		deflines.add("// Chlod Alejandro IS NOT VIABLE FOR ANY DAMAGE DONE TO");
		deflines.add("// YOUR ACCOUNT. ConsoleTweeter ALSO DOES NOT COLLECT");
		deflines.add("// YOUR INFORMATION AND STORE IT IN A DATABASE. THE ONLY");
		deflines.add("// COPIES OF YOUR KEY, TOKEN AND SECRETS ARE IN THIS FILE");
		deflines.add("// AND ON TWITTER'S DATABASES.");
		deflines.add("// ");
		deflines.add("// Insert your values below:");
		deflines.add("");
		deflines.add("CONSUMER-KEY: ");
		deflines.add("CONSUMER-SECRET: ");
		deflines.add("ACCESS-TOKEN: ");
		deflines.add("ACCESS-TOKEN-SECRET: ");
	}
	
	public List<String> getDefaultConfigLines() {
		return deflines;
	}
	
	public List<String> getDefaultConfigCommentLines() {
		List<String> comments = new ArrayList<String>();
		for(String i : deflines) {
			if ((i.length() < 2 ? "NOTASTRING" : (i.length() == 2 ? i : i.substring(0, 2))) == "//") {
				comments.add(i);
			}
		}
		return comments;
	}
	
	public List<String> getDefaultConfigSettingLines() {
		List<String> settings = new ArrayList<String>();
		for(String i : deflines) {
			if (i.substring(0, 2) != "//" && i.length() != 0) {
				settings.add(i);
			}
		}
		return settings;
	}
	
	public Map<String, String> getDefaultConfigSetting() {
		Map<String, String> m = new HashMap<String, String>();
		for(String i: deflines) {
			if (i.substring(0, 2) != "//" && i.length() != 0 && i.split(": ", 1).length == 2) {
				String setting = i.split(": ", 1)[0];
				String value = i.split(": ", 1)[1];
				m.put(setting, value);
			}
		}
		return m;
	}
	
}
