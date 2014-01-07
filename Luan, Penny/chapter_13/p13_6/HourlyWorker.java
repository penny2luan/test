package p13_6;

public class HourlyWorker extends Worker{
	
	public HourlyWorker(String name, int rate)
	{
		super(name, rate);
	}
	
	public double computePay(int hours)
	{
		if(hours > maxHours)
			return super.computePay(maxHours) + (super.computePay(hours - maxHours) * 1.5);
		else
			return super.computePay(hours);
	}

}
