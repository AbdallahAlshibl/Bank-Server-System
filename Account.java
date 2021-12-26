public class Account {
		private int accountID;
		private String   name;
		private double  balance;
	
		Account (int accountID, String name, double balance) throws IllegalArgumentException {
			if (balance < 0)
				throw new IllegalArgumentException("Error: Balance is less than zero.");
			else {
			this.accountID = accountID;
			this.name = name;
			this.balance = balance;
			}
		}
		Account(){	
		}
		public double getBalance(  ) {
			return balance;
		}
		public int getAccountID( ) {
			return accountID;
		}
		public String getName( ) {
			return name;
		}
		public void SetAccountID(int AccountID) {
			this.accountID = AccountID;
		}
		public void deposit(double  amount) throws IllegalArgumentException {
			if (amount < 0)
				throw new IllegalArgumentException("\nError: Invalid deposit amount");
			else {
				balance = balance + amount;	
			}
		}
		public void withdraw(double   amount) throws IllegalArgumentException {
			if (amount < 0)
				throw new IllegalArgumentException("\nError: Invalid withdraw amount");
			else {
				balance = balance - amount;		
			}
		}
		public void transfer(double  amount, Account  destinationAccount) throws IllegalArgumentException{
			if (amount < 0)
				throw new IllegalArgumentException("\nError: Invalid transfer amount");
			else {
				balance = balance - amount;
				destinationAccount.balance += amount;
			}
		}
		public String toString( ) {
			return String.format("%9d\t%s\t%16.2f\n", accountID, name, balance);
		}
		public boolean equals(Object obj){
	    	if(obj == null)
	    		return false;
	    	else if(this.getClass() != obj.getClass())
	    		return false;
	    	else{
	    		Account TheAccount = (Account)obj;
	    		return this.accountID == TheAccount.accountID;
	    	}	    
	    }
}