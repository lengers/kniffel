package com.dhbw.programming;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

	private static Scanner user_input;
	private static int[] rollable;
	private static int[] dice;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		dice = new int[5];
		user_input = new Scanner(System.in);

		System.out.println("After the first shake, this are your dice:");
		for (int i = 0; i < dice.length; i++) {
			dice[i] = (int) ((Math.random() * 6) + 1);
		}
		Main.printDice(dice);

		Main.userRequest();
		Main.userRequest();
		System.out.println("You scored " + IntStream.of(dice).sum() + " points.");
	}

	public static void printDice(int[] dice) {

		System.out.println(" 1  2  3  4  5");
		System.out.println(" " + dice[0] + "  " + dice[1] + "  " + dice[2] + "  " + dice[3] + "  " + dice[4]);
	}

	private static void again(int[] dice) {
		System.out.println("Which dice do you want to roll again? You can choose from [" + rollable
				+ "] (Enter in form of 1,3,4)");
		String rollAgain = user_input.next();
		System.out.println(rollAgain);
		String[] toRoll = rollAgain.split(",");

		for (int i = 0; i < toRoll.length; i++) {
			int j = Integer.parseInt(toRoll[i]) - 1;
			dice[j] = (int) ((Math.random() * 6) + 1);
		}

		Main.printDice(dice);
	}

	private static void userRequest() {
		System.out.println("Do you want to go again? (y/n)");
		String go_again = user_input.next();
		go_again = "y";
		if (go_again == "y") {
			Main.again(dice);
		} else if (go_again == "n") {
			System.out.println("Ok, your dice are final for this round.");
		} else {
			System.out.println("I did not understand that...");
			System.out.println(go_again);
		}
	}

}
