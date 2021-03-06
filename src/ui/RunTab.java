package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import generic.Files;
import generic.Functions;
import generic.Problem;
import generic.Variable;
import generic.Xml;
import jMetal.OptimizationProcess;

// adicionar sitio para meter jars

/**
 * RunTab is the class that creates a tab to the frame that will allow the user
 * to load a jar file or to load a xml file, that will run the algorithm to
 * resolve the problem. It also allows the user to save a configuration to use
 * in the future
 * 
 * @author Nuno Fialho EIC1 72910
 * @author Sandro Ferreira EIC1 72911
 * @author Duarte Pinto EIC1 73117
 */

public class RunTab {

	private static JPanel run;
	public static Problem prob;
	public static JLabel lblJarName;
	private JTextField path;
	private static String jarPath;
	public static JLabel running;
	private static JComboBox binaryAlgorithms;
	private static String[] algorithm = { "" };
	static String[] AlgorithsForDoubleProblemType = new String[] { "NSGAII", "SMSEMOA", "GDE3", "IBEA", "MOCell",
			"MOEAD", "PAES", "RandomSearch" };
	static String[] AlgorithsForIntegerProblemType = new String[] { "NSGAII", "SMSEMOA", "MOCell", "PAES",
			"RandomSearch" };
	static String[] AlgorithsForBinaryProblemType = new String[] { "NSGAII", "SMSEMOA", "MOCell", "MOCH", "PAES",
			"RandomSearch", "SPEA2" };
	private Xml a;
	private static ArrayList<Variable> probVariables;

	/**
	 * Constructor of the class RunTab
	 * 
	 * @param frame
	 *            where the tab will be implemented
	 * @param gui
	 *            where the tab will be implemented
	 */
	public RunTab(JFrame frame, Gui gui) {
		run = new JPanel();
		run.setLayout(null);

		JButton btnSaveConfiguration = new JButton("Save configuration");
		btnSaveConfiguration.setBounds(133, 416, 144, 49);
		run.add(btnSaveConfiguration);
		btnSaveConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(AdvancedConfigurationTab.getProblem().getName());
				a.saveConfig(AdvancedConfigurationTab.getProblem(), gui.getAdm().getXmlDir());
			}
		});

		JButton btnLoadConfiguration = new JButton("Load");
		btnLoadConfiguration.setBounds(380, 76, 137, 22);
		run.add(btnLoadConfiguration);
		btnLoadConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(run);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
					a.loadConfig(chooser.getSelectedFile());
				}
			}
		});

		JLabel lblXmlPath = new JLabel("Load configuration from a previously saved XML file:");
		lblXmlPath.setBounds(80, 80, 300, 16);
		run.add(lblXmlPath);

		JLabel lblPath = new JLabel("Upload Jar file:");
		lblPath.setBounds(80, 131, 200, 16);
		run.add(lblPath);

		JButton btnLoadJar = new JButton("Load");
		btnLoadJar.setBounds(180, 131, 80, 16);
		run.add(btnLoadJar);

		btnLoadJar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Jar files", "jar");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(run);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					lblJarName.setText(chooser.getSelectedFile().getName());
					jarPath = chooser.getSelectedFile().getPath();
					// running.setText("");
				}
			}
		});

		running = new JLabel();
		running.setBounds(133, 300, 300, 50);
		run.add(running);
		running.setText("JMetal is currently running");
		running.setVisible(false);

		JButton btnRun = new JButton("Run");
		btnRun.setBounds(400, 415, 145, 50);
		run.add(btnRun);

		lblJarName = new JLabel("");
		lblJarName.setBounds(275, 130, 270, 15);
		run.add(lblJarName);

		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				running.setVisible(true);
				OptimizationProcess oP = new OptimizationProcess(AdvancedConfigurationTab.getProblem());
				
					oP.run();
					
				Files.loadR(Functions.fileType());
				Files.loadTex(Functions.fileType());
				running.setText("JMetal finished, you can view results");
			}
		});
	}

	public static String getJarName() {
		return lblJarName.getText();
	}

	public JPanel getRun() {
		return run;
	}

	public static void setJar(String jarpath) {
		lblJarName.setText(jarpath);
		jarPath = jarpath;
		// running.setText("");
	}

	public static String getJarPath() {
		return jarPath;
	}

	public static String getAlg() {
		return (String) binaryAlgorithms.getModel().getSelectedItem();
	}
}
