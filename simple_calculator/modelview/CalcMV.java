package modelview;

import view.MainFrame;
import javax.swing.*;

import model.CalculatorModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CalcMV {
	private MainFrame frame;
	private JLabel controlLabel;
	private CalculatorModel model;

	
	public CalcMV() {
		frame = new MainFrame();
		model = new CalculatorModel();
		
		init();
		
		//attach listner to buttons
		for (String symbol : frame.symbol4btns) {
			JButton btn = frame.getButton(symbol);
			char csymbol = symbol.charAt(0);
			
			if(48 <= (int)csymbol && (int)csymbol <= 57) // '0' <= csymbol <= '9'
			{
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
//						System.out.println(csymbol);
						controlLabel.setText(controlLabel.getText() + csymbol);					
					}
				});
			}
			else if(csymbol == '=') {
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String result="";
						String op1 = frame.getOP1().getText();
						String op2 = frame.getOP2().getText();
						
						switch(frame.getOperator()) {
						case "+":
							result = model.sum(op1, op2);
							break;
						case "-":
							result = model.sub(op1, op2);
							break;
						case "*":
							result = model.mul(op1, op2);
							break;
						case "/":
							result = model.div(op1, op2);
							break;
						default :
							result = "operator Error";
							break;
						}
						
						frame.getOP1().setText(result);
						frame.getOP2().setText("");
					}
				});

			}
			else if(csymbol == '.') {
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(controlLabel.getText().indexOf('.') == -1) {
							controlLabel.setText(controlLabel.getText() + csymbol);
						}						
					}				
				});
			}				
			else // operators: +, -, *, /
			{
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setOperator(symbol);						
						controlLabel = frame.getOP2();				
					}				
				});
				
			}
		}
		
		//attach listner to menu
		frame.getMenuInit().addMouseListener(new MouseListener() {			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				init();				
				frame.getMenuInit().setSelected(false);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

		
				
			}
		});
		

		
	}
	
	private void init() {
		frame.getOP1().setText("");
		frame.getOP2().setText("");
		controlLabel = frame.getOP1();
	}
	

}
