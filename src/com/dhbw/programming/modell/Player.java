package com.dhbw.programming.modell;

public class Player {
	private String name;
	private int upperpoints;
	private int bonus;
	private int lowerpoints;
	private int totalpoints;
	private Data data;

	public Player(String name, int upperpoints, int bonus, int lowerpoints, int totalpoints, Data data) {
		super();
		this.name = name;
		this.upperpoints = upperpoints;
		this.bonus = bonus;
		this.lowerpoints = lowerpoints;
		this.totalpoints = totalpoints;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public int getUpperpoints() {
		return upperpoints;
	}

	public void setUpperpoints(int upperpoints) {
		this.upperpoints = upperpoints;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getLowerpoints() {
		return lowerpoints;
	}

	public void setLowerpoints(int lowerpoints) {
		this.lowerpoints = lowerpoints;
	}

	public int getTotalpoints() {
		return totalpoints;
	}

	public void setTotalpoints(int totalpoints) {
		this.totalpoints = totalpoints;
	}

}
