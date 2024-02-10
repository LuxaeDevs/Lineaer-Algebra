package mainGUI;

import java.awt.Dimension;
import java.awt.Font;

import functionSystem.ComparisonMode;
import functionSystem.RationalNumber;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
// The helper class is not related to any class, it's just a class where I can use code that 
public class HelperClass {
	public RationalNumber[][] getExtendedMatrix(LinearSystem equationSystem[], int size)
	{
		RationalNumber extendedMatrix[][] = new RationalNumber[size][size+1];
		for(int i = 0; i<size;i++)
		{
			RationalNumber temp[] = equationSystem[i].getExtendedVector();
			for(int j = 0; j<temp.length; j++)
			{
				extendedMatrix[i][j] = temp[j];
			}
		}
		return extendedMatrix;
	}
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	public void displayResult(RationalNumber[] resArray, int size, JPanel parentPanel, String variableString, boolean canShowResult)
	{
		if(!canShowResult)
			return;
		parentPanel.removeAll();
		Dimension sizeDimension = new Dimension(parentPanel.size().width, parentPanel.size().height/size);
		for(int i = 0; i<size; i++)
		{
			String toDisplay = variableString.charAt(i) + " = " + resArray[i].toString();
			JLabel tempLabel = new JLabel(toDisplay);
			tempLabel.setFont(new Font("Tahoma", Font.PLAIN, sizeDimension.height/4));
			tempLabel.setVerticalAlignment(SwingConstants.CENTER);
			parentPanel.add(tempLabel);
		}
		parentPanel.revalidate();
		parentPanel.repaint();
	}
	
	static <T> void reverse(T arr[])
	{
		for(int i = 0; i<arr.length; i++)
		{
			T tempT = arr[i];
			arr[i] = arr[arr.length - 1 - i];
			arr[arr.length - 1 - i] = tempT;
		}
		
	}
	static void printMatrix(RationalNumber extendedMatrix[][])
	{
		for(int i = 0; i<extendedMatrix.length; i++)
		{
			for(int j = 0; j<extendedMatrix[i].length; j++)
			{
				System.out.print(extendedMatrix[i][j].toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	// i ripped it from google lmao
	public RationalNumber[] gaussianMethod(RationalNumber extendedMatrix[][], int size)
	{
		RationalNumber res[] = new RationalNumber[size];
		
		for(int i = 0; i<size - 1; i++)
		{
			for(int j = i + 1; j<size; j++)
			{
				
				if( extendedMatrix[i][j].absolute().compareTo(extendedMatrix[j][i].absolute(), ComparisonMode.GREATER_THAN_OR_EQUAL))
				{
					continue;
				}
				for(int k = 0; k<=size; k++)
				{
					RationalNumber temp = extendedMatrix[i][k];
					extendedMatrix[i][k] = extendedMatrix[j][k];
					extendedMatrix[j][k] = temp;
				}

				
			}
			
		}
		for(int i = 0; i<size - 1; i++)
		{
			for(int j = i + 1; j<size; j++)
			{
				RationalNumber scale = extendedMatrix[j][i].divide(extendedMatrix[i][i]);
				
				for(int k = 0; k<=size; k++)
				{
					extendedMatrix[j][k] = extendedMatrix[j][k].subtract(scale.multiply(extendedMatrix[i][k]));
				}

			}
		}
		printMatrix(extendedMatrix);
	    for(int i=size-1;i>=0;i--)          
	    {                     
	        res[i]=extendedMatrix[i][size];
	                    
	        for(int j=i+1;j<size;j++)
	        {
	          if(i!=j)
	          {
	        	  System.out.println(res[i].toString() + '-'+ extendedMatrix[i][j].multiply(res[j]));
	        	  res[i].printlnNumber();
	        	  res[i] = res[i].subtract(extendedMatrix[i][j].multiply(res[j]));
	        	  extendedMatrix[i][j].multiply(res[j]).printlnNumber();
	        	  //System.out.println(extendedMatrix[i][j].toString() + " " + res[i].toString() + ' ' + res[j]);
	              
	          }          
	        }
	        //printMatrix(extendedMatrix);
	        
	        res[i] = res[i].divide(extendedMatrix[i][i]);
	         
	    }
	    reverse(res);
		return res;
	}

}
