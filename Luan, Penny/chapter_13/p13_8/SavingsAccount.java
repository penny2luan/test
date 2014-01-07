package p13_8;
/**
   An account that earns interest at a fixed rate.
*/
public class SavingsAccount extends BankAccount
{  
   /**
      Constructs a bank account with a given interest rate.
      @param rate the interest rate
   */
   public SavingsAccount(double rate) 
   {  
      interestRate = rate;
   }

   public double endOfMonth()
   {
	      double interest = getBalance() * interestRate / 100;
	      deposit(interest);
	      
	      return super.getBalance();
   }

   private double interestRate;
}
