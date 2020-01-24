import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldConfidenta;
	private JTextField textFieldSuportMinim;
	private JTextField textFieldNrReguli;
	private JFileChooser fc;
	private File file;
	float confidenta;
	float minSuport;
	int nrReguli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 630);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblApriori = new JLabel("Apriori");
		lblApriori.setForeground(Color.WHITE);
		lblApriori.setFont(new Font("Arial", Font.PLAIN, 34));
		lblApriori.setBounds(352, 13, 115, 41);
		contentPane.add(lblApriori);

		JPanel panelSetari = new JPanel();
		panelSetari.setBackground(new Color(51, 102, 102));
		panelSetari.setBounds(12, 67, 294, 503);
		contentPane.add(panelSetari);
		panelSetari.setLayout(null);

		JLabel lblNewLabel = new JLabel("Confidenta: ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(22, 127, 106, 34);
		panelSetari.add(lblNewLabel);

		JLabel lblConfError = new JLabel("Nr real");
		lblConfError.setFont(new Font("Arial", Font.PLAIN, 12));
		lblConfError.setForeground(Color.WHITE);
		lblConfError.setBounds(154, 180, 101, 22);
		panelSetari.add(lblConfError);

		textFieldConfidenta = new JTextField();
		textFieldConfidenta.setFont(new Font("Arial", Font.PLAIN, 19));
		textFieldConfidenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					confidenta = Float.parseFloat(textFieldConfidenta.getText());
					//lblConfError.setText("");
				} catch (NumberFormatException e) {
					//lblConfError.setText("Numar incorect");
				}
			}
		});
		textFieldConfidenta.setBounds(154, 127, 101, 40);
		textFieldConfidenta.setColumns(10);
		panelSetari.add(textFieldConfidenta);

		JLabel lblSuportMinim = new JLabel("Suport minim:");
		lblSuportMinim.setForeground(Color.WHITE);
		lblSuportMinim.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSuportMinim.setBounds(22, 226, 136, 34);
		panelSetari.add(lblSuportMinim);

		JLabel lblNrReguli = new JLabel("Numar reguli:");
		lblNrReguli.setForeground(Color.WHITE);
		lblNrReguli.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNrReguli.setBounds(22, 326, 120, 34);
		panelSetari.add(lblNrReguli);

		JLabel lblSuportError = new JLabel("Nr intreg");
		lblSuportError.setForeground(Color.WHITE);
		lblSuportError.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSuportError.setBounds(154, 282, 101, 22);
		panelSetari.add(lblSuportError);

		textFieldSuportMinim = new JTextField();
		textFieldSuportMinim.setFont(new Font("Arial", Font.PLAIN, 19));
		textFieldSuportMinim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					minSuport = Integer.parseInt(textFieldSuportMinim.getText());
					//lblSuportError.setText("");
				} catch (NumberFormatException e1) {
					//lblSuportError.setText("Numar incorect");
				}
			}
		});
		textFieldSuportMinim.setColumns(10);
		textFieldSuportMinim.setBounds(154, 226, 101, 40);
		panelSetari.add(textFieldSuportMinim);

		JLabel lblReguliError = new JLabel("Nr intreg");
		lblReguliError.setForeground(Color.WHITE);
		lblReguliError.setFont(new Font("Arial", Font.PLAIN, 12));
		lblReguliError.setBounds(154, 379, 101, 22);
		panelSetari.add(lblReguliError);

		textFieldNrReguli = new JTextField();
		textFieldNrReguli.setFont(new Font("Arial", Font.PLAIN, 19));
		textFieldNrReguli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					nrReguli = Integer.parseInt(textFieldNrReguli.getText());
					//lblReguliError.setText("");
				} catch (NumberFormatException e1) {
					//lblReguliError.setText("Numar incorect");
				}
			}
		});
		textFieldNrReguli.setColumns(10);
		textFieldNrReguli.setBounds(154, 326, 101, 40);
		panelSetari.add(textFieldNrReguli);

		fc = new JFileChooser();
		JButton btnIncarcaFisier = new JButton("Incarca fisier");
		btnIncarcaFisier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int returnVal = fc.showOpenDialog(Home.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					// String cale = fc.get
					System.out.println(file.toString());
				}
			}
		});

		btnIncarcaFisier.setForeground(new Color(0, 0, 0));
		btnIncarcaFisier.setBackground(new Color(255, 255, 255));
		btnIncarcaFisier.setFont(new Font("Arial", Font.PLAIN, 20));
		btnIncarcaFisier.setBounds(22, 33, 153, 46);
		panelSetari.add(btnIncarcaFisier);

		TextArea afis = new TextArea();
		afis.setFont(new Font("Arial", Font.PLAIN, 20));
		afis.setBounds(323, 67, 524, 503);
		contentPane.add(afis);

		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {
					afis.setText("Hello\n");
					Apriori apriori = new Apriori(file);

					int nr = Integer.parseInt(textFieldNrReguli.getText());
					System.out.println("nr reguli " + nr);
					apriori.setNrReguli(nr);

					float aux = Float.parseFloat(textFieldConfidenta.getText());
					System.out.println("conf " + aux);
					apriori.setMinConfidenta(aux);

					int aux2 = Integer.parseInt(textFieldSuportMinim.getText());
					apriori.setMinSuport(aux2);
					System.out.println("min sup " + aux2);
					

					apriori.aprioriRun();
					apriori.generareReguli();
					ArrayList<Itemset> L = apriori.getItemseturiFrecvente();
					ArrayList<Itemset> stangaReguli = apriori.getStangaReguli();
					ArrayList<Itemset> dreaptaReguli = apriori.getDreaptaReguli();
					ArrayList<Double> confReguli = apriori.getConfReguli();

					// itemseturi frecvente
					afis.append("\nLista itemseturi frecvente:\n");
					for (int i = 0; i < L.size(); i++) {
						String[] s = L.get(i).getNume();
						for (int j = 0; j < s.length - 1; j++) {
							afis.append(" " + s[j]);
							afis.append(" ");
						}
						//afis.append(Integer.toString(L.get(i).getCount()));
						afis.append("\n");
					}

					afis.append("\nReguli generate: \n");
					
					for(int i=0; i<stangaReguli.size() && i<apriori.getNrReguli(); i++) {
						afis.append(" "+Integer.toString(i)+".");
						Itemset it1 = stangaReguli.get(i);
						String[] n1 = it1.getNume();

						afis.append(" " + Arrays.toString(n1)); 
						afis.append(" -> ");

						Itemset it2 = dreaptaReguli.get(i);
						String[] n2 = it2.getNume();
						afis.append(Arrays.toString(n2));
						//afis.append("  conf "+confReguli.get(i));
						afis.append(" \n");
						
					}
					
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnStart.setForeground(new Color(0, 0, 0));
		btnStart.setBackground(Color.WHITE);
		btnStart.setFont(new Font("Arial", Font.PLAIN, 20));
		btnStart.setBounds(145, 444, 120, 46);
		panelSetari.add(btnStart);

	}
}
