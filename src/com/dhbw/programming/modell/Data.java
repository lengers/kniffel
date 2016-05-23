package com.dhbw.programming.modell;

public class Data {
	private ThreeOfAKind ThreeofakindOne;
	private ThreeOfAKind ThreeofakindTwo;
	private ThreeOfAKind ThreeofakindThree;
	private ThreeOfAKind ThreeofakindFour;
	private ThreeOfAKind ThreeofakindFive;
	private ThreeOfAKind ThreeofakindSix;

	private FourOfAKind FourofakindOne;
	private FourOfAKind FourofakindTwo;
	private FourOfAKind FourofakindThree;
	private FourOfAKind FourofakindFour;
	private FourOfAKind FourofakindFive;
	private FourOfAKind FourofakindSix;

	private Kniffel kniffel;
	private FullHouse fullHouse;
	private LittleStraight littleStraight;
	private BigStraight bigStraight;

	// public Data() {
	// }

	public Data(ThreeOfAKind threeofakindOne, ThreeOfAKind threeofakindTwo, ThreeOfAKind threeofakindThree,
			ThreeOfAKind threeofakindFour, ThreeOfAKind threeofakindFive, ThreeOfAKind threeofakindSix,
			FourOfAKind fourofakindOne, FourOfAKind fourofakindTwo, FourOfAKind fourofakindThree,
			FourOfAKind fourofakindFour, FourOfAKind fourofakindFive, FourOfAKind fourofakindSix, Kniffel kniffel,
			FullHouse fullHouse, LittleStraight littleStraight, BigStraight bigStraight) {
		super();
		ThreeofakindOne = threeofakindOne;
		ThreeofakindTwo = threeofakindTwo;
		ThreeofakindThree = threeofakindThree;
		ThreeofakindFour = threeofakindFour;
		ThreeofakindFive = threeofakindFive;
		ThreeofakindSix = threeofakindSix;
		FourofakindOne = fourofakindOne;
		FourofakindTwo = fourofakindTwo;
		FourofakindThree = fourofakindThree;
		FourofakindFour = fourofakindFour;
		FourofakindFive = fourofakindFive;
		FourofakindSix = fourofakindSix;
		this.kniffel = kniffel;
		this.fullHouse = fullHouse;
		this.littleStraight = littleStraight;
		this.bigStraight = bigStraight;
	}

	public ThreeOfAKind getThreeofakindOne() {
		return (ThreeofakindOne);
	}

	public void setThreeofakindOne(int number, int points, Boolean lock) {
		ThreeofakindOne.setNumber(number);
		ThreeofakindOne.setPoints(points);
		ThreeofakindOne.setLock(lock);
	}

	public ThreeOfAKind getThreeofakindTwo() {
		return ThreeofakindTwo;
	}

	public void setThreeofakindTwo(int number, int points, Boolean lock) {
		ThreeofakindTwo.setNumber(number);
		ThreeofakindTwo.setPoints(points);
		ThreeofakindTwo.setLock(lock);
	}

	public ThreeOfAKind getThreeofakindThree() {
		return ThreeofakindThree;
	}

	public void setThreeofakindThree(int number, int points, Boolean lock) {
		ThreeofakindThree.setNumber(number);
		ThreeofakindThree.setPoints(points);
		ThreeofakindThree.setLock(lock);
	}

	public ThreeOfAKind getThreeofakindFour() {
		return ThreeofakindFour;
	}

	public void setThreeofakindFour(int number, int points, Boolean lock) {
		ThreeofakindFour.setNumber(number);
		ThreeofakindFour.setPoints(points);
		ThreeofakindFour.setLock(lock);
	}

	public ThreeOfAKind getThreeofakindFive() {
		return ThreeofakindFive;
	}

	public void setThreeofakindFive(int number, int points, Boolean lock) {
		ThreeofakindFive.setNumber(number);
		ThreeofakindFive.setPoints(points);
		ThreeofakindFive.setLock(lock);
	}

	public ThreeOfAKind getThreeofakindSix() {
		return ThreeofakindSix;
	}

	public void setThreeofakindSix(int number, int points, Boolean lock) {
		ThreeofakindSix.setNumber(number);
		ThreeofakindSix.setPoints(points);
		ThreeofakindSix.setLock(lock);
	}

	public FourOfAKind getFourofakindOne() {
		return FourofakindOne;
	}

	public void setFourofakindOne(int number, int points, Boolean lock) {
		FourofakindOne.setNumber(number);
		FourofakindOne.setPoints(points);
		FourofakindOne.setLock(lock);
	}

	public FourOfAKind getFourofakindTwo() {
		return FourofakindTwo;
	}

	public void setFourofakindTwo(int number, int points, Boolean lock) {
		FourofakindTwo.setNumber(number);
		FourofakindTwo.setPoints(points);
		FourofakindTwo.setLock(lock);
	}

	public FourOfAKind getFourofakindThree() {
		return FourofakindThree;
	}

	public void setFourofakindThree(int number, int points, Boolean lock) {
		FourofakindThree.setNumber(number);
		FourofakindThree.setPoints(points);
		FourofakindThree.setLock(lock);
	}

	public FourOfAKind getFourofakindFour() {
		return FourofakindFour;
	}

	public void setFourofakindFour(int number, int points, Boolean lock) {
		FourofakindFour.setNumber(number);
		FourofakindFour.setPoints(points);
		FourofakindFour.setLock(lock);
	}

	public FourOfAKind getFourofakindFive() {
		return FourofakindFive;
	}

	public void setFourofakindFive(int number, int points, Boolean lock) {
		FourofakindFive.setNumber(number);
		FourofakindFive.setPoints(points);
		FourofakindFive.setLock(lock);
	}

	public FourOfAKind getFourofakindSix() {
		return FourofakindSix;
	}

	public void setFourofakindSix(int number, int points, Boolean lock) {
		FourofakindSix.setNumber(number);
		FourofakindSix.setPoints(points);
		FourofakindSix.setLock(lock);
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

}
