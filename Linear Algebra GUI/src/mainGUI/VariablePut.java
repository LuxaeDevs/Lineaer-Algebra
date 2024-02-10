package mainGUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.management.MalformedObjectNameException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class VariablePut {
	public JTextField value;
	JLabel varName;
	static HelperClass helperClass = new HelperClass();
	void createValueBar(JPanel parentPanel, int textSize, Dimension areaSize)
	{
		value = new JTextField();
		value.setFont(new Font("Tahoma", Font.PLAIN, textSize/4));
		value.setPreferredSize(areaSize);
		//value.setOpaque(false);
		value.setTransferHandler(null);
		value.setHorizontalAlignment(SwingConstants.CENTER);
		parentPanel.add(value);
	}
	void createEqualsBar(JPanel parentPanel, int textSize, Dimension areaSize ,String putString)
	{
		
		varName = new JLabel(putString);
		varName.setPreferredSize(areaSize);
		varName.setFont(new Font("Tahoma", Font.PLAIN, textSize/4));
		varName.setVerticalAlignment(SwingConstants.CENTER);
		varName.setHorizontalAlignment(SwingConstants.CENTER);
		parentPanel.add(varName);
	}
	
	VariablePut(JPanel parentPanel, int size, String putString, boolean atEnd) {
		Dimension areaSize = new Dimension(size, size);
		if(atEnd)
		{
			createEqualsBar(parentPanel, size, areaSize, putString);
			createValueBar(parentPanel, size, areaSize);
		}
		else 
		{
			createValueBar(parentPanel, size, areaSize);
			createEqualsBar(parentPanel, size, areaSize, putString);
		}
	}
	public void modifyVariableValue(String val)
	{
		value.setText(val);
		
	}
	public String fetchText()
	{
		return value.getText();
	}

	

}
