package com.dhbw.programming;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.dhbw.programming.modell.BigStraight;
import com.dhbw.programming.modell.Data;
import com.dhbw.programming.modell.FourOfAKind;
import com.dhbw.programming.modell.FullHouse;
import com.dhbw.programming.modell.Kniffel;
import com.dhbw.programming.modell.LittleStraight;
import com.dhbw.programming.modell.Player;
import com.dhbw.programming.modell.ThreeOfAKind;

public class Main {

	private static Scanner user_input;
	private static int[] rollable;
	private static int[] dice;
	private static int[][] player;
	static int[] numbers = new int[6];
	static int points = 0;

	public static void main(String[] args) {

		// TODO create correct dat

		ThreeOfAKind bla = new ThreeOfAKind(1);
		Data data = new Data(new ThreeOfAKind(1), new ThreeOfAKind(2), new ThreeOfAKind(3), new ThreeOfAKind(4),
				new ThreeOfAKind(5), new ThreeOfAKind(6), new FourOfAKind(1), new FourOfAKind(2), new FourOfAKind(3),
				new FourOfAKind(4), new FourOfAKind(5), new FourOfAKind(6), new Kniffel(), new FullHouse(),
				new LittleStraight(), new BigStraight());
		Player player = new Player("Tom", 0, data);

		data.setFourofakindSix(data.getFourofakindSix().getNumber(), data.getFourofakindSix().getPoints(),
				data.getFourofakindSix().getLock());

		System.out.println(player.getData());
		Bot bot = new Bot();
		bot.introduction();

		MainFrame.main(null);

		dice = new int[5];
		user_input = new Scanner(System.in);
		// player = new int[2];
		// Points at position
		// player[0] = new int[16];
		// Lock for position
		// player[1] = new int[16];
		// Arrays.fill(player[1], 0);

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

		Main.getValue(dice);
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

	private static void getValue(int[] dice) {
		Arrays.fill(numbers, 0);
		for (int i = 0; i < dice.length; i++) {
			numbers[i - 1] += 1;
		}

		// if (Arrays.stream(numbers).allMatch()) {
		// // Predicate.isEqual(numbers[0])) {
		//
		// }
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] >= 4) {
				// Five-of-a-kind, called "Kniffel", results in 50 points
				if (numbers[i] == 5 && player[1][15] == 0) {
					player[0][15] = 50;
					player[1][15] = 1;
					System.out.println("You rolled a yahtzee!");
					// Four-of-a-kind, add all pips as points
				} else if (player[1][i] == 0) {
					player[0][i] = IntStream.of(dice).sum();
					player[1][i] = 1;
					System.out.println("You rolled a four-of-a-kind!");
				}
			} else if (numbers[i] == 3) {
				if (true) {

				} else if (player[1][i] == 0) {
					player[0][i] = IntStream.of(dice).sum();
					player[1][i] = 1;
					System.out.println("You rolled a three-of-a-kind!");
				} else {
					System.out.println("No field unlocked, error.");
				}
			}
		}
	}

}
