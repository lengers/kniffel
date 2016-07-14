package com.dhbw.programming;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	private JPanel contentPane;
	private JTable table;
	private int wrongClick = 0;
	private ArrayList<Player> playerList;
	private ListIterator<Player> iterator;
	private Player player;
	private Data data;
	private int playerCount;
	private int rollCount = 0;
	private String color[][];
	private Dice diceModell = new Dice(color);
	private ColoredTableCellRenderer tableCellRenderer = new ColoredTableCellRenderer();
	// private
	private JButton[] buttons = new JButton[5]; // = { diceOneButton,
												// diceTwoButton,
	// diceThreeButton, diceFourButton,
	// diceFiveButton };

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

		// Notwendig f�r Einfaerbung
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
	};

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
	public static void main(ArrayList<Player> playerList, int playerCount) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame(playerList, playerCount);
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
	public GameFrame(ArrayList<Player> playerList, int pCount) {
		this.playerList = playerList;
		playerCount = pCount;
		iterator = playerList.listIterator();
		player = iterator.next();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton diceButton = new JButton("Roll the dice");
		diceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Using DATA from " + player.getName() + ", OFAKIND for 2 is "
						+ player.getData().getOfakindTwo().getLock());
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

				if (rollCount < 3) {
					System.out.println("Index: " + iterator.nextIndex());
					System.out.println("Hasnext: " + iterator.hasNext());
					System.out.println(playerList.size());
					rollDice(player);
					System.out.println("Index: " + iterator.nextIndex());
					rollCount++;
				} else {
					System.out.println("You can only roll the dice three times.");
					consoleSend("You can only roll the dice three times.");
				}

				// iterate over array of potential points, check if lock is
				// true, then set grey (disabled) or if show is true, then set
				// green (selectable)

				repaintTable();
			}
		});
		diceButton.setBounds(569, 412, 155, 58);
		contentPane.add(diceButton);

		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// re-enable all Dice buttons
				for (int i = 0; i < buttons.length; i++) {
					buttons[i].setEnabled(true);
				}
				// Begin again
				if (iterator.hasNext() != true) {
					iterator = playerList.listIterator();
					System.out.println("Resetting iterator");
				}
				player = iterator.next();

				consoleSend("It's " + player.getName() + "'s turn.");
				rollCount = 0;
				System.out.println("Using " + player);
				repaintTable();
				doneAction();
				for (int i = 0; i < buttons.length; i++) {
					buttons[i].setIcon(null);
				}
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
		String[] tableDescriptions = { "nur Einser zählen", "nur Zweier zählen", "nur Dreier zählen",
				"nur Vierer zählen", "nur Fünfer zählen", "nur Sechser zählen", "gesamt", "Bonus bei 63 oder mehr",
				"gesamt oberer Teil", "Dreierpasch", "Viererpasch", "Full-House", "Kleine Straße", "Große Straße",
				"Kniffel", "Chance", "gesamt unterer Teil", "gesamt oberer Teil", "Endsumme" };

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

				// do some action if appropriate column
				try {
					if (column == iterator.nextIndex()) {
						if (potentialPoints[row - 1].getShow() == true) {
							potentialPoints[row - 1].setLock(true);
							System.out.println("Set lock for " + potentialPoints[row - 1].getClass());
							// table.repaint();
							doneAction();
						}
					} else {
						throw new IllegalArgumentException("Nope.");
					}
				} catch (Exception e1) {
					if (wrongClick >= 3) {
						consoleSend("I'm beginning to think you are doing this on purpose...");
						wrongClick = 0;
					}
					consoleSend("You cannot enter points there.");
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

		diceOneButton = new JButton();
		diceOneButton.setBounds(48, 12, 64, 64);
		panel.add(diceOneButton);
		buttons[0] = diceOneButton;

		diceTwoButton = new JButton();
		diceTwoButton.setBounds(48, 88, 64, 64);
		panel.add(diceTwoButton);
		buttons[1] = diceTwoButton;

		diceThreeButton = new JButton();
		diceThreeButton.setBounds(48, 164, 64, 64);
		panel.add(diceThreeButton);
		buttons[2] = diceThreeButton;

		diceFourButton = new JButton();
		diceFourButton.setBounds(48, 240, 64, 64);
		panel.add(diceFourButton);
		buttons[3] = diceFourButton;

		diceFiveButton = new JButton();
		diceFiveButton.setBounds(48, 310, 64, 64);
		panel.add(diceFiveButton);
		buttons[4] = diceFiveButton;

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

		textArea = new JEditorPane("text", "Let's begin!");
		JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 13, 545, 58);
		textArea.setEditable(false);
		contentPane.add(scrollPane);

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

	private void doneAction() {
		// display 0 in white fields for all cells that are not
		// available with current pips
		for (int j = 0; j < potentialPoints.length; j++) {
			color[iterator.nextIndex()][(j + 1)] = "white";
			System.out.println("Coloring [" + iterator.nextIndex() + "][" + (j + 1) + "] white.");
			if (potentialPoints[j] != null) {
				potentialPoints[j].setShow(false);
			}
		}
		repaintTable();
	}

	private void repaintTable() {
		for (int i = 0; i < potentialPoints.length; i++) {
			if (potentialPoints[i] != null) {
				if (potentialPoints[i].getLock()) {
					color[iterator.nextIndex()][(i + 1)] = "grey";
					// System.out.println("Coloring [" +
					// iterator.nextIndex() + "][" + (i + 1) + "]
					// grey.");
				} else if (potentialPoints[i].getShow()) {
					// System.out.println(color[iterator.nextIndex()][i]);
					color[iterator.nextIndex()][(i + 1)] = "green";
					// System.out.println("Coloring [" +
					// iterator.nextIndex() + "][" + (i + 1) + "]
					// green.");
					gameTableModel.setValueAt(potentialPoints[i].getPoints(), (i + 1), iterator.nextIndex());
				} else {
					color[iterator.nextIndex()][(i + 1)] = "white";
					// System.out.println("Coloring [" +
					// iterator.nextIndex() + "][" + (i + 1) + "]
					// white.");

					gameTableModel.setValueAt(potentialPoints[i].getPoints(), (i + 1), iterator.nextIndex());
				}
			}
		}
		tableCellRenderer.repaint();
	}
}
