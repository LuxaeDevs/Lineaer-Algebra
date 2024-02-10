package mainGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import functionSystem.RationalNumber;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.temporal.Temporal;
import java.util.HashMap;

public class MainClass extends JFrame {
	public static LinearSystem equationSystem[];
	private static final long serialVersionUID = 1L;
	static JComboBox comboBox;
	private JPanel contentPane;
	public static MainClass frame;
	public static String variableString = "xyzab";
	public static JPanel variablePanel;
	public static boolean showResult = false;
	static HelperClass helperClass = new HelperClass();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainClass();
					frame.setVisible(true);
					
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void createRandomEquation(int size, LinearSystem equations[], JPanel resultPanel)
	{
		int temp[] = new int[size];
		for(int i = 0; i<size; i++)
		{
			Integer valueInteger = helperClass.getRandomNumber(-10, 10);
			temp[i] = valueInteger;
		}

		for(LinearSystem equation : equations)
		{
			equation.randomEquation(temp, frame);
		}
	
		helperClass.displayResult(calculateResult(size), size, resultPanel, variableString, showResult);
	}
	public static RationalNumber[] calculateResult(int size, LinearSystem equationSystem[])
	{
		return helperClass.gaussianMethod(helperClass.getExtendedMatrix(equationSystem, size), size);
	}
	public static void inputKeyEvent(KeyEvent e, int size, JPanel resultPanel, JTextField inputText) {
		
		int a = e.getKeyChar();
		e.consume();
		String temp;
		if((a < (int)'0' || a > '9'))
		{
			temp = inputText.getText();
			if((a != 8 && a != 127))
				return;
			if(temp.isEmpty())
			{
				

				helperClass.displayResult(calculateResult(size, equationSystem), size, 
						resultPanel, variableString, showResult);

				return;
			}
			char temp2 = temp.charAt(temp.length() - 1);
			temp = temp.substring(0, temp.length() - 1);
			inputText.setText(temp);

			helperClass.displayResult(calculateResult(size, equationSystem), size, 
					resultPanel, variableString, showResult);
			inputText.setText(temp + temp2);
			return;
		}
		temp = inputText.getText() + (char)a;
		inputText.setText(temp);
		if(!showResult)
		{	
			return;
		}
		helperClass.displayResult(calculateResult(size, equationSystem), size, 
				resultPanel, variableString, showResult);	
	}
	public static void createLinearSystems(int size, JPanel toPut, JPanel resultPanel)
	{
		equationSystem = null;
		equationSystem = new LinearSystem[size];
		toPut.removeAll();
		
		
		//Dimension sizePanel = new Dimension(toPut.getSize().width, (toPut.getSize().height)/size);
		for(int i = 0; i<size; i++)
		{
			equationSystem[i] = new LinearSystem(size, size, i, toPut, variableString );
			for(int j = 0; j<equationSystem[i].variables.length; j++)
			{
				if(i == j)
				{
					equationSystem[i].variables[j].value.setText("1");
				}
				else 
				{
					equationSystem[i].variables[j].value.setText("0");
				}
			}
			
		}
		for(int i = 0; i< size; i++)
		{
			for(int j = 0 ; j<equationSystem[i].variables.length; j++)
			{
				
				JTextField insertTextField = equationSystem[i].variables[j].value;
				insertTextField.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						inputKeyEvent(e, size, resultPanel, insertTextField);
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				});			
			}
		}
		if(showResult)
		{ ;
			helperClass.displayResult(calculateResult(size), size, resultPanel, variableString, showResult);
		}
		
	}


	
	public static RationalNumber[] calculateResult(int size)
	{
		
		
		return helperClass.gaussianMethod(helperClass.getExtendedMatrix(equationSystem, size), size);
	}
	
	public static void createSystemOption()
	{
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		String tempString[] = new String[variableString.length() -1];
		for(int i = 1; i<variableString.length(); i++)
		{
			tempString[i-1] = String.valueOf(i + 1);
		}
		comboBox.setModel(new DefaultComboBoxModel(tempString));
		comboBox.setBounds(306, 64, 56, 48);
	}

	/**
	 * Create the frame.
	 */
	public void toggleResult(JButton solveButton, JPanel resultPanel, int size)
	{
		showResult = !showResult;
		int temp = (showResult == false)? 0 : 1;
		String toToggle[] = {"Show Result", "Hide Result"};
		solveButton.setText(toToggle[temp]);
		if(!showResult)
		{
			resultPanel.removeAll();
			
			
			resultPanel.revalidate();
			resultPanel.repaint();
		}
		else 
		{
			
			helperClass.displayResult(calculateResult(size), size, resultPanel, variableString, showResult);
		}
	}
	public MainClass() {
		createSystemOption();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Linear System Solver");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-23, 11, 786, 42);
		contentPane.add(lblNewLabel);
		
		variablePanel = new JPanel();
		variablePanel.setBounds(10, 140, 400, 400);
		contentPane.add(variablePanel);
		
		variablePanel.setLayout(new GridLayout(0, 1, 0, 1));
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBounds(425, 140, 351, 400);
		contentPane.add(resultPanel);
		resultPanel.setLayout(new GridLayout(0, 1, 0, 1));
		

		
		int sizeN = Integer.valueOf(comboBox.getSelectedItem().toString());
		createLinearSystems(sizeN, variablePanel, resultPanel);
		
		comboBox.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				int size = Integer.valueOf(comboBox.getSelectedItem().toString());
				createLinearSystems(size, variablePanel, resultPanel);
				frame.revalidate();
			}
		});
		contentPane.add(comboBox);
		
		JButton btnCreateSystem = new JButton("Create");
		btnCreateSystem.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCreateSystem.setBounds(372, 64, 124, 48);
		contentPane.add(btnCreateSystem);

		
		JLabel lblNewLabel_1 = new JLabel("Variable Amount:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(67, 64, 229, 48);
		contentPane.add(lblNewLabel_1);
		

		
		JButton btnSolve = new JButton("Show Result");
		btnSolve.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSolve.setBounds(506, 64, 218, 48);
		contentPane.add(btnSolve);

		btnSolve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int size = Integer.valueOf(comboBox.getSelectedItem().toString());
				toggleResult(btnSolve, resultPanel, size);
			}
		});
		btnCreateSystem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int size = Integer.valueOf(comboBox.getSelectedItem().toString());
				 createRandomEquation(size, equationSystem, resultPanel);
				
				
			}
		});
	}
}
