package com.dhbw.programming.modell;

public class Data {
	private OfAKind ofakindOne;
	private OfAKind ofakindTwo;
	private OfAKind ofakindThree;
	private OfAKind ofakindFour;
	private OfAKind ofakindFive;
	private OfAKind ofakindSix;

	private ThreeOfAKind threeofakind;
	private FourOfAKind fourofakind;
	private Kniffel kniffel;
	private FullHouse fullHouse;
	private LittleStraight littleStraight;
	private BigStraight bigStraight;
	private Chance chance;

	public Data(OfAKind threeofakindOne, OfAKind threeofakindTwo, OfAKind threeofakindThree, OfAKind threeofakindFour,
			OfAKind threeofakindFive, OfAKind threeofakindSix, ThreeOfAKind threeofakind, FourOfAKind fourofakind,
			FullHouse fullHouse, LittleStraight littleStraight, BigStraight bigStraight, Kniffel kniffel,
			Chance chance) {
		super();
		ofakindOne = threeofakindOne;
		ofakindTwo = threeofakindTwo;
		ofakindThree = threeofakindThree;
		ofakindFour = threeofakindFour;
		ofakindFive = threeofakindFive;
		ofakindSix = threeofakindSix;
		this.fourofakind = fourofakind;
		this.threeofakind = threeofakind;
		this.kniffel = kniffel;
		this.fullHouse = fullHouse;
		this.littleStraight = littleStraight;
		this.bigStraight = bigStraight;
	}

	public OfAKind getOfakindOne() {
		return (ofakindOne);
	}

	public void setOfakindOne(int number, int points, Boolean lock) {
		ofakindOne.setNumber(number);
		ofakindOne.setPoints(points);
		ofakindOne.setLock(lock);
	}

	public OfAKind getOfakindTwo() {
		return ofakindTwo;
	}

	public void setOfakindTwo(int number, int points, Boolean lock) {
		ofakindTwo.setNumber(number);
		ofakindTwo.setPoints(points);
		ofakindTwo.setLock(lock);
	}

	public OfAKind getOfakindThree() {
		return ofakindThree;
	}

	public void setOfakindThree(int number, int points, Boolean lock) {
		ofakindThree.setNumber(number);
		ofakindThree.setPoints(points);
		ofakindThree.setLock(lock);
	}

	public OfAKind getOfakindFour() {
		return ofakindFour;
	}

	public void setOfakindFour(int number, int points, Boolean lock) {
		ofakindFour.setNumber(number);
		ofakindFour.setPoints(points);
		ofakindFour.setLock(lock);
	}

	public OfAKind getOfakindFive() {
		return ofakindFive;
	}

	public void setOfakindFive(int number, int points, Boolean lock) {
		ofakindFive.setNumber(number);
		ofakindFive.setPoints(points);
		ofakindFive.setLock(lock);
	}

	public OfAKind getOfakindSix() {
		return ofakindSix;
	}

	public void setOfakindSix(int number, int points, Boolean lock) {
		ofakindSix.setNumber(number);
		ofakindSix.setPoints(points);
		ofakindSix.setLock(lock);
	}

	public ThreeOfAKind getThreeofakind() {
		return threeofakind;
	}

	public void setThreeofakind(int number, int points, Boolean lock) {
		threeofakind.setLock(lock);
		threeofakind.setPoints(points);
		threeofakind.setNumber(number);
	}

	public FourOfAKind getFourofakind() {
		return fourofakind;
	}

	public void setFourofakind(int number, int points, Boolean lock) {
		fourofakind.setLock(lock);
		fourofakind.setPoints(points);
		fourofakind.setNumber(number);
	}

	public Kniffel getKniffel() {
		return kniffel;
	}

	public void setKniffel(Boolean lock) {
		kniffel.setLock(lock);
	}

	public FullHouse getFullHouse() {
		return fullHouse;
	}

	public void setFullHouse(Boolean lock) {
		fullHouse.setLock(lock);
	}

	public LittleStraight getLittleStraight() {
		return littleStraight;
	}

	public void setLittleStraight(Boolean lock) {
		littleStraight.setLock(lock);
	}

	public BigStraight getBigStraight() {
		return bigStraight;
	}

	public void setBigStraight(Boolean lock) {
		bigStraight.setLock(lock);
	}

	public Chance getChance() {
		return chance;
	}

	public void setChance(Chance chance) {
		this.chance = chance;
	}

}
