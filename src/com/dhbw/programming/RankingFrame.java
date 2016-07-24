/**
 * 
 */
package com.dhbw.programming;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dhbw.programming.help.playerpointsComparator;
import com.dhbw.programming.modell.Player;

public class RankingFrame extends JFrame {

	private JPanel contentPane;
	private Image image = null;
	private ArrayList<Player> playerList;
	private ListIterator<Player> iterator;
	private Player player;
	private int playerCount;
	private String path;
	private static RankingFrame frame;

	public static void main(ArrayList<Player> playerList, int playerCount, JFrame MainFrame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RankingFrame frame = new RankingFrame(playerList, playerCount, MainFrame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RankingFrame(ArrayList<Player> playerList, int pCount, JFrame MainFrame) {

		this.playerList = playerList;
		playerCount = pCount;
		iterator = playerList.listIterator();
		player = iterator.next();

		Collections.sort(playerList, new playerpointsComparator());

		switch (playerCount) {
		case 2:
			path = "file:src/com/dhbw/programming/ranking2.png";
			// platz8Label.setVisible(false);
			break;
		case 3:
			path = "file:src/com/dhbw/programming/ranking3.png";
			break;
		case 4:
			path = "file:src/com/dhbw/programming/ranking4.png";
			break;
		case 5:
			path = "file:src/com/dhbw/programming/ranking5.png";
			break;
		case 6:
			path = "file:src/com/dhbw/programming/ranking6.png";
			break;
		case 7:
			path = "file:src/com/dhbw/programming/ranking7.png";
			break;
		case 8:
			path = "file:src/com/dhbw/programming/ranking8.png";
			break;
		}

		try {
			image = ImageIO.read(new URL(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(753, 643));

		// addWindowListener(new WindowAdapter() {
		// @Override
		// public void windowClosing(WindowEvent e) {
		// frame.setVisible(false);
		// MainFrame.setVisible(true);
		// };
		// });

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};

		contentPane.setBounds(0, 0, 753, 643);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainFrame.setVisible(true);
			}
		});
		newGameButton.setBounds(150, 513, 155, 58);
		getContentPane().add(newGameButton);

		// JButton backButton = new JButton("Back");
		// backButton.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// GameFrame.main(playerList, playerCount, null);
		// System.out.println("TestTest2");
		// }
		// });
		// backButton.setBounds(448, 513, 155, 58);
		// getContentPane().add(backButton);

		JLabel platz1Label = new JLabel("Platz 1");
		platz1Label.setBounds(320, 141, 102, 24);
		contentPane.add(platz1Label);

		JLabel platz2Label = new JLabel("Platz 2");
		platz2Label.setBounds(220, 189, 85, 24);
		contentPane.add(platz2Label);

		JLabel platz3Label = new JLabel("Platz 3");
		platz3Label.setBounds(438, 212, 102, 24);
		platz3Label.setVisible(false);
		contentPane.add(platz3Label);

		JLabel platz4Label = new JLabel("Platz 4");
		platz4Label.setForeground(Color.WHITE);
		platz4Label.setBounds(248, 391, 70, 24);
		platz4Label.setVisible(false);
		contentPane.add(platz4Label);

		JLabel platz5Label = new JLabel("Platz 5");
		platz5Label.setForeground(Color.WHITE);
		platz5Label.setBounds(248, 440, 70, 24);
		platz5Label.setVisible(false);
		contentPane.add(platz5Label);

		JLabel platz6Label = new JLabel("Platz 6");
		platz6Label.setForeground(Color.WHITE);
		platz6Label.setBounds(248, 489, 70, 24);
		platz6Label.setVisible(false);
		contentPane.add(platz6Label);

		JLabel platz7Label = new JLabel("Platz 7");
		platz7Label.setForeground(Color.WHITE);
		platz7Label.setBounds(438, 393, 70, 24);
		platz7Label.setVisible(false);
		contentPane.add(platz7Label);

		JLabel platz8Label = new JLabel("Platz 8");
		platz8Label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		platz8Label.setForeground(Color.WHITE);
		platz8Label.setBounds(438, 442, 70, 24);
		contentPane.add(platz8Label);

		// Change visibility depending on the number of players and add
		// playername to the labels
		platz1Label.setText(playerList.get(0).getName());
		platz2Label.setText(playerList.get(1).getName());

		int i = playerCount;
		while (i > 2) {
			switch (i) {
			case 3:
				platz3Label.setVisible(true);
				platz3Label.setText(playerList.get(i - 1).getName());
				i = 2;
				break;
			case 4:
				platz4Label.setVisible(true);
				platz4Label.setText(playerList.get(i - 1).getName());
				i = 3;
				break;
			case 5:
				platz5Label.setVisible(true);
				platz5Label.setText(playerList.get(i - 1).getName());
				i = 4;
				break;
			case 6:
				platz6Label.setVisible(true);
				platz6Label.setText(playerList.get(i - 1).getName());
				i = 5;
				break;
			case 7:
				platz7Label.setVisible(true);
				platz7Label.setText(playerList.get(i - 1).getName());
				i = 6;
				break;
			case 8:
				platz8Label.setVisible(true);
				platz8Label.setText(playerList.get(i - 1).getName());
				i = 7;
				break;
			}
		}

		pack();
		setVisible(true);
	}

	/**
	 * Launch the application.
	 */

}
