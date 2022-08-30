package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Words {
	public static String[] wordStrings = { "other", "there", "which", "their", "about", "write", "would", "these",
			"thing", "could", "sound", "water", "first", "place", "where", "after", "round", "every", "under", "great",
			"think", "cause", "right", "three", "small", "large", "spell", "light", "house", "again", "point", "world",
			"build", "earth", "stand", "found", "study", "still", "learn", "plant", "cover", "state", "never", "cross",
			"start", "might", "story", "while", "press", "close", "night", "north", "white", "begin", "paper", "group",
			"music", "those", "often", "until", "river", "carry", "began", "horse", "watch", "color", "plain", "usual",
			"young", "ready", "above", "leave", "black", "short", "class", "order", "south", "piece", "since", "whole",
			"space", "heard", "early", "reach", "table", "vowel", "money", "serve", "voice", "power", "field", "pound",
			"drive", "stood", "front", "teach", "final", "green", "quick", "ocean", "clear", "wheel", "force", "plane",
			"stead", "laugh", "check", "shape", "bring", "paint", "among", "grand", "heart", "heavy", "dance", "speak",
			"count", "store", "train", "sleep", "prove", "catch", "mount", "board", "glass", "grass", "visit", "month",
			"happy", "eight", "raise", "solve", "metal", "seven", "third", "shall", "floor", "coast", "value", "fight",
			"sense", "quite", "broke", "scale", "child", "speed", "organ", "dress", "cloud", "quiet", "stone", "climb",
			"stick", "smile", "trade", "mouth", "exact", "least", "shout", "wrote", "clean", "break", "blood", "touch",
			"brown", "equal", "woman", "whose", "radio", "spoke", "human", "party", "agree", "chair", "fruit", "thick",
			"guess", "sharp", "crowd", "sight", "hurry", "chief", "clock", "enter", "major", "fresh", "allow", "print",
			"block", "chart", "event", "quart", "truck", "noise", "level", "throw", "shine", "wrong", "broad", "anger",
			"claim", "sugar", "death", "skill", "women", "thank", "match", "steel", "guide", "score", "apple", "pitch",
			"dream", "total", "basic", "smell", "track", "shore", "sheet", "favor", "spend", "chord", "share", "bread",
			"offer", "slave", "chick", "enemy", "reply", "drink", "occur", "range", "steam", "meant", "teeth",
			"shell" };

	public static void GetWordDefenition(String word) {
		try {
			URL url = new URL(String.format("https://api.dictionaryapi.dev/api/v2/entries/en/%s", word));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();

			// check its connection to the API and get the data as a JSON
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			} else {
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());
				while (scanner.hasNext())
					informationString.append(scanner.nextLine());
				scanner.close();

				// read and parse the JSON to a string
				JSONParser parse = new JSONParser();
				JSONArray dataobject = (JSONArray) parse.parse(String.valueOf(informationString));
				JSONObject wordArrayData = (JSONObject) dataobject.get(0);

				// get the meaning and find its definitions
				JSONArray meaning = (JSONArray) parse.parse(String.valueOf(wordArrayData.get("meanings")));
				JSONObject meaningOBJ = (JSONObject) meaning.get(0);

				// get the meaning and find its definitions, this whole heap of garbage get the arrays, gets a singular property in object, then proceeds to make it into an object, and then turns it back into a JSON string
				// which finally loops back into a sole definition
				JSONObject definitionString = (JSONObject) parse.parse(String.valueOf(meaningOBJ));

				JSONObject definition = (JSONObject) ((JSONArray) parse
						.parse(String.valueOf(definitionString.get("definitions")))).get(0);
				System.out.println(definition.get("definition"));
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
