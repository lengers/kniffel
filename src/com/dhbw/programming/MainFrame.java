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
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import com.dhbw.programming.modell.GameFrame;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();

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
		setBounds(100, 100, 401, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("Start");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameFrame.main(null);
			}
		});
		btnNewButton_1.setBounds(255, 96, 117, 25);
		contentPane.add(btnNewButton_1);

		JLabel lblIWantTo = new JLabel("I want to start a new game with ");
		lblIWantTo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIWantTo.setBounds(12, 12, 256, 15);
		contentPane.add(lblIWantTo);

		JSpinner playerSpinner = new JSpinner();
		playerSpinner.setFont(new Font("Dialog", Font.BOLD, 14));
		playerSpinner.setModel(new SpinnerNumberModel(2, 2, 8, 1));
		playerSpinner.setBounds(274, 7, 38, 25);
		contentPane.add(playerSpinner);

		JLabel lblPlayers = new JLabel("players.");
		lblPlayers.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPlayers.setBounds(319, 12, 70, 15);
		contentPane.add(lblPlayers);

		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setAction(action);
		chckbxNewCheckBox.setBounds(35, 35, 28, 23);
		contentPane.add(chckbxNewCheckBox);

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

		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAbout.setBounds(12, 96, 117, 25);
		contentPane.add(btnAbout);
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
