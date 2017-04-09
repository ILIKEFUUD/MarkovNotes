
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfugue.player.Player;

public class FunkRunnerWithGUIWow {

	public static void main(String[] args) {

		MarkovChain chain = new MarkovChain();

		JFrame f = new JFrame("Funk Runner");
		f.setLayout(new BorderLayout());

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
					ArrayList<String> notes = Parser.csvNotesBear(fileName);
					ArrayList<Double> times = Parser.csvTimes(fileName);

					ArrayList<Integer> integerValueOfNotes = new ArrayList<Integer>();

					for (String str : notes) {
						int i = MarkovChain.tableValue(str);
						integerValueOfNotes.add(i);
					}

					int precedingValue = integerValueOfNotes.get(0);
					for (int x = 1; x < integerValueOfNotes.size() - 1; x++) {
						chain.addNextNote(precedingValue, integerValueOfNotes.get(x), times.get(x));
						precedingValue = integerValueOfNotes.get(x);
					}

					in.setText("Added file");

				} catch (Exception e1) {
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
				
				double averageTime = 0;
				
				ArrayList<Double> song = chain.getValuesOfSong(200);

				ArrayList<Integer> values = new ArrayList<Integer>();
				ArrayList<Double> times = new ArrayList<Double>();

				for (int x = 0; x < song.size(); x++) {
					if (x % 2 != 0)
						times.add(song.get(x));
					else {
						double d = song.get(x);
						int add = (int) d;
						values.add(add);
					}
				}

				System.out.println(times);

				ArrayList<String> notes = new ArrayList<String>();

				// bear
				int[][] moo = MarkovChain.table;
				int noteNum = -1;
				int octaveNum = -1;
				String noteString = "";
				for (int i : values) {
					for (int j = 0; j < 10; j++) {
						for (int k = 0; k < 12; k++) {
							if (moo[j][k] == i) {
								noteNum = k;
								octaveNum = j + 1;
							}
						}
					}

					switch (noteNum) {
					case 1:
						noteString = "C";
						break;
					case 2:
						noteString = "Db";
						break;
					case 3:
						noteString = "D";
						break;
					case 4:
						noteString = "Eb";
						break;
					case 5:
						noteString = "E";
						break;
					case 6:
						noteString = "F";
						break;
					case 7:
						noteString = "Gb";
						break;
					case 8:
						noteString = "G";
						break;
					case 9:
						noteString = "Ab";
						break;
					case 10:
						noteString = "A";
						break;
					case 11:
						noteString = "Bb";
						break;
					default:
						noteString = "B";
						break;
					}

					notes.add(noteString + octaveNum);
				}

				// take notes and output MANNN
				Player player = new Player();
				String playString = "";
				// for (String i : notes) {
				// playString += i + " ";
				// }

				for (int x = 0; x < notes.size(); x++) {
					if (times.get(x) == .25) {
						playString += notes.get(x) + "q ";
					} else if (times.get(x) == 1) {
						playString += notes.get(x) + "w ";
					} else if (times.get(x) == 0.5) {
						playString += notes.get(x) + "h ";
					} else if (times.get(x) == 0) {
						playString += notes.get(x) +"i ";
					} else {
						playString += "R ";
					}
					
					averageTime+=times.get(x);
				}
				
				averageTime = Math.abs(averageTime/times.size());
				
				System.out.println(averageTime);
				int tempo = (int) (9.3/averageTime);
				playString = "T"+tempo+" "+ playString;
				
				player.play(playString);

			}

		});
		
		ImageIcon image = new ImageIcon("/Users/TauCeti/Desktop/UMD_Markov_Notes/TheFunkEngineGraphic.jpg");
		JLabel l = new JLabel("", image, JLabel.CENTER);
		JPanel BEAR = new JPanel(new BorderLayout());
		BEAR.add(l, BorderLayout.CENTER );

		f.add(panel, BorderLayout.SOUTH);
		f.add(BEAR, BorderLayout.NORTH);

		f.setVisible(true);
	}

}
