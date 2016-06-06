package com.dhbw.programming;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GameFrame extends JFrame {
	JButton diceOneButton, diceTwoButton, diceThreeButton, diceFourButton, diceFiveButton;

	private JPanel contentPane;
	private JTable table;
	public int[] dice = new int[5];
	public Vector<Integer> toRoll = new Vector<Integer>();
	private DefaultTableModel gameTableModel = new DefaultTableModel() {

		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}
	};

	private void rollDice() {
		if (diceOneButton.isEnabled() == true) {
			dice[0] = (int) ((Math.random() * 6) + 1);
		}
		if (diceTwoButton.isEnabled() == true) {
			dice[1] = (int) ((Math.random() * 6) + 1);
		}
		if (diceThreeButton.isEnabled() == true) {
			dice[2] = (int) ((Math.random() * 6) + 1);
		}
		if (diceFourButton.isEnabled() == true) {
			dice[3] = (int) ((Math.random() * 6) + 1);
		}
		if (diceFiveButton.isEnabled() == true) {
			dice[4] = (int) ((Math.random() * 6) + 1);
		}

		diceOneButton.setText(String.valueOf(dice[0]));
		diceTwoButton.setText(String.valueOf(dice[1]));
		diceThreeButton.setText(String.valueOf(dice[2]));
		diceFourButton.setText(String.valueOf(dice[3]));
		diceFiveButton.setText(String.valueOf(dice[4]));

	}

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
		diceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rollDice();
			}
		});
		diceButton.setBounds(569, 412, 155, 58);
		contentPane.add(diceButton);

		JButton undoButton = new JButton("Undo");
		undoButton.setBounds(569, 535, 155, 25);
		contentPane.add(undoButton);

		table = new JTable(gameTableModel);
		table.setBounds(12, 54, 545, 518);
		contentPane.add(table);
		gameTableModel.addColumn("Info");
		System.out.println(Main.getPlayerCount());
		for (int i = 0; i < Main.getPlayerCount(); i++) {
			System.out.println("Creating player " + i);
			gameTableModel.addColumn("Player" + i);
		}
		gameTableModel.addRow(new Object[] { "Info" });
		table.getColumn("Info");
		table.setCellSelectionEnabled(true);
		// table.isCellSelected(1, 1) {
		// setForeground(Color.blue);
		// };
		// table.editCellAt(1, 1, setForeground(Color.black));

		JPanel panel = new JPanel();
		panel.setBounds(569, 12, 155, 388);
		contentPane.add(panel);
		panel.setLayout(null);

		diceOneButton = new JButton("1");
		diceOneButton.setBounds(48, 12, 64, 64);
		panel.add(diceOneButton);

		diceTwoButton = new JButton("2");
		diceTwoButton.setBounds(48, 88, 64, 64);
		panel.add(diceTwoButton);

		diceThreeButton = new JButton("3");
		diceThreeButton.setBounds(48, 164, 64, 64);
		panel.add(diceThreeButton);

		diceFourButton = new JButton("4");
		diceFourButton.setBounds(48, 240, 64, 64);
		panel.add(diceFourButton);

		diceFiveButton = new JButton("5");
		diceFiveButton.setBounds(48, 310, 64, 64);
		panel.add(diceFiveButton);

		JButton helpButton = new JButton("Help");
		helpButton.setBounds(569, 498, 155, 25);
		contentPane.add(helpButton);

		diceOneButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (diceOneButton.isEnabled() == false) {
					diceOneButton.setEnabled(true);
				} else {
					diceOneButton.setEnabled(false);
				}
			}
		});
		diceTwoButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (diceTwoButton.isEnabled() == false) {
					diceTwoButton.setEnabled(true);
				} else {
					diceTwoButton.setEnabled(false);
				}
			}
		});
		diceThreeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (diceThreeButton.isEnabled() == false) {
					diceThreeButton.setEnabled(true);
				} else {
					diceThreeButton.setEnabled(false);
				}
			}
		});
		diceFourButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (diceFourButton.isEnabled() == false) {
					diceFourButton.setEnabled(true);
				} else {
					diceFourButton.setEnabled(false);
				}
			}
		});
		diceFiveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (diceFiveButton.isEnabled() == false) {
					diceFiveButton.setEnabled(true);
				} else {
					diceFiveButton.setEnabled(false);
				}
			}
		});
	}
}
