package ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import generic.Variable;

public class VariableConfigurationTab {
	
	private static JTable table;
	private static JPanel varconfig;
	private DefaultTableModel model;
	private String[] colums = { "Rule", "Weight"};
	private static Gui gui = null;
	private JScrollPane scrollPane;
	private ArrayList<Variable> rules = new ArrayList<Variable>();
	
	
	
	public VariableConfigurationTab(JFrame frame, Gui gui) {
		varconfig = new JPanel();
		varconfig.setLayout(null);
		this.gui = gui;
		
		model = new DefaultTableModel(new Object[335][2],colums) {

//			public boolean isCellEditable(int row, int col) {
//				if (col == 1) { // columnIndex: the column you want to make it
//								// editable
//					return true;
//				} else {
//					return false;
//				}
//			}

		};
		
		 table = new JTable(model);
		 scrollPane = new JScrollPane(table);
		 scrollPane.setBounds(111, 113, 419, 381);
		 varconfig.add(scrollPane);
		
		
		
	}
	
	public static void writeRules(Variable rules) {
		for(int i = 0; i < AdvancedConfigurationTab.getQuantity();i++) {
			((DefaultTableModel) table.getModel()).insertRow(i, rules.getVector());
		}
		varconfig.repaint();
		Gui.repaint();
	}

	public JPanel getVarconfig() {
		return varconfig;
	}
	

	
	
}
