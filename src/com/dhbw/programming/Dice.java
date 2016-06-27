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

		determinePoints(player);
	}

	private void determinePoints(Player player) {
		Data data = player.getData();

		OfAKind[] kinds = { data.getOfakindOne(), data.getOfakindTwo(), data.getOfakindThree(), data.getOfakindFour(),
				data.getOfakindFive(), data.getOfakindSix() };

		for (int i = 0; i < numbers.length; i++) {
			// Chance always possible
			// if (data.getChance().getLock() == false) {
			//
			// }
			if (numbers[i] >= 2) {
				if (numbers[i] == 3) {
					if (data.getThreeofakind().getLock() == false) {
						data.getThreeofakind().setPoints(pips);
						data.getThreeofakind().setShow(true);
						System.out.println("ThreeOfAKind show -> true");
					} else if (kinds[i].getLock() == false) {
						kinds[i].setPoints(i * 3);
						kinds[i].setShow(true);
						System.out.println("kinds[" + i + "] show -> true");
					} else {
						for (int j = 0; j < numbers.length; j++) {
							if (j != i && j == 2 && data.getFullHouse().getLock() == false) {
								data.getFullHouse().setShow(true);
								System.out.println("FullHouse show -> true");
							}
						}
					}

				} else if (numbers[i] == 4) {
					if (data.getFourofakind().getLock() == false) {
						data.getFourofakind().setNumber(pips);
						data.getFourofakind().setShow(true);
						System.out.println("FourOfAKind show -> true");
					} else if (kinds[i].getLock() == false) {
						kinds[i].setPoints(i * 4);
						kinds[i].setShow(true);
						System.out.println("kinds[" + i + "] show -> true");

					}

				} else if (numbers[i] == 5) {
					if (data.getKniffel().getLock() == false) {
						data.getKniffel().setShow(true);
						System.out.println("Kniffel show -> true");
					} else if (kinds[i].getLock() == false && data.getKniffel().getAdditionalKniffel() == true) {
						// Additional kniffel
						data.getKniffel().setAdditionalKniffel(true);
						kinds[i].setPoints(50);
						kinds[i].setShow(true);
						System.out.println("kinds[" + i + "] show -> true");
					}

				} else {
					// only 2#
					kinds[i].setPoints(i * 2);
					kinds[i].setShow(true);
					System.out.println("kinds[" + i + "] show -> true");
				}
			} else {
				// only Straight remains
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
						System.out.println("LittleStraight show -> true");
					} else if (n >= 5 && data.getBigStraight().getLock() == false) {
						data.getBigStraight().setShow(true);
						System.out.println("BigStraight show -> true");
						data.getLittleStraight().setShow(true);
						System.out.println("LittleStraight show -> true");
					}
					n = 0;
				}
			}

		}

	}

}
