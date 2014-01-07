package p13_8;
/**
   A checking account that charges transaction fees.
*/
public class CheckingAccount extends BankAccount
{  
   /**
      Constructs a checking account with a given balance.
      @param initialBalance the initial balance
   */
   public CheckingAccount(double initialBalance)
   {  
      super(initialBalance);
      transactionCount = 0; 
   }

   public void deposit(double amount) 
   {  
      transactionCount++;
      super.deposit(amount); 
   }
   
   public void withdraw(double amount) 
   {  
      transactionCount++;
      super.withdraw(amount); 
   }

   public double endOfMonth()
   {
	      if (transactionCount > FREE_TRANSACTIONS)
	      {  
	         double fees = TRANSACTION_FEE *
	               (transactionCount - FREE_TRANSACTIONS);
	         super.withdraw(fees);
	      }
	      transactionCount = 0;
	      
	      return super.getBalance();
   }

   private int transactionCount;

   private static final int FREE_TRANSACTIONS = 3;
   private static final double TRANSACTION_FEE = 2.0;

}
