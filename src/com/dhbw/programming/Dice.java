package com.dhbw.programming;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.dhbw.programming.modell.Data;
import com.dhbw.programming.modell.OfAKind;
import com.dhbw.programming.modell.Player;

// this class if for dicing, you can dive with all 5 dices or with a selected number of dices,
// the dice happens as a random int from 1 to 6, the methods in this class also check, if the conditions for the fields are met

public class Dice {

	// we have 5 dices so we creat an array of 5 dices
	private int[] dice = new int[5];

	// we have 6 different number which can be rolled, numbers 1 - 6
	private int[] numbers = new int[6];
	private int pips;
	private String[][] color;

	public Dice(String[][] color) {
		this.color = color;
	}

	// getters and setters
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

	// the function rollDice dices for the player if he hits the button,
	// therefore it dices every dice which is not set false (by clicking on it)
	public void rollDice(Player player, Boolean diceOneButton, Boolean diceTwoButton, Boolean diceThreeButton,
			Boolean diceFourButton, Boolean diceFiveButton) {
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

		// Debug dice
		// dice[0] = 2;
		// dice[1] = 1;
		// dice[2] = 2;
		// dice[3] = 1;
		// dice[4] = 2;

		this.numbers = fillNumbersArray(dice);

		this.pips = IntStream.of(dice).sum();

		determinePoints(player);
	}

	// fakeRoll is needed if it is the next players turn, he should not have the
	// last dices numbers
	public void fakeRoll(Player player, Boolean diceOneButton, Boolean diceTwoButton, Boolean diceThreeButton,
			Boolean diceFourButton, Boolean diceFiveButton) {

		dice[0] = 0;
		dice[1] = 0;
		dice[2] = 0;
		dice[3] = 0;
		dice[4] = 0;

		this.numbers = fillNumbersArray(dice);

	}

	private void determinePoints(Player player) {
		Data data = player.getData();

		// debug information
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
			if (data.getChance().getShow() == false) {
				if (data.getChance().getLock() == false) {
					data.getChance().setPoints(pips);
					data.getChance().setShow(true);
				}
			}

			if (numbers[i] >= 2) {
				if (numbers[i] == 3) {
					// enabling three of a kind because dices meet conditions
					// for the field
					Threeofakind(data);

					if (numbers[i] == 3) {
						if (kinds[i].getLock() == false) {
							kinds[i].setPoints((i + 1) * 3);
							kinds[i].setShow(true);
						} else {
							kinds[i].setShow(false);
						}

						if (data.getFullHouse().getShow() == false) { // check
																		// first
																		// before
																		// starting
																		// the
																		// loop
							for (int j = 0; j < numbers.length; j++) {
								if (j != i && numbers[j] == 2 && data.getFullHouse().getLock() == false
										&& data.getFullHouse().getShow() == false) {
									if (data.getFullHouse().getShow() == false) { // check
																					// again
										data.getFullHouse().setShow(true);
										System.out.println("FullHouse is true");
										break;
									} else {
										data.getFullHouse().setShow(false);
										System.out.println("FullHouse is false");
									}

								}
							}

						}
					}
					// enabling four of a kind because dices meet conditions for
					// the field
				} else if (numbers[i] == 4) {
					Fourofakind(data);
					Threeofakind(data);
					if (kinds[i].getLock() == false) {
						kinds[i].setPoints((i + 1) * 4);
						kinds[i].setShow(true);

					} else {
						kinds[i].setShow(false);
					}

					// enabling three + four of a kind and Kniffel, because
					// dices meet conditions for all field (5 dices with same
					// numbers)
				} else if (numbers[i] > 4) {
					Fourofakind(data);
					Threeofakind(data);
					if (data.getKniffel().getShow() == false) {
						if (data.getKniffel().getLock() == false) {
							data.getKniffel().setShow(true);
						} else {
							data.getKniffel().setShow(false);
						}
					}

					if (kinds[i].getLock() == false) {
						kinds[i].setPoints((i + 1) * 5);
						kinds[i].setShow(true);
					} else {
						kinds[i].setShow(false);
					}

					if (kinds[i].getShow() == false) {
						if (kinds[i].getLock() == false && data.getKniffel().getAdditionalKniffel() == false) {
							// Additional kniffel
							data.getKniffel().setAdditionalKniffel(true);
							kinds[i].setShow(true);
							kinds[i].setPoints(data.getKniffel().getPoints());
						} else if (data.getKniffel().getShow() == false) {
							data.getKniffel().setShow(false);
						}
					}

				} else {
					// only 2#
					if (kinds[i].getLock() == false) {
						kinds[i].setPoints((i + 1) * 2);
						kinds[i].setShow(true);
					} else {
						kinds[i].setShow(false);
					}

				}
			} else {

				// only Straight and single kindOf remains
				if (kinds[i].getLock() == false) {
					if (numbers[i] == 1) {
						kinds[i].setPoints(i + 1);
						kinds[i].setShow(true);
					} else {
						kinds[i].setShow(false);

					}
				}

				// dices meet conditions for little straight
				if (data.getLittleStraight().getShow() == false) {
					if (i < 3 && numbers[i] > 0 // 1
							&& numbers[i + 1] > 0 // 2
							&& numbers[i + 2] > 0 // 3
							&& numbers[i + 3] > 0) {
						System.out.println("little straight");
						data.getLittleStraight().setShow(true);
					} else {
						data.getLittleStraight().setShow(false);
					}
				}

				// dices meet conditions for big straight
				if (data.getBigStraight().getShow() == false) {
					if (i < 2 && numbers[i] >= 1 && numbers[i + 1] >= 1 && numbers[i + 2] >= 1 && numbers[i + 3] >= 1
							&& numbers[i + 4] >= 1 && data.getBigStraight().getShow() == false) {
						System.out.println("big straight");
						data.getBigStraight().setShow(true);
					} else {
						data.getBigStraight().setShow(false);
					}
				}

			}

		}

	}

	private void Fourofakind(Data data) {
		if (data.getFourofakind().getLock() == false) {
			if (data.getFourofakind().getShow() == false) {
				data.getFourofakind().setPoints(pips);
				data.getFourofakind().setShow(true);
			} else {
				data.getFourofakind().setShow(false);
			}
		} else {
			data.getFourofakind().setShow(true);
		}
	}

	private void Threeofakind(Data data) {
		if (data.getThreeofakind().getLock() == false) {
			if (data.getThreeofakind().getShow() == false) {
				data.getThreeofakind().setPoints(pips);
				data.getThreeofakind().setShow(true);
			} else {
				data.getThreeofakind().setShow(false);
			}
		} else {
			data.getThreeofakind().setShow(true);
		}
	}

}
