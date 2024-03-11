package view;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class MainFrame extends JFrame{
	private HashMap<String, JButton> btns;
	private JLabel op1 = new JLabel("op1");
	private JLabel op2 = new JLabel("op2");
	private JLabel operator = new JLabel("operator");
	private JMenuBar menuBar;
	private JMenu initmenu;
	final public String[] symbol4btns = {
			"1", "2", "3", "+",
			"4", "5", "6", "-",
			"7", "8", "9", "*",
			"0", ".", "=", "/"
	};
	
	public MainFrame() {		
		JPanel contentPane = new JPanel(new BorderLayout());
		
		JPanel displayPane = new JPanel(new GridLayout(3, 1));
		displayPane.add(op1);
		displayPane.add(operator);
		displayPane.add(op2);		
		
		JPanel controlPane = new JPanel(new GridLayout(4, 4));
		btns = new HashMap<>();
		
		
		
		for(int i=0; i<symbol4btns.length; ++i)
		{
			String symbol = symbol4btns[i];
			JButton btn = new JButton(symbol);
			btns.put(symbol, btn);
			controlPane.add(btn);
		}

		contentPane.add(displayPane, BorderLayout.NORTH);
		contentPane.add(controlPane, BorderLayout.CENTER);
		setContentPane(contentPane);
		
		menuBar = new JMenuBar();
		initmenu = new JMenu("Init");
		menuBar.add(initmenu);
		
		this.setJMenuBar(menuBar);
		
		
		setSize(300, 300);
		setVisible(true);
		
	}
	
	public JButton getButton(String symbol)
	{
		return btns.get(symbol);		
	}
	public JLabel getOP1() {
		return op1;
	}
	public JLabel getOP2() {
		return op2;
	}
	public String getOperator() {
		return operator.getText();
	}
	public void setOperator(String operator) {
		this.operator.setText(operator);		
	}
	public JMenu getMenuInit() {
		return initmenu;
	}
	
	
	public static void main(String [] args) {
		new MainFrame();
	}

}
