package com.dhbw.programming;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.dhbw.programming.modell.Data;
import com.dhbw.programming.modell.Player;

public class Dice {

	private int[] dice;
	private int[] numbers;
	private int pips;

	public Dice() {
	}

	public int[] getDice() {
		return dice;
	}

	public void setDice(int[] dice) {
		this.dice = dice;
	}

	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public int getPips() {
		return pips;
	}

	public void setPips(int pips) {
		this.pips = pips;
	}

	private int[] fillNumbersArray(int[] dice) {
		Arrays.fill(numbers, 0);
		for (int i = 0; i < dice.length; i++) {
			numbers[i - 1] += 1;
		}
		return numbers;

	}

	public void rollDice(Boolean diceOneButton, Boolean diceTwoButton, Boolean diceThreeButton, Boolean diceFourButton,
			Boolean diceFiveButton) {
		if (diceOneButton == true) {
			dice[0] = (int) ((Math.random() * 6) + 1);
		}
		if (diceTwoButton == true) {
			dice[1] = (int) ((Math.random() * 6) + 1);
		}
		if (diceThreeButton == true) {
			dice[2] = (int) ((Math.random() * 6) + 1);
		}
		if (diceFourButton == true) {
			dice[3] = (int) ((Math.random() * 6) + 1);
		}
		if (diceFiveButton == true) {
			dice[4] = (int) ((Math.random() * 6) + 1);
		}

		this.numbers = fillNumbersArray(dice);

		this.pips = IntStream.of(dice).sum();
	}

	private void determinePoints(Player player) {
		Data data = player.getData();

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] >= 4) {
				// Five-of-a-kind, called "Kniffel", results in 50 points
				if (numbers[i] == 5 && data.getKniffel().getLock() == false) {
					data.getKniffel().setShow(true);
					// player.setTotalpoints(player.getTotalpoints() +
					// data.getKniffel().getPoints());
					// data.setKniffel(true);
					// lastOperation = data.getKniffel();
					// Four-of-a-kind, add all pips as points
				} else {
					data.getOfakindFive().setShow(true);
					data.getOfakindTwo().setShow(true);
					data.getOfakindThree().setShow(true);
					data.getOfakindFour().setShow(true);
					data.getOfakindFive().setShow(true);
					System.out.println();
					switch (i) {
					case 1:
						if (data.getOfakindOne().getLock() == false) {
							data.getOfakindOne().setShow(true);
						}
						break;
					case 2:
						if (data.getOfakindTwo().getLock() == false) {
							data.getOfakindTwo().setShow(true);
						}
						// break;
						// case 3:
						// if (data.getFourofakindThree().getLock() == false) {
						// player.setTotalpoints(player.getTotalpoints() +
						// pips);
						// data.setFourofakindThree(2, pips, true);
						// System.out.println("You rolled a four-of-a-kind!");
						// }
						// break;
						// case 4:
						// if (data.getFourofakindFour().getLock() == false) {
						// player.setTotalpoints(player.getTotalpoints() +
						// pips);
						// data.setFourofakindFour(2, pips, true);
						// System.out.println("You rolled a four-of-a-kind!");
						// }
						// break;
						// case 5:
						// if (data.getFourofakindFive().getLock() == false) {
						// player.setTotalpoints(player.getTotalpoints() +
						// pips);
						// data.setFourofakindFive(2, pips, true);
						// System.out.println("You rolled a four-of-a-kind!");
						// }
						// break;
						// case 6:
						// if (data.getFourofakindSix().getLock() == false) {
						// player.setTotalpoints(player.getTotalpoints() +
						// pips);
						// data.setFourofakindSix(2, pips, true);
						// System.out.println("You rolled a four-of-a-kind!");
						// }
						// break;
					}

				}
				// if (player[1][i] == 0) {
				// player[0][i] = IntStream.of(dice).sum();
				// player[1][i] = 1;
				// System.out.println("You rolled a four-of-a-kind!");
				// }
			} else if (numbers[i] == 3) {
				if (pips < 2) {

				} else {

					// switch (i) {
					// case 1:
					// if (data.getThreeofakindOne().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindOne(1, pips, true);
					// System.out.println("You rolled a three-of-a-kind!");
					// }
					// break;
					// case 2:
					// if (data.getThreeofakindTwo().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindTwo(2, pips, true);
					// }
					// break;
					// case 3:
					// if (data.getThreeofakindThree().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindThree(3, pips, true);
					// }
					// break;
					// case 4:
					// if (data.getThreeofakindFour().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindFour(4, pips, true);
					// }
					// break;
					// case 5:
					// if (data.getThreeofakindFive().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindFive(5, pips, true);
					// }
					// break;
					// case 6:
					// if (data.getThreeofakindSix().getLock() == false) {
					// player.setTotalpoints(player.getTotalpoints() + pips);
					// data.setThreeofakindSix(6, pips, true);
					// }
					// break;
					// default:
					// System.out.println("No field unlocked, error.");
					// break;
				}
			} else if (numbers[0] == 1 && numbers[1] == 1 && numbers[2] == 1 && numbers[3] == 1 && numbers[4] == 1) {
				data.setLittleStraight(true);
				player.setTotalpoints(player.getTotalpoints() + data.getLittleStraight().getPoints());
			} else if (numbers[1] == 1 && numbers[2] == 1 && numbers[3] == 1 && numbers[4] == 1 && numbers[5] == 1) {
				data.setBigStraight(true);
				player.setTotalpoints(player.getTotalpoints() + data.getBigStraight().getPoints());
			}
		}

	}

}
