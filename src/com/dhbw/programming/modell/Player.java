package com.dhbw.programming.modell;

public class Player {
	private String name;
	private int points;
	private Data data;

	public Player(String name, int points, Data data) {
		super();
		this.name = name;
		this.points = points;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
