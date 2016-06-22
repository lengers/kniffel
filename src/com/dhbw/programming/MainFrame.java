package com.dhbw.programming;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.dhbw.programming.modell.BigStraight;
import com.dhbw.programming.modell.Chance;
import com.dhbw.programming.modell.Data;
import com.dhbw.programming.modell.FourOfAKind;
import com.dhbw.programming.modell.FullHouse;
import com.dhbw.programming.modell.Kniffel;
import com.dhbw.programming.modell.LittleStraight;
import com.dhbw.programming.modell.OfAKind;
import com.dhbw.programming.modell.Player;
import com.dhbw.programming.modell.ThreeOfAKind;

public class MainFrame extends JFrame {

	// test f�r pushen 23 safjksjfkejflkwjf

	private JLabel lblIWantTo_1;
	private JSpinner botSpinner;
	private JLabel lblComputerPlayers;

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JTable nameTable;
	private int botCount = 0;
	private int playerCount;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private Data data = new Data(new OfAKind(1), new OfAKind(2), new OfAKind(3), new OfAKind(4), new OfAKind(5),
			new OfAKind(6), new ThreeOfAKind(), new FourOfAKind(), new FullHouse(), new LittleStraight(),
			new BigStraight(), new Kniffel(), new Chance());
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
				System.out.println(playerCount);
				for (int i = 0; i < playerCount; i++) {
					System.out.println("Creating player " + i);
					Player player = new Player("Player" + i, 0, 0, 0, 0, data);
					playerList.add(player); // new Player((String) "Player" + i,
											// 0, 0, 0, 0, data));
				}
				GameFrame.main(playerList, playerCount);
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
				playerCount = (((Number) playerSpinner.getValue()).intValue());

				if (playerCount < tableModel.getRowCount() + 1) {
					tableModel.removeRow(tableModel.getRowCount() - 1);
					// Main.setPlayerCount(Main.getPlayerCount() - 1);
				} else {
					// Main.setPlayerCount(Main.getPlayerCount() + 1);
					tableModel.addRow(new Object[] { "Player " + (playerCount), "Player" + (playerCount) });
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
		botCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (botCheckBox.isSelected()) {
					System.out.println("Bots activated. Bleep bloop.");
					lblIWantTo_1.setEnabled(true);
					lblComputerPlayers.setEnabled(true);
					botSpinner.setEnabled(true);
					tableModel.addRow(new Object[] { "Bot " + (botCount), "Bot" + (botCount) });
					playerSpinner.setEnabled(false);

				} else {
					System.out.println("Bots deactivated. Bleeeeeeep. :(");
					lblIWantTo_1.setEnabled(false);
					lblComputerPlayers.setEnabled(false);
					botSpinner.setEnabled(false);
					playerSpinner.setEnabled(true);
				}
			}
		});

		botSpinner = new JSpinner();
		botSpinner.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		botSpinner.setEnabled(false);
		botSpinner.setBounds(187, 38, 28, 20);
		contentPane.add(botSpinner);

		lblComputerPlayers = new JLabel("computer players.");
		lblComputerPlayers.setEnabled(false);
		lblComputerPlayers.setBounds(219, 39, 153, 15);
		contentPane.add(lblComputerPlayers);

		lblIWantTo_1 = new JLabel("I want to include");
		lblIWantTo_1.setEnabled(false);
		lblIWantTo_1.setBounds(64, 39, 128, 15);
		contentPane.add(lblIWantTo_1);

		JButton aboutButton = new JButton("About");
		aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String about = "This project was created as an assignment for four students of the DHBW Mannheim, DE. <br><br>"
						+ "It implements a simple version of the game Yahtzee. <br><br>"
						+ "To play, select the number of players (and, if you wish, the amount of bots to compete with), change their names by clicking in the right column of the player table, and click start to begin."
						+ " Rules of the game and further instructions are available once the game has begun.";
				JEditorPane textArea = new JEditorPane("text/html", about);
				JScrollPane scrollPane = new JScrollPane(textArea);
				scrollPane.setPreferredSize(new Dimension(280, 280));
				JOptionPane.showMessageDialog(contentPane, scrollPane, "About", JOptionPane.PLAIN_MESSAGE);

			}
		});
		aboutButton.setBounds(12, 210, 117, 25);
		contentPane.add(aboutButton);

		nameTable = new JTable(tableModel);
		nameTable.setBounds(0, 53, 360, 132);
		contentPane.add(nameTable);
		tableModel.addColumn("Col1");
		tableModel.addColumn("Col2");
		tableModel.addRow(new Object[] { "Player 1", "Player1" });
		tableModel.addRow(new Object[] { "Player 2", "Player2" });
		playerCount = 2;
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
