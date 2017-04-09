import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfugue.player.Player;

public class FunkRunnerWithGUIWOO {

	public static void main(String[] args) {

		MarkovChain chain = new MarkovChain();

		JFrame f = new JFrame("Funk Runner");

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 600);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel label = new JLabel("Enter path here");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(label, gbc);

		TextField in = new TextField(30);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		panel.add(in, gbc);

		JButton enterFile = new JButton("Enter File");
		gbc.gridx = 1;
		gbc.gridy = 1;
		enterFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// String fileName = in.getText();
				// try {
				//
				// Parser parse = new Parser();
				//
				// ArrayList<String> notes = parse.csvNotesBear(fileName);
				// ArrayList<Double> times = parse.csvTimes(fileName);
				//
				//
				// ArrayList<Integer> values = new ArrayList<Integer>();
				// for (int x = 0; x < notes.size(); x++){
				// int i = MarkovChain.tableValue(notes.get(x));
				//
				// values.add(i);
				// }
				//
				// for (int x = 1; x < values.size(); x++) {
				// chain.addNextNote(values.get(x - 1), values.get(x),
				// times.get(x));
				// }
				//
				// in.setText("Successfully Entered File.");
				// } catch (FileNotFoundException e1) {
				// in.setText("Can not enter that File");
				// }

				String fileName = in.getText();

				try {
					Scanner s = new Scanner(new File(fileName));

					in.setText("Added file");

				} catch (FileNotFoundException e1) {
					in.setText("Could not add file");
					e1.printStackTrace();
				}
			}

		});

		panel.add(enterFile, gbc);

		JButton createMarkovSong = new JButton("CREATE MARKOV FILE");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(createMarkovSong, gbc);

		createMarkovSong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// take notes and output MANNN
				// Player player = new Player();
				// String playString = "";
				// for (String i : notes) {
				// playString += i + " ";
				// }

				ArrayList<Character> notes = new ArrayList<Character>();
				for (int x = 0; x < 100; x++) {
					int i = (int) ((71 - 65 + 1) * Math.random() + 65);
					char c = (char) i;
					notes.add(c);
				}

				Player player = new Player();
				String playString = "";

				for (Character c : notes) {
					String str = ""+c;
					int n = (int) (10 * Math.random());
					playString += str + n + " ";
				}
				System.out.println(playString);
				player.play(playString);

			}

		});

		ImageIcon image = new ImageIcon("/Users/TauCeti/Desktop/UMD_Markov_Notes/TheFunkEngineGraphic.jpg");
		JLabel l = new JLabel("", image, JLabel.CENTER);
		JPanel BEAR = new JPanel(new BorderLayout());
		BEAR.add(l, BorderLayout.CENTER );

		f.add(panel, BorderLayout.SOUTH);
		f.add(BEAR, BorderLayout.NORTH);
		
		f.add(panel);

		f.setVisible(true);// TODO Auto-generated method stub

	}

}
