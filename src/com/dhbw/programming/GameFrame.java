package com.dhbw.programming;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.dhbw.programming.help.ColoredTableCellRenderer;
import com.dhbw.programming.modell.Data;
import com.dhbw.programming.modell.Player;
import com.dhbw.programming.modell.Throw;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	public JButton diceOneButton, diceTwoButton, diceThreeButton, diceFourButton, diceFiveButton;
	public JEditorPane textArea;

	private int roundCount = 0;
	private JPanel contentPane;
	private JTable table;
	private int wrongClick = 0;
	private ArrayList<Player> playerList;
	private ListIterator<Player> iterator;
	private Player player;
	private Data data;
	private int playerCount;
	private Boolean cellSelected = false;
	private int rollCount = 0;
	private String color[][];
	private Dice diceModell = new Dice(color);
	private ColoredTableCellRenderer tableCellRenderer = new ColoredTableCellRenderer();
	private JButton[] buttons = new JButton[5];
	private JButton diceButton;
	private JButton doneButton;
	private Throw[] potentialPoints;
	private ImageIcon[] icons = { new ImageIcon(Main.class.getResource("Dice1.png")),
			new ImageIcon(Main.class.getResource("Dice2.png")), new ImageIcon(Main.class.getResource("Dice3.png")),
			new ImageIcon(Main.class.getResource("Dice4.png")), new ImageIcon(Main.class.getResource("Dice5.png")),
			new ImageIcon(Main.class.getResource("Dice6.png")) };
	private DefaultTableModel gameTableModel = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}

		// necessary for cell coloring
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
	};
	private static GameFrame frame;

	private void consoleSend(String string) {
		textArea.setText(textArea.getText() + "\n" + string);
		textArea.repaint();
	}

	private void rollDice(Player player) {
		diceModell.rollDice(player, diceOneButton.isEnabled(), diceTwoButton.isEnabled(), diceThreeButton.isEnabled(),
				diceFourButton.isEnabled(), diceFiveButton.isEnabled());

		int[] dice = diceModell.getDice();

		for (int i = 0; i < buttons.length; i++) {
			ImageIcon icon = icons[dice[i] - 1];
			buttons[i].setIcon(icon);
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(ArrayList<Player> playerList, int playerCount, JFrame MainFrame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GameFrame(playerList, playerCount, MainFrame);
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
	public GameFrame(ArrayList<Player> playerList, int pCount, JFrame MainFrame) {
		this.playerList = playerList;
		playerCount = pCount;
		iterator = playerList.listIterator();
		player = iterator.next();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				MainFrame.setVisible(true);
			};
		});
		setBounds(100, 100, 761, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		diceButton = new JButton("Roll the dice");
		diceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Using DATA from " + player.getName());
				System.out.println("iterator.previousIndex(): " + iterator.previousIndex());
				data = player.getData();
				potentialPoints = new Throw[] { data.getOfakindOne(), data.getOfakindTwo(), data.getOfakindThree(),
						data.getOfakindFour(), data.getOfakindFive(), data.getOfakindSix(), null, null, null,
						data.getThreeofakind(), data.getFourofakind(), data.getFullHouse(), data.getLittleStraight(),
						data.getBigStraight(), data.getKniffel(), data.getChance() };

				for (int i = 0; i < potentialPoints.length; i++) {
					if (potentialPoints[i] != null) {
						potentialPoints[i].setShow(false);
					}
				}

				if (rollCount == 2) {
					diceButton.setEnabled(false);
				}
				System.out.println("Index: " + iterator.nextIndex());
				System.out.println("Hasnext: " + iterator.hasNext());
				System.out.println(playerList.size());
				rollDice(player);
				System.out.println("Index: " + iterator.nextIndex());
				rollCount++;
				repaintTable();
				doneButton.setEnabled(true);

			}
		});
		diceButton.setBounds(569, 412, 155, 58);
		contentPane.add(diceButton);

		doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaintTable();
				if (cellSelected == true) {
					// add up all points and display them
					player.setUpperpoints(data.getOfakindOne().getPoints() + data.getOfakindTwo().getPoints()
							+ data.getOfakindThree().getPoints() + data.getOfakindFour().getPoints()
							+ data.getOfakindFive().getPoints() + data.getOfakindSix().getPoints());
					if (player.getUpperpoints() >= 63) {
						player.setBonus(35);
					}
					player.setUpperpointsAll(player.getUpperpoints() + player.getBonus());
					player.setLowerpoints(data.getBigStraight().getPoints() + data.getChance().getPoints()
							+ data.getLittleStraight().getPoints() + data.getFullHouse().getPoints()
							+ data.getKniffel().getPoints() + data.getThreeofakind().getPoints()
							+ data.getFourofakind().getPoints());
					player.setTotalpoints(player.getUpperpointsAll() + player.getLowerpoints());
					gameTableModel.setValueAt(player.getUpperpoints(), 7, iterator.nextIndex());
					gameTableModel.setValueAt(player.getBonus(), 8, iterator.nextIndex());
					gameTableModel.setValueAt(player.getUpperpointsAll(), 9, iterator.nextIndex());
					gameTableModel.setValueAt(player.getLowerpoints(), 17, iterator.nextIndex());
					gameTableModel.setValueAt(player.getUpperpointsAll(), 18, iterator.nextIndex());
					gameTableModel.setValueAt(player.getTotalpoints(), 19, iterator.nextIndex());

					// re-enable all Dice buttons
					for (int i = 0; i < buttons.length; i++) {
						buttons[i].setEnabled(true);
					}
					// Begin again
					if (iterator.hasNext() != true) {
						if (roundCount <= 13) {
							iterator = playerList.listIterator();
							System.out.println("Resetting iterator");
							roundCount++;

						} else {
							consoleSend("[*] End of game.");
							RankingFrame.main(playerList, playerCount, frame);
							frame.setVisible(false);
						}
					}

					player = iterator.next();

					consoleSend("[*] It's " + player.getName() + "'s turn.");
					rollCount = 0;
					System.out.println("Using " + player);
					repaintTable();
					doneAction();

					for (int i = 0; i < buttons.length; i++) {
						buttons[i].setIcon(null);
					}
					diceButton.setEnabled(true);
				} else {
					consoleSend("[!] You must select a cell to continue");
				}
				doneButton.setEnabled(false);
			}
		});
		doneButton.setBounds(569, 482, 155, 45);
		contentPane.add(doneButton);

		table = new JTable(gameTableModel);
		table.setDefaultRenderer(String.class, tableCellRenderer);
		table.setBounds(12, 83, 545, 518);
		contentPane.add(table);
		gameTableModel.addColumn("Info");
		System.out.println(playerCount);
		for (int i = 0; i < playerCount; i++) {
			System.out.println("Creating player " + i);
			gameTableModel.addColumn("Player" + i);
		}
		gameTableModel.addRow(new Object[] { "Info" });

		// creating Table
		String[] tableDescriptions = { "nur Einser z\u00e4hlen", "nur Zweier z\u00e4hlen", "nur Dreier z\u00e4hlen",
				"nur Vierer z\u00e4hlen", "nur F\u00fcnfer z\u00e4hlen", "nur Sechser z\u00e4hlen", "gesamt",
				"Bonus bei 63 oder mehr", "gesamt oberer Teil", "Dreierpasch", "Viererpasch", "Full-House",
				"Kleine Stra\u00dfe", "Gro\u00dfe Stra\u00dfe", "Kniffel", "Chance", "gesamt unterer Teil",
				"gesamt oberer Teil", "Endsumme" };

		for (int i = 0; i < tableDescriptions.length; i++) {
			gameTableModel.addRow(new Object[] { tableDescriptions[i] });
		}

		// writing player names in jTable
		for (int i = 0; i < playerCount; i++) {
			System.out.println("Writing player " + (i));
			gameTableModel.setValueAt(playerList.get(i).getName(), 0, i + 1);
		}

		System.out.println("player" + playerCount);
		color = new String[playerCount + 1][21];

		for (int i = 0; i < color.length; i++) {
			for (int j = 0; j < 21; j++) {
				color[i][j] = "white";
			}
		}

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();

				// do some action if appropriate column, else print on console,
				// that player is clicking on wrong cell
				try {
					if (rollCount > 0) {
						if (column == iterator.nextIndex()) {
							if (potentialPoints[row - 1].getLock() == false) {
								potentialPoints[row - 1].setLock(true);
								System.out.println("Set lock for " + potentialPoints[row - 1].getClass());
								// table.repaint();
								cellSelected = true;
								doneAction();
								// potentialPoints[row - 1].setLock(false);
							} else {
								System.out.println("ja, ne. SHOW ist " + potentialPoints[row - 1].getShow());
							}
						} else {
							throw new IllegalArgumentException("Nope.");
						}
					} else {
						consoleSend("[!] Please roll the dice before you select points.");
					}
				} catch (Exception e1) {
					if (wrongClick >= 3) {
						consoleSend("[!] I'm beginning to think you are doing this on purpose...");
						wrongClick = 0;
					}
					consoleSend("[!] You cannot enter points there.");
					wrongClick++;
				}

				System.out.println(row + ", " + column);

			}
		});

		// Einmaliges Aufrufen der Einfaerben-Funktion
		tableCellRenderer.dyeTable(color, table, gameTableModel, false, false, 0, 2);

		// table.getColumn("Info");
		table.setCellSelectionEnabled(true);
		// table.isCellSelected(1, 1) {
		// setForeground(Color.blue);
		// };
		// table.editCellAt(1, 1, setForeground(Color.black));

		JPanel panel = new JPanel();
		panel.setBounds(569, 12, 155, 388);
		contentPane.add(panel);
		panel.setLayout(null);

		// creating first dice as clickable button
		diceOneButton = new JButton();
		diceOneButton.setBounds(48, 12, 64, 64);
		panel.add(diceOneButton);
		buttons[0] = diceOneButton;

		// creating second dice as clickable button
		diceTwoButton = new JButton();
		diceTwoButton.setBounds(48, 88, 64, 64);
		panel.add(diceTwoButton);
		buttons[1] = diceTwoButton;

		// creating third dice as clickable button
		diceThreeButton = new JButton();
		diceThreeButton.setBounds(48, 164, 64, 64);
		panel.add(diceThreeButton);
		buttons[2] = diceThreeButton;

		// creating fourth dice as clickable button
		diceFourButton = new JButton();
		diceFourButton.setBounds(48, 240, 64, 64);
		panel.add(diceFourButton);
		buttons[3] = diceFourButton;

		// creating fifth dice as clickable button
		diceFiveButton = new JButton();
		diceFiveButton.setBounds(48, 310, 64, 64);
		panel.add(diceFiveButton);
		buttons[4] = diceFiveButton;

		// creating helpButton
		JButton helpButton = new JButton("Help");
		helpButton.setBounds(569, 547, 155, 25);
		contentPane.add(helpButton);

		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String help = "<center><b>Yahtzee Manual/Rules</b></center><br><br>" + "<b>Begin of Game</b><br>"
						+ "At the start of the game, there will pop up a small window where you can select the player numbers. Change their names by clicking in the right column of the player table and click 'Start' to begin. Then the game can start and the real game window will appear!<br><br>"
						+ "<b>Game Goal</b><br>"
						+ "Clever dice and achieve the highest score on the Yahtzee table at the end!<br><br>"
						+ "<b>Course of Play</b><br>"
						+ "The players are in turn with rolling the dice once per round. They can dice up to three times by pressing the ‘Roll the dice’ button. The first roll is done with all five dices by clicking on the dice button. Then the player can decide with how many dices he want to dice at 2nd and 3rd attempt. The Player can select or unselect dices by clicking on them. He then rolls with those he ‘did not like’ and therefore did not click on. He can try to improve his results with the 2nd and 3rd attempt or he can forgo one or both additional dices by clicking the ‘Done’ button after the first dice. Latest after the third roll, the player must enter his dice roll in Yahtzee in the table by clicking in the table and clicking the ‘Done’ button. If the dices meet none of the conditions for the box, then zero is entered in a box which is chosen by the player.<br><br>"
						+ "<b>The Points</b><br>"
						+ "The Yahtzee table is divided into an upper and lower section. The upper section shows the Boxes for ones, twos, threes, fours, fives and sixes. If the player decides to write his result in one of these boxes by clicking on them, the result of all dices with the same number are summed up and added to the field. The field is shown as green if the result is higher than zero and signalizes the player, that the field would be a possible good choice.<br><br>"
						+ "<b>The Bonus</b><br>"
						+ "To achieve the 35 bonus points, the players must have at least 63 or more points in the upper section of the table. If they achieve a higher upper score than 63, the bonus points are added automatically by the program.<br><br>"
						+ "<b>Three of a kind:</b><br>"
						+ "You need at least 3 dices with the same number. Every Dice does count for the score.<br><br>"
						+ "<b>Four of a kind:</b><br>"
						+ "You need at least 4 dices with the same number. Every Dice does count for the score.<br><br>"
						+ "<b>Full-House:</b><br>"
						+ "You need at least 2 dices with the same number and 3 dices with the same number. You get 25 points as score.<br><br>"
						+ "<b>Little Straight</b><br>"
						+ "You need at least 4 out of 5 dices showing following numbers such as 1,2,3,4 or 2,3,4,5, or 3,4,5,6. You get 30 points as score.<br><br>"
						+ "<b>Big Straight</b><br>"
						+ "You need all 5 dices showing following numbers such as 1,2,3,4,5 or 2,3,4,5,6. You get 40 points as score.<br><br>"
						+ "<b>Yahtzee</b><br>"
						+ "You need 5 dices showing the same number. You get 50 points as score.<br><br>"
						+ "<b>Chance</b><br>"
						+ "Every number does count added as score, there are no requirements.<br><br>"
						+ "<b>2nd and 3rd Yahtzee</b><br>"
						+ "The player has 2 opportunities if he dices a 2nd or 3rd Yahtzee: ‘Yahtzee as Joker’ or ‘Yahtzee for additional 50 points’.<br><br>"
						+ "<b>Yahtzee as Joker</b><br>"
						+ "If the player has already scored the Yahtzee field and the relating field for the numbers in the upper part, he is allowed to count his throw as any field in the lower part, e.g. he can count the 2nd Yahtzee as a Big Straight and get 40 points for it. But if he has already scored every field in the lower part, he has to ‘zero’ a field in the upper part.<br><br>"
						+ "<b>Yahtzee for 50 additional points</b><br>"
						+ "If the player has already scored in his Yahtzee field and the relating field for the numbers is still free in the upper part, he gets 50 additional points. He gets the score for the numbers in the upper field (for example: Yahtzee with number 5 results in 25 points for upper field of number 5) and he gets 50 additional points in the Yahtzee field.<br><br>"
						+ "<b>End of Game</b><br>"
						+ "The game ends after the last player scored in his last field. The program now calculates the final scores and opens a ranking table showing all players. The player with the highest score is the winner. The player can start a new game by clicking on the ‘New Game’ button.<br><br>";
				JEditorPane textArea = new JEditorPane("text/html", help);
				JScrollPane scrollPane = new JScrollPane(textArea);
				scrollPane.setPreferredSize(new Dimension(600, 600));
				JOptionPane.showMessageDialog(contentPane, scrollPane, "About", JOptionPane.PLAIN_MESSAGE);

			}
		});

		// JScrollBar scrollBar = new JScrollBar();
		// scrollBar.setBounds(540, 12, 17, 59);
		// contentPane.add(scrollBar);
		//
		// JTextPane textPane = new JTextPane();
		// textPane.setBounds(12, 13, 545, 58);
		// contentPane.add(textPane);

		// creating Console to display game informations
		textArea = new JEditorPane("text", "Let's begin!");
		JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 13, 545, 58);
		textArea.setEditable(false);
		contentPane.add(scrollPane);

		// adding Listener for first dice, so you can select or unselect
		// the dice by clicking on it
		diceOneButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (diceOneButton.isEnabled() == false) {
					diceOneButton.setEnabled(true);
				} else {
					diceOneButton.setEnabled(false);
				}
			}
		});
		// adding Listener for second dice, so you can select or unselect
		// the dice by clicking on it
		diceTwoButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (diceTwoButton.isEnabled() == false) {
					diceTwoButton.setEnabled(true);
				} else {
					diceTwoButton.setEnabled(false);
				}
			}
		});
		// adding Listener for third dice, so you can select or unselect
		// the dice by clicking on it
		diceThreeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (diceThreeButton.isEnabled() == false) {
					diceThreeButton.setEnabled(true);
				} else {
					diceThreeButton.setEnabled(false);
				}
			}
		});
		// adding Listener for fourth dice, so you can select or unselect
		// the dice by clicking on it
		diceFourButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (diceFourButton.isEnabled() == false) {
					diceFourButton.setEnabled(true);
				} else {
					diceFourButton.setEnabled(false);
				}
			}
		});
		// adding Listener for fifth dice, so you can select or unselect
		// the dice by clicking on it
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

	private void doneAction() {
		// display 0 in white fields for all cells that are not
		// available with current pips
		for (int j = 0; j < potentialPoints.length; j++) {
			color[iterator.nextIndex()][(j + 1)] = "white";
			System.out.println("Coloring [" + iterator.nextIndex() + "][" + (j + 1) + "] white.");
			if (potentialPoints[j] != null && potentialPoints[j].getLock() == false) {
				potentialPoints[j].setShow(false);
			}
		}
		repaintTable();
		diceButton.setEnabled(false);
	}

	// painting table in green and grey
	private void repaintTable() {
		for (int i = 0; i < potentialPoints.length; i++) {
			if (potentialPoints[i] != null) {
				if (potentialPoints[i].getLock()) {
					color[iterator.nextIndex()][(i + 1)] = "grey";
					gameTableModel.setValueAt(potentialPoints[i].getPoints(), (i + 1), iterator.nextIndex());
				} else if (potentialPoints[i].getShow() != null) {
					color[iterator.nextIndex()][(i + 1)] = "green";
					gameTableModel.setValueAt(potentialPoints[i].getPoints(), (i + 1), iterator.nextIndex());
				} else {
					color[iterator.nextIndex()][(i + 1)] = "white";
				}
			}
			if (iterator.previousIndex() == 0) {
				color[playerList.size()][i + 1] = "white";
			} else {
				color[iterator.previousIndex()][(i + 1)] = "white";
			}
		}
		tableCellRenderer.repaint();
	}
}
