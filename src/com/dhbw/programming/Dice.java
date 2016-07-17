package com.dhbw.programming;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.dhbw.programming.modell.Data;
import com.dhbw.programming.modell.OfAKind;
import com.dhbw.programming.modell.Player;

public class Dice {

	private int[] dice = new int[5];
	private int[] numbers = new int[6];
	private int pips;
	private String[][] color;

	public Dice(String[][] color) {
		this.color = color;
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
			numbers[dice[i] - 1] += 1;
		}
		return numbers;

	}

	public void rollDice(Player player, Boolean diceOneButton, Boolean diceTwoButton, Boolean diceThreeButton,
			Boolean diceFourButton, Boolean diceFiveButton) {
		// if (diceOneButton == true) {
		// dice[0] = (int) ((Math.random() * 6) + 1);
		// }
		// if (diceTwoButton == true) {
		// dice[1] = (int) ((Math.random() * 6) + 1);
		// }
		// if (diceThreeButton == true) {
		// dice[2] = (int) ((Math.random() * 6) + 1);
		// }
		// if (diceFourButton == true) {
		// dice[3] = (int) ((Math.random() * 6) + 1);
		// }
		// if (diceFiveButton == true) {
		// dice[4] = (int) ((Math.random() * 6) + 1);
		// }

		dice[0] = 1;

		dice[1] = 1;

		dice[2] = 1;

		dice[3] = 1;

		dice[4] = 1;

		this.numbers = fillNumbersArray(dice);

		this.pips = IntStream.of(dice).sum();

		determinePoints(player);
	}

	private void determinePoints(Player player) {
		Data data = player.getData();

		System.out.println("Rolling dice for " + player.getName());

		OfAKind[] kinds = { data.getOfakindOne(), data.getOfakindTwo(), data.getOfakindThree(), data.getOfakindFour(),
				data.getOfakindFive(), data.getOfakindSix() };

		/*
		 * For every option: Check if the lock is present, if it is, set it to
		 * show, which will be ignored by the coloring system, as it is blocked.
		 * If the lock is not set, change the points, which will then be
		 * displayed by the coloring function.
		 */

		for (int i = 0; i < numbers.length; i++) {
			System.out.println(i + ": " + numbers[i]);
			// Chance always possible
			if (data.getChance().getLock() == false) {
				data.getChance().setPoints(pips);
				data.getChance().setShow(true);
				// System.out.println("Chance show -> true");
			}

			if (numbers[i] >= 2) {
				if (numbers[i] == 3) {
					if (data.getThreeofakind().getLock() == false) {
						data.getThreeofakind().setPoints(pips);
						data.getThreeofakind().setShow(true);
					} else {
						data.getThreeofakind().setShow(true);
					}
					if (numbers[i] == 3) {
						if (kinds[i].getLock() == false) {
							kinds[i].setPoints((i + 1) * 3);
							System.out.println("Value: " + kinds[i].getPoints());
							kinds[i].setShow(true);
						}
						kinds[i].setShow(true);
						System.out.println("Value: " + kinds[i].getPoints());
						for (int j = 0; j < numbers.length; j++) {
							if (j != i && j == 2 && data.getFullHouse().getLock() == false) {
								data.getFullHouse().setShow(true);
							} else {
								data.getFullHouse().setShow(true);
							}

						}
					} else {
						data.getFullHouse().setShow(true);
					}
				} else if (numbers[i] == 4) {
					if (data.getFourofakind().getLock() == false) {
						data.getFourofakind().setPoints(pips);
						data.getFourofakind().setShow(true);
					} else {
						data.getFourofakind().setShow(true);
					}
					if (kinds[i].getLock() == false) {
						kinds[i].setPoints((i + 1) * 4);
						kinds[i].setShow(true);

					} else {
						kinds[i].setShow(true);
					}

				} else if (numbers[i] > 4) {
					if (data.getKniffel().getLock() == false) {
						data.getKniffel().setShow(true);
					} else {
						data.getKniffel().setShow(true);

					}
					if (kinds[i].getLock() == false && data.getKniffel().getAdditionalKniffel() == true) {
						// Additional kniffel
						data.getKniffel().setAdditionalKniffel(true);
						kinds[i].setShow(true);
					} else {
						data.getKniffel().setShow(true);
					}

				} else {
					// only 2#
					kinds[i].setPoints((i + 1) * 2);
					kinds[i].setShow(true);
				}
			} else {
				// enable all options that are still set to zero points to allow
				// for elimination
				data.getKniffel().setShow(true);
				data.getThreeofakind().setShow(true);
				data.getFourofakind().setShow(true);
				data.getFullHouse().setShow(true);

				// only Straight and single kindOf remains
				if (numbers[i] == 1) {
					kinds[i].setPoints(i + 1);
					kinds[i].setShow(true);
					System.out.println("ONLY ONE OF " + i);
				} else {
					kinds[i].setShow(true);

				}

				// Straight
				int n = 0;
				for (int z = 0; z <= 3; z++) {
					for (int j = z; j < numbers.length; j++) {
						if (numbers[j] >= 1 && numbers[j] < 3) {
							n += 1;
						}
						if (numbers[j] == 0) {
							break;
						}
					}
					if (n == 4 && data.getLittleStraight().getLock() == false) {
						data.getLittleStraight().setShow(true);
					} else {
						data.getLittleStraight().setShow(true);

					}
					if (n >= 5 && data.getBigStraight().getLock() == false) {
						data.getBigStraight().setShow(true);
						data.getLittleStraight().setShow(true);
					} else {
						data.getBigStraight().setShow(true);

					}
					n = 0;
				}
				// color everything else that has the value zero, but can be
				// crossed out

			}

		}

	}

}
