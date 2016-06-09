package com.dhbw.programming;

import java.util.Arrays;
import java.util.stream.IntStream;

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

}
