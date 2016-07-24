package com.dhbw.programming;

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
