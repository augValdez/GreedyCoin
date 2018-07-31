/**
 * Plays Greedy Coin game such that the computer never loses.
 * 
 * [ YOUR NAME GOES HERE]
 */
import java.awt.List;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class GreedyCoinGame {
	
	private LinkedList<Integer> list = new LinkedList <Integer>();
	private LinkedList<Integer> location = new LinkedList<Integer>();
	public boolean turn;
	public boolean br = true;
	int blue;
	int red;
	private int userInput;
	int length;
	int userTotal;
	int compTotal;
	
	public GreedyCoinGame(String file) throws FileNotFoundException {
		Scanner inFile = new Scanner(new File(file));
		
		while(inFile.hasNextLine()) {
			list.add(inFile.nextInt());
		}
		
		inFile.close();
	}
		

	// prints the coins and their position
	public void printCoins() {
		System.out.println("\n-----------------------");
		//printing the numbers
		System.out.print("values:    ");
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		location.clear();
		for(int i = 0; i < list.size(); i++) {
				location.add(i);
		}
		//printing the locations
		System.out.println();
		System.out.print("locations: ");
		for(int i = 0; i < location.size(); i++) {
			System.out.print(location.get(i) + " ");			
		}
		System.out.println("\n-----------------------");
	}

	public void playGame() {
		
		System.out.println("Let's play the coin game!\n" + 
				"the goal is to get the highest sum,\n" + 
				"pick the first or last coin to add to your total\n");

		play();
	
		
	}
	
	public void play() {
		Scanner keyboard = new Scanner(System.in);
		
		while(list.size() != 0) {
			printCoins();
			
			System.out.println("Indicate the position of the coin you choose: 0 or " + (location.size() - 1));
			userInput = keyboard.nextInt();
		
			
			if(turn == false) {
				computerTurn();
				//userInput = keyboard.nextInt();
				turn = true;
			} else {
				userTurn();
				userInput = keyboard.nextInt();
				turn = false;
			}			
		}
		
			System.out.println("Game Over! Final Score \nUser: "+ userTotal + " \nComputer: "+ compTotal );
		
	}


	public boolean blueOrRed() {
		br = false;
		for(int i = 0; i<list.size();i+=2) {
			blue += list.get(i);
			br = true;
		}
		// Odds/Blue Strategy
		for(int i = 1; i<list.size(); i+=2) {
			red += list.get(i);
			br = false;
		}
		return br;
	}

	
	public void userTurn() {
		if(userInput == 0) {
			userTotal += list.getFirst();
			System.out.println("user selected: " + list.getFirst());
			list.removeFirst();
			location.removeFirst();
			System.out.println("Users score: " + userTotal);
			System.out.println("Computers score: " + compTotal);
		} else if (userInput == location.size() - 1) {
			userTotal += list.getLast();
			location.removeLast();
			System.out.println("user selected: " + list.getLast());
			list.removeLast();
			System.out.println("Users score: " + userTotal);
			System.out.println("Computers score: " + compTotal);
		} else {
			System.out.println("enter another number");			
		}
		turn = false;
		play();
		
	}
	
	
	
	public void computerTurn() {
		if(blueOrRed() == true) {
			compTotal += list.getFirst();
			System.out.println("comp selected: " + list.getFirst());
			list.removeFirst();
			location.removeFirst();
			System.out.println("Users score: " + userTotal);
			System.out.println("Computers score: " + compTotal);
		} else {
			compTotal += list.getLast();
			System.out.println("comp selected: " + list.getLast());
			list.removeLast();
			location.removeLast();
			System.out.println("Users score: " + userTotal);
			System.out.println("Computers score: " + compTotal);
		} 
		turn = true;
		play();
		
	
	}

	
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Pass a file on the command line");
			System.exit(0);
		} 
		GreedyCoinGame game = new GreedyCoinGame(args[0]);

		game.playGame();
	}

}
