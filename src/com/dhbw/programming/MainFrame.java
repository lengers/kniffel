package com.dhbw.programming;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JTable nameTable;
	private DefaultTableModel tableModel = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return column != 0;
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameFrame.main(null);
			}
		});
		startButton.setBounds(255, 210, 117, 25);
		contentPane.add(startButton);

		JLabel lblIWantTo = new JLabel("I want to start a new game with ");
		lblIWantTo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIWantTo.setBounds(12, 12, 256, 15);
		contentPane.add(lblIWantTo);

		JSpinner playerSpinner = new JSpinner();
		playerSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Main.setPlayerCount(((Number) playerSpinner.getValue()).intValue());

				if (Main.getPlayerCount() < tableModel.getRowCount() + 1) {
					tableModel.removeRow(tableModel.getRowCount() - 1);
					// Main.setPlayerCount(Main.getPlayerCount() - 1);
				} else {
					// Main.setPlayerCount(Main.getPlayerCount() + 1);
					tableModel.addRow(new Object[] { "Player " + (Main.getPlayerCount() - 1),
							"Player" + (Main.getPlayerCount() - 1) });
				}
			}
		});
		playerSpinner.setFont(new Font("Dialog", Font.BOLD, 14));
		playerSpinner.setModel(new SpinnerNumberModel(2, 2, 8, 1));
		playerSpinner.setBounds(274, 7, 38, 25);
		contentPane.add(playerSpinner);

		JLabel lblPlayers = new JLabel("players.");
		lblPlayers.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPlayers.setBounds(319, 12, 70, 15);
		contentPane.add(lblPlayers);

		JCheckBox botCheckBox = new JCheckBox("");
		botCheckBox.setAction(action);
		botCheckBox.setBounds(35, 35, 28, 23);
		contentPane.add(botCheckBox);

		JSpinner botSpinner = new JSpinner();
		botSpinner.setEnabled(false);
		botSpinner.setBounds(187, 38, 28, 20);
		contentPane.add(botSpinner);

		JLabel lblComputerPlayers = new JLabel("computer players.");
		lblComputerPlayers.setEnabled(false);
		lblComputerPlayers.setBounds(219, 39, 153, 15);
		contentPane.add(lblComputerPlayers);

		JLabel lblIWantTo_1 = new JLabel("I want to include");
		lblIWantTo_1.setEnabled(false);
		lblIWantTo_1.setBounds(64, 39, 128, 15);
		contentPane.add(lblIWantTo_1);

		JButton aboutButton = new JButton("About");
		aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		aboutButton.setBounds(12, 210, 117, 25);
		contentPane.add(aboutButton);

		nameTable = new JTable(tableModel);
		nameTable.setBounds(12, 66, 360, 132);
		contentPane.add(nameTable);
		tableModel.addColumn("Col1");
		tableModel.addColumn("Col2");
		tableModel.addRow(new Object[] { "Player 1", "Player1" });
		tableModel.addRow(new Object[] { "Player 2", "Player2" });
		Main.setPlayerCount(2);
		System.out.println(nameTable.getModel());
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
