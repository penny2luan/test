package p13_6;

public class Worker {
	
	public String Name;
	public int Rate;
	public int maxHours = 40;
	
	public Worker(String name, int salaryRate)
	{
		Name = name;
		Rate = salaryRate;
	}
	
	public double computePay(int hours)
	{
		return hours * Rate;
	}

}
