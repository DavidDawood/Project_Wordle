package main;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static int lives = 5;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String selectWord = Words.wordStrings[new Random().nextInt(Words.wordStrings.length)];
		System.out.println("Guess the 5 letter word!");
		while (lives > 0) {
			String guessedWord = scanner.next();
			if (guessedWord.length() != 5) {
				System.out.println("Please insert a 5 letter word");
				continue;
			}
			if (selectWord.equals(guessedWord)) {
				System.out.println(String.format(
						"Congratulations the word was %s and you got it right with %s lives left", selectWord, lives));
				System.out.println(String.format("The definition for %s is...", selectWord));
				Words.GetWordDefenition(selectWord);
				scanner.close();
				return;
			}
			checkInWord(selectWord, guessedWord);
			--lives;
		}
		scanner.close();
		System.out.println("You lost");
	}

	// returns the index of found character, if not found returns -1
	public static void checkInWord(String word, String guessedWord) {
		char[] arr = word.toCharArray();
		char[] guessed = guessedWord.toCharArray();
		for (int i = 0; i < arr.length; i++) {

			// in word and in correct position
			if (arr[i] == guessed[i]) {
				System.out.println(String.format("%s is in the correct position", guessed[i]));
				continue;
			}
			// in word but wrong position
			if (isInWord(word, guessed[i]) && arr[i] != guessed[i]) {
				System.out.println(String.format("%s is in the wrong position", guessed[i]));
				continue;
			}
			// not in word
			System.out.println(String.format("%s is not in the word", guessed[i]));
		}
	}

	public static boolean isInWord(String word, Character character) {
		return word.chars().anyMatch(x -> x ==character);
	}
	
	//	https://api.dictionaryapi.dev/api/v2/entries/en/<word>
}

//Project: Wordle
//​
//MVP
//​
//-   Recreate a simplified version of the game Wordle to be played in a Java console application
//-   The game should be able to randomly select a 5-letter word from the provided word list
//-   The user will be able to enter a guess word that is also 5 characters long
//-   For each letter, the application will tell the user if that letter is correct, right letter in the wrong position, or wrong letter
//-   After the user guesses 6 times incorrectly, the game is over and the user loses
//-   If the user guesses the word correctly, the game is over and the user wins
//-   In addition you must implement one of the following extensions, or an extension of your own design as approved by a coach
//​
//Extensions
//​
//-   Read the word list directly from the file when the application starts
//-   Create a history file that keeps track of user wins/losses and how many letters they guessed it in
//-   When the game finishes and the secret word is shown, also display the dictionary definition for that word 
// -   Hint: Use a free API (https://dictionaryapi.dev/)
//-   Generate an output of the word/guesses and copy it to the user's clipboard so they can share it on socials