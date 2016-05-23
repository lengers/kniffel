package com.dhbw.programming.modell;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class GameFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton diceButton = new JButton("Roll the dice");
		diceButton.setBounds(569, 377, 155, 58);
		contentPane.add(diceButton);

		JPanel dicePanel = new JPanel();
		dicePanel.setBounds(569, 12, 155, 351);
		contentPane.add(dicePanel);

		JButton undoButton = new JButton("Undo");
		undoButton.setBounds(569, 535, 155, 25);
		contentPane.add(undoButton);

		table = new JTable();
		table.setBounds(12, 12, 545, 560);
		contentPane.add(table);
	}
}
