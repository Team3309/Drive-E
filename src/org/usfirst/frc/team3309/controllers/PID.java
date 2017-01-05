package org.usfirst.frc.team3309.controllers;

import org.usfirst.frc.team3309.robot.Dashboard;

public class PID {
	
	private double P_GAIN = 0;
	private double I_GAIN = 0;
	private double D_GAIN = 0;
	
	private double P_TERM_LIMIT = 0;
	private double I_TERM_LIMIT = 0;
	private double D_TERM_LIMIT = 0;
	
	private boolean LIMIT_P = false;
	private boolean LIMIT_I = false;
	private boolean LIMIT_D = false;
	
	private double I_ERROR = 0;
	private double LAST_ERROR = 0;
	
	private double OUTPUT_LIMIT = 1;
	private boolean LIMIT_OUTPUT = false;
	
	private double INPUT_SCALE = 1;
	
	//This constructor creates a PID controller that will calculate the integral and derivative given a single feedback source
	public double PID_Control (double input, double target)
	{
		double output = 0;

		input = input * INPUT_SCALE;
		
		double error= target-input;
		
		I_ERROR = I_ERROR + error;
		
		double pTerm = P_GAIN * error;
		double iTerm = I_GAIN * I_ERROR;
		double dTerm = D_GAIN * (error - LAST_ERROR);
		
		
		if(LIMIT_I)
		{
			if(iTerm>I_TERM_LIMIT) 
				{
				iTerm = I_TERM_LIMIT; //Set term to limit
				I_ERROR = I_TERM_LIMIT/I_GAIN; //Set Integral to limit
				}
			if(iTerm<-I_TERM_LIMIT) 
				{
				iTerm = -I_TERM_LIMIT;
				I_ERROR = iTerm/I_GAIN;
				}
		}
		
		output = pTerm + iTerm + dTerm;
		
		if(LIMIT_OUTPUT)
		{
			if(output>OUTPUT_LIMIT) output = OUTPUT_LIMIT;
			if(output<-OUTPUT_LIMIT) output = -OUTPUT_LIMIT;
		}
		
		LAST_ERROR = error;
		
		
		return output;
	}
	
	//Calculates the control signal based on an input and an integrated 
	public double PID_Control (double input, double integral, double target)
	{
		double output = 0;

		input = input * INPUT_SCALE;
		
		double error= target-input;
		
		I_ERROR = I_ERROR + error;
		
		double pTerm = P_GAIN * error;
		double iTerm = I_GAIN * I_ERROR;
		double dTerm = D_GAIN * (LAST_ERROR - error);
		
		
		if(LIMIT_I)
		{
			if(iTerm>I_TERM_LIMIT) 
				{
				iTerm = I_TERM_LIMIT; //Set term to limit
				I_ERROR = I_TERM_LIMIT/I_GAIN; //Set Integral to limit
				}
			if(iTerm<-I_TERM_LIMIT) 
				{
				iTerm = -I_TERM_LIMIT;
				I_ERROR = iTerm/I_GAIN;
				}
		}
		
		output = pTerm + iTerm + dTerm;
		
		if(LIMIT_OUTPUT)
		{
			if(output>OUTPUT_LIMIT) output = OUTPUT_LIMIT;
			if(output<-OUTPUT_LIMIT) output = -OUTPUT_LIMIT;
		}
		
		LAST_ERROR = error;
		
		
		return output;
	}
	
	//This constructor creates a PID controller that will calculate the derivative signal and 
	//Inputs: input signal, integral signal
	public double PID (double input, double integralInput, double target)
	{
		double output = 0;
		
		return output;
	}
	
	public double PID (double input, double integralInput, double derivativeInput, double target)
	{
		double output = 0;
		
		return output;
	}
	
	public void setPgain(double gain)
	{
		P_GAIN = gain;
		
	}
	
	public void setIgain(double gain)
	{
		I_GAIN = gain;
		
	}
	
	public void setDgain(double gain)
	{
		D_GAIN = gain;
		
	}
	
	public void setOutputLimit(double limit)
	{
		OUTPUT_LIMIT = limit;
		LIMIT_OUTPUT = true;
	}
	
	public void setItermLimit (double limit)
	{
		I_TERM_LIMIT = limit;
		LIMIT_I = true;
	}
	
	public void clearOutputLimit()
	{
		OUTPUT_LIMIT = 0;
		LIMIT_OUTPUT = false;
	}
	
	public void clearItermLimit()
	{
		I_TERM_LIMIT =0;
		LIMIT_I = false;
	}
	
	public void setInputScale(double scaler)
	{
		INPUT_SCALE = scaler;
	}
	
	public void clearHistory()
	{
		I_ERROR = 0;
		LAST_ERROR = 0;
	}
}
