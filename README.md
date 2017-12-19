# ConsoleTweet/ConsoleTweeter
May also be known as Chlod's Auto Tweeter.

## Introduction
CT (ConsoleTweet) is a Java-based program that integrates with
Twitter to send tweets using your console. It requires Java.

ConsoleTweet can be used in CRON tasks to create simple tweets
at timed intervals.

Console Tweet uses [yusuke](https://github.com/yusuke)'s [Twitter4J](https://github.com/yusuke/Twitter4J) library
in order to send requests to the Twitter API

## Usage
ConsoleTweets works by taking fed arguments and turning it into
a tweet, like so:

```
java -jar cat-latest.jar "Testing Tweet"
```

or

```
java -jar cat-latest.jar Testing Tweet
```

Both will produce the same output, as the program automatically
builds the arguments together.

ConsoleTweet also contains a syntax that automatically replaces
reserved words to add more functionality and automation to your
automatic tweets

## Syntax
NOTE: Objects in (parentheses) are not part of the output
### Time
Syntax | Function | Example
--- | --- | ---
--datetime-- | month/day/year hour:minute meridian | 11/11/2011 11:11 PM
--date-- | month/day/year | 11/11/2011
--time-- | hour:minute meridian | 11:11 PM
--24datetime-- | month/day/year 24hour:minute | 11/11/2011 23:11 
--24time-- | 24hour:minute | 23:11
--24datetimes-- | month/day/year 24hour:minute:second | 11/11/2011 23:11:11
--24times-- | 24hour:minute:second | 23:11:11
--datetimes-- | month/day/year hour:minute:second meridian | 11/11/2011 11:11:11 PM
--times-- | hour:minute:second meridian | 11:11:11 PM
--dydatetime-- | Day month/day/year hour:minute meridian | Fri 11/11/2011 11:11 PM 
--dydate-- | Day month/day/year | Fri 11/11/2011
--dy24datetime-- | Day month/day/year 24hour:minute | Fri 11/11/2011 23:11 
--dydatetimes-- | Day month/day/year hour:minute:second meridian | Fri 11/11/2011 11:11:11 PM
--dy24datetimes-- | Day month/day/year 24hour:minute:second | Fri 11/11/2011 23:11:11

### Twitter Profile Settings
Syntax | Function | Example
--- | --- | ---
--username-- | User's username | [ChlodAlejandro](https://twitter.com/ChlodALejandro)
--usermade-- | User creation date | Mon 02/20/2012 08:15:16 PM
--displayname-- | User's set display name | Chlod Alejandro (as of 12/19/2017 23:19)
--usertz-- | User's timezone | Beijing (despite living in the PH)
--userdesc-- | User's description (bio) | student, programmer, dad of [@MentionChlod](https://twitter.com/MentionChlod), SU fan, space fan. follow for too much tweets, retweets and likes. dm me if you need someone to talk to. (as of 12/19/2017 23:19)
--userid-- | User's Twitter ID | 497834217
--userloc-- | User's set location | 32 Twitter Drive, Internet (example only)
--userlang-- | User's preffered language | en (English)
--userpcolor-- | User's profile color (deprecated) | 000000

### Twitter Profile Counts
Syntax | Function | Example
--- | --- | ---
--followers-- | User's follower count | 202 (as of 12/19/2017 23:19)
--following-- | User's friend (following) count | 376 (as of 12/19/2017 23:19)
--tweetcount-- | User's tweet count | 5235 (as of 12/19/2017 23:19)
--favorites-- | User's like count | 4165 (as of 12/19/2017 23:19)
--lists-- | Count of lists user is included in | 1 (as of 12/19/2017 23:19)

### Twitter Profile Links
Syntax | Function | Example
--- | --- | ---
--userlink-- | User's set link | <bit.ly/chloda> (as of 12/19/2017 23:19)
--userprofile-- | User's profile link | <https://twitter.com/ChlodAlejandro>
--userdp-- | User's display picture link | <https://pbs.twimg.com/profile_images/934421851467431936/FeNLfUL5.jpg> (as of 12/19/2017 23:19)
--userbanner-- | User's banner link | <https://pbs.twimg.com/profile_banners/497834217/1511420159/web> (as of 12/19/2017 23:19)

## Warnings
THE PROGRAM IS IN PRE-ALPHA. IT IS BOUND TO BREAK.

THE PROGRAM WILL REQUIRE A USER-MADE TWITTER APPLICATION. THE USER WILL
CREATE THEIR OWN TWITTER APPLICATION IF THEY CHOOSE TO. THEY ARE LIABLE
FOR DAMAGE TO THEIR ACCOUNT IF THEIR ACCESS TOKEN OR ACCESS TOKEN SECRET
ARE EXPOSED TO ANYONE BUT THEMSELVES OR TWITTER. THE CREATOR OR THE
PROGRAM SHALL NOT RECIEVE ANY CHARGES. A BETTER SYSTEM FOR STORING
CONFIGURATIONS IS UPCOMING.

## Planned Features
1. Integrate more syntax rules
 	- Singular time syntax (--hour--, --minute--, --second--)
2. Create a better time syntax
	- Planning on 'insert tweet here by --username-- at date("dateformat")'
3. Patch up all bugs
4. Fix loopholes
5. Create much more secure way of storing keys

