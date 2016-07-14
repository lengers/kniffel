package com.dhbw.programming.help;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColoredTableCellRenderer extends DefaultTableCellRenderer {

	private String color[][];

	/** Function fills color-array with values **/
	public void dyeTable(String color[][], JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {

		this.color = color;
		getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	};

	/** Function dyes cell with different colors **/
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// Array �berpr�fen nach gew�nschter Farbe
		switch (color[column][row]) {
		case "white":
			comp.setBackground(null);
			comp.setForeground(null);
			break;
		case "green":
			comp.setBackground(Color.GREEN);
			break;
		case "blue":
			comp.setBackground(Color.BLUE);
			break;
		case "red":
			comp.setBackground(Color.RED);
			break;
		case "grey":
			comp.setBackground(Color.LIGHT_GRAY);
			break;
		}

		// if (color[column][row].equals("grey")){
		// comp.setBackground(Color.LIGHT_GRAY);
		// }
		// else if (color[column][row].equals("green")){
		// comp.setBackground(Color.GREEN);
		// }
		// else if (color[column][row].equals("red")){
		// comp.setBackground(Color.RED);
		// }
		// else if (color[column][row].equals("white")){
		// comp.setBackground(null);
		// }

		return (comp);
	}
}
