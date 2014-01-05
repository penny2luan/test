package gregorian;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calender {
	public static void main(String args[])
	{
		//printing today's date
		Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		System.out.println("Today's Date is " + dateFormatter.format(now));

		//printing the date 100 days from today
	GregorianCalendar cal = new GregorianCalendar();
	cal.add(Calendar.DAY_OF_MONTH, 100);
	 int year = cal.get(Calendar.YEAR);
     int month = cal.get(Calendar.MONTH);      // 0 to 11
     int day = cal.get(Calendar.DAY_OF_MONTH);
 	int weekDay = cal.get(Calendar.DAY_OF_WEEK);
 	System.out.printf("100 days from now is ");
 	switch (weekDay)
	{
	case 1:
		System.out.printf("Monday, ");
		break;
	case 2:
		System.out.printf("Tuesday, ");
		break;
	case 3:
		System.out.printf("Wednesday, ");
		break;
	case 4:
		System.out.printf("Thursday, ");
		break;
	case 5:
		System.out.printf("Friday, ");
		break;
	case 6:
		System.out.printf("Saturday, ");
		break;
	case 7:
		System.out.printf("Sunday, ");
		break;
	}
     System.out.printf("%02d/%02d/%4d \n", month+1, day, year);
     
     //printing weekday of my birthday
     GregorianCalendar myBirthday = new GregorianCalendar(1996, Calendar.SEPTEMBER, 26);
 	int weekday = myBirthday.get(Calendar.DAY_OF_WEEK);
 	System.out.printf("I was born on a ");
 	switch (weekday)
 	{
 	case 1:
 		System.out.println("Monday.");
 		break;
 	case 2:
 		System.out.println("Tuesday.");
 		break;
 	case 3:
 		System.out.println("Wednesday.");
 		break;
 	case 4:
 		System.out.println("Thursday.");
 		break;
 	case 5:
 		System.out.println("Friday.");
 		break;
 	case 6:
 		System.out.println("Saturday.");
 		break;
 	case 7:
 		System.out.println("Sunday.");
 	}
     
 	//printing 10,000 days from my birthday
     myBirthday.add(Calendar.DAY_OF_MONTH, 10000);
	 int yearB = myBirthday.get(Calendar.YEAR);
     int monthB = myBirthday.get(Calendar.MONTH);      // 0 to 11
     int dayB = myBirthday.get(Calendar.DAY_OF_MONTH);
 	System.out.printf("10,000 days from my birthday is: ");
     System.out.printf("%02d/%02d/%4d \n", monthB+1, dayB, yearB);

	}
}
