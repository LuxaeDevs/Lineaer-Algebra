package mainGUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.HashMap;

import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import functionSystem.RationalNumber;



public class LinearSystem {
	public VariablePut variables[];
	// public JTextField equalsTo;
	HelperClass helper = new HelperClass();
	public  JPanel currentPanel;
	LinearSystem(int row, int column, int index, JPanel parentPanel, String variableString) {
		variables = new VariablePut[column + 1];
		
		JPanel rowPanel = new JPanel();
		Dimension sizePanel = new Dimension(parentPanel.getSize().width, 
				(parentPanel.getSize().height/row));
		rowPanel.setPreferredSize(sizePanel);
		rowPanel.setLayout(new GridLayout(1, 0, 1, 0));
		
		
		  if(index%2==0)
                rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
		  
		for(int i = 0; i<column + 1; i++)
		{
			String toPutString = (i != column)? (String.valueOf(variableString.charAt(i)) + "+") 
					: "=";
			variables[i] = new VariablePut(rowPanel, sizePanel.height,toPutString, (i == column));
		}
		parentPanel.add(rowPanel);
		
	}

	public void randomEquation(int variableValue[], MainClass frame)
	{
		int res = 0;
		for(int i = 0; i<variables.length - 1; i++)
		{
			int temp =	helper.getRandomNumber(-10, 10);
			variables[i].modifyVariableValue(String.valueOf(temp));
			res += temp * variableValue[i];
		}
		variables[variables.length - 1].modifyVariableValue(String.valueOf(res));
		
	}
	static boolean isInvalid(String input)
	{
		return input.isEmpty() || (input.length() == 1 && input.charAt(0) == '-');
	}
	public RationalNumber[] getExtendedVector()
	{
		RationalNumber temp[] = new RationalNumber[variables.length];
		for(int i = 0;i<variables.length; i++)
		{
			String owo = variables[i].fetchText();
			temp[i] = (isInvalid(owo)) ? new RationalNumber(0) : new RationalNumber(Double.valueOf(owo));
		}
		return temp;
	}
	
	
	
}
