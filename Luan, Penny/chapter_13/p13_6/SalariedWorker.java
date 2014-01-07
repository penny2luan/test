package p13_6;

public class SalariedWorker extends Worker{
	
	public SalariedWorker(String name, int rate)
	{
		super(name, rate);
	}
	
	public double computePay(int hours)
	{
		return super.computePay(maxHours);
	}

}
