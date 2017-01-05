package org.usfirst.frc.team3309.sensor;


public class MovingAverage extends Filter {

	private boolean REMOVE_OUTLIERS = false;
	private int OUTLIER_PERCENT = 200;
	private double[] WINDOW;
	private boolean FILLED = false;
	private int COUNT = 0;
	private double LAST_AVERAGE = 0;
	private boolean FIRST_RUN = true;
	
	public MovingAverage(int size, boolean removeOutliers)
	{
		if (removeOutliers) REMOVE_OUTLIERS = true;
		WINDOW = new double[size];
	}
	
	public double getMovingAvg(double input)
	{
		double average = input;
		double sum = 0;
		
		if(FIRST_RUN) 
			{
			LAST_AVERAGE = average;
			FIRST_RUN = false;
			}
		
		if(REMOVE_OUTLIERS) //Remove outliers if desired
		{
			if(LAST_AVERAGE != 0  && input>LAST_AVERAGE*(1 + OUTLIER_PERCENT/100)) //Check if the input is greater than a certain percent over the average
				{
				System.out.println("Filter rejected outlier...");
				return LAST_AVERAGE; 
				}
			if(LAST_AVERAGE != 0  && input<LAST_AVERAGE*(1 - OUTLIER_PERCENT/100)) //Check if the input is lesser than a certain percent of the average
				{
				System.out.println("Filter rejected outlier...");
				return LAST_AVERAGE;
				}
		}
		
		WINDOW[COUNT] = input; //Put the input into the window array

		if(FILLED) //If the array is filled, sum the entire array
		{
			for(int i=0;i<WINDOW.length;i++)
			{
				sum = sum + WINDOW[i];
			}
			average = sum/WINDOW.length;
		}else
			{
				//Only count to the number of values in the array if it's unfilled
				for(int i=0;i<COUNT+1;i++)
				{
					sum = sum + WINDOW[i];
					
				}
				average = sum/(COUNT+1);
			}
		
		COUNT ++; //Increment the count
		
		if(COUNT==WINDOW.length) //Reset the count after it's reached the end of the array
			{
			COUNT = 0;
			FILLED = true;
			}
		
		LAST_AVERAGE = average;
		return average;
	}
	
	public void setOutlier(int percent)
	{
		OUTLIER_PERCENT = percent;
	}
}
