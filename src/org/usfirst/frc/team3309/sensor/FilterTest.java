package org.usfirst.frc.team3309.sensor;

//Quick class 

public class FilterTest {
	
	public static void main(String[] args)
	{
	MovingAverage myFilter = new MovingAverage(10, true);
	
	double output = 0;
	for(int i=1;i<101;i++)
	{
		System.out.println("Input: " + i);
		output = myFilter.getMovingAvg(i);
		
		System.out.println("Final Average: " + output);
		System.out.println("");
	}
	
		
	}

}
