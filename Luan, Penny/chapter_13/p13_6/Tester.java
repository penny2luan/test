package p13_6;

public class Tester {

	public static void main(String[] args) {
		Worker hourly = new HourlyWorker("Bob", 1);
		Worker salaried = new SalariedWorker("Steve", 1);
		
		System.out.println(hourly.computePay(10)); //should print 10
		System.out.println(salaried.computePay(10)); //should print 40
		System.out.println();
		System.out.println(hourly.computePay(50)); //should print 55
		System.out.println(salaried.computePay(50)); //should print 40

	}

}
