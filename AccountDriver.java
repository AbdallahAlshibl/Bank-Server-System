import java.util.*;
import java.io.*;
public class AccountDriver  { 
	static Account[] AccountsArray = new Account[Size() ];
	public static int Size() {
		int Size = 0;
		try {
		Scanner Input = new Scanner (new FileInputStream("AccountInfo.txt"));
		while(Input.hasNextLine()) {
			 Size++;
			 Input.nextLine();
		}
		Input.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		return Size;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner kb = new Scanner (System.in);
		Scanner Input = new Scanner (new FileInputStream("AccountInfo.txt"));
		Scanner Input2 = new Scanner (new FileInputStream("AccountInfo.txt"));
		int Choice, Index = 0;
		
		int AccountID;
		String FirstName;
		String SecondName;
		String CombainedName;
		double Balance;
		
		while (Input2.hasNextLine()) {
			AccountID = Input2.nextInt();
			FirstName = Input2.next();
			SecondName = Input2.next();
			CombainedName = FirstName + " " + SecondName;
			Balance = Input2.nextDouble();
			AccountsArray[Index] = new Account (AccountID, CombainedName, Balance );
			Index++;
		}
		do {
			System.out.println("\n1. Display Account Info for all Accounts");
			System.out.println("2. Display Account Info for a particular Account");
			System.out.println("3. Withdraw");
			System.out.println("4. Deposit");
			System.out.println("5. Transfer");
			System.out.println("6. Add New Account");
			System.out.println("7. Delete Account");
			System.out.println("8. Exit\n");
			System.out.print("Please select your choice: ");
			Choice = kb.nextInt();
			switch(Choice) {
			case 1 :
				DisplayAllAccountsInfo();
				promptEnterKey();
				break;
			case 2 :
				DisplayParticularAccountInfo();
				promptEnterKey();
				break;
			case 3 :
				Withdraw();
				promptEnterKey();
				break;
			case 4 :
				Deposit();
				promptEnterKey();
				break;
			case 5 :
				Transfer();
				promptEnterKey();
				break;
			case 6 :
				AddNewAccount();
				promptEnterKey();
				break;
			case 7 :
				DeleteAccount();
				promptEnterKey();
				break;
			case 8 :
				System.out.print("\n\tThank you ...");
				System.exit(0);
			default:
				System.out.print("\nError: Invalid choice. Please correct your choice:\n");
			}
		} while(Choice!=8);
	}
	public static void promptEnterKey(){
		   System.out.println("\nPress Enter key to continue . . .");
		   Scanner kb = new Scanner(System.in);
		   kb.nextLine();
	}
	public static void DisplayAllAccountsInfo() {
		System.out.println("\n AccountID\tAccount Owner\tAccount Balance(SAR)");
		for (int i=0; i < AccountsArray.length; i++) {
			System.out.print(AccountsArray[i]);
		}
	}
	public static void DisplayParticularAccountInfo() {
		Scanner kb = new Scanner (System.in);
		System.out.print("Enter accountID: ");
		int AccountID = kb.nextInt();
		
		Account AccountforSearch = new Account();
		AccountforSearch.SetAccountID(AccountID);
		boolean found = false;
		int i;
		for (i=0; i<AccountsArray.length; i++) {
			if(AccountforSearch.equals(AccountsArray[i])) {
				found = true;
				break;
			}
		}
		if (found)
			System.out.printf("\n AccountID: %d\tAccount Owner: %s\tAccount Balance: %,.2f SAR\n\n",AccountsArray[i].getAccountID(),AccountsArray[i].getName(), AccountsArray[i].getBalance() );
		else
			System.out.println("\nError: Invalid account number");
	}
	public static void Withdraw() throws IOException {
		Scanner kb = new Scanner (System.in);
		PrintWriter AccountWriter = new PrintWriter(new FileOutputStream("AccountInfo.txt"),true);
		System.out.print("Please enter accountID: ");
		int AccountID = kb.nextInt();
		System.out.print("Please enter withdraw amount (SAR): ");
		int WithdrawAmount = kb.nextInt();
		
		Account AccountforSearch = new Account();
		AccountforSearch.SetAccountID(AccountID);
		boolean found = false;
		int i;
		for (i=0; i<AccountsArray.length; i++) {
			if(AccountforSearch.equals(AccountsArray[i])) {
				found = true;
				break;
			}
		}
		if (found) {
			if(WithdrawAmount<=0)
				System.out.println("\nError: Invalid withdraw amount");
			else {
				if( WithdrawAmount > AccountsArray[i].getBalance())
					System.out.printf("%nError: Insufficient balance. Balance available is only %,.2f SAR%n", AccountsArray[i].getBalance());
				else {
					System.out.printf("%nBalance before withdrawal : %,.2f Saudi Riyals%n", AccountsArray[i].getBalance());
					System.out.printf("Amount withdrawn from Account: %,d Saudi Riyals%n", WithdrawAmount);
					AccountsArray[i].withdraw(WithdrawAmount);
					for(int x =0 ; x<AccountsArray.length-1; x++) {
						AccountWriter.printf("%d\t%s\t%16.2f%n",AccountsArray[x].getAccountID(), AccountsArray[x].getName(), AccountsArray[x].getBalance());
					}
					AccountWriter.printf("%d\t%s\t%16.2f",AccountsArray[AccountsArray.length-1].getAccountID(), AccountsArray[AccountsArray.length-1].getName(), AccountsArray[AccountsArray.length-1].getBalance());
					AccountWriter.close();
					System.out.printf("New Balance: %,.2f Saudi Riyals%n", AccountsArray[i].getBalance());
				}
			}
		}else
			System.out.println("\nError: Invalid account number");
	}
	public static void Deposit() throws IOException {
		Scanner kb = new Scanner (System.in);
		PrintWriter AccountWriter = new PrintWriter(new FileOutputStream("AccountInfo.txt"),true);
		System.out.print("Please enter accountID: ");
		int AccountID = kb.nextInt();
		System.out.print("Please enter deposit amount (SAR): ");
		int DepositAmount = kb.nextInt();
		
		Account AccountforSearch = new Account();
		AccountforSearch.SetAccountID(AccountID);
		boolean found = false;
		int i;
		for (i=0; i<AccountsArray.length; i++) {
			if(AccountforSearch.equals(AccountsArray[i])) {
				found = true;
				break;
			}
		}
		if (found) {
			if(DepositAmount<=0)
				System.out.println("\nError: Invalid deposit amount");
			else {
				System.out.printf("%nBalance before deposit : %,.2f Saudi Riyals%n", AccountsArray[i].getBalance());
				System.out.printf("Amount deposit to Account: %,d Saudi Riyals%n", DepositAmount);
				AccountsArray[i].deposit(DepositAmount);
				for(int x =0 ; x<AccountsArray.length-1; x++) {
					AccountWriter.printf("%d\t%s\t%16.2f%n",AccountsArray[x].getAccountID(), AccountsArray[x].getName(), AccountsArray[x].getBalance());
				}
				AccountWriter.printf("%d\t%s\t%16.2f",AccountsArray[AccountsArray.length-1].getAccountID(), AccountsArray[AccountsArray.length-1].getName(), AccountsArray[AccountsArray.length-1].getBalance());
				AccountWriter.close();
				System.out.printf("New Balance: %,.2f Saudi Riyals%n", AccountsArray[i].getBalance());
			}
		}else
			System.out.println("\nError: Invalid account number");
	}
	public static void Transfer() throws IOException {
		Scanner kb = new Scanner (System.in);
		PrintWriter AccountWriter = new PrintWriter(new FileOutputStream("AccountInfo.txt"),true);
		System.out.print("Please enter source accountID: ");
		int SourceAccountID = kb.nextInt();
		System.out.print("Please enter destination accountID: ");
		int DestinationAccountID = kb.nextInt();
		System.out.print("Please enter transfer amount (SAR): ");
		int TransferAmount = kb.nextInt();
		
		Account SourceAccountIDforSearch = new Account();
		SourceAccountIDforSearch.SetAccountID(SourceAccountID);
		boolean foundSource = false;
		int i;
		for (i=0; i<AccountsArray.length; i++) {
			if(SourceAccountIDforSearch.equals(AccountsArray[i])) {
				foundSource = true;
				break;
			}
		}
		Account DestinationAccountIDforSearch = new Account();
		DestinationAccountIDforSearch.SetAccountID(DestinationAccountID);
		boolean foundDestination = false;
		int x;
		for (x=0; x<AccountsArray.length; x++) {
			if(DestinationAccountIDforSearch.equals(AccountsArray[x])) {
				foundDestination = true;
				break;
			}
		}
		if (!foundSource) {
			System.out.println("Error: Invalid source account ID");
		}else {
			if (!foundDestination) 
				System.out.println("Error: Invalid destination account ID");
			else {
				if(TransferAmount <= 0)
					System.out.println("\nError: Invalid transfer amount");
				else {
					if(TransferAmount > AccountsArray[i].getBalance())
						System.out.printf("%nError: Insufficient balance. Balance available is only %,.2f SAR%n", AccountsArray[i].getBalance());
					else {
						
						System.out.printf("%nSource balance before transfer : %,.2f Saudi Riyals%n", AccountsArray[i].getBalance());
						System.out.printf("Amount transferred to account %d: %,d Saudi Riyals%n", AccountsArray[x].getAccountID(),TransferAmount);
						AccountsArray[i].transfer(TransferAmount, AccountsArray[x]);
						for(int m =0 ; m<AccountsArray.length-1; m++) {
							AccountWriter.printf("%d\t%s\t%16.2f%n",AccountsArray[m].getAccountID(), AccountsArray[m].getName(), AccountsArray[m].getBalance());
						}
						AccountWriter.printf("%d\t%s\t%16.2f",AccountsArray[AccountsArray.length-1].getAccountID(), AccountsArray[AccountsArray.length-1].getName(), AccountsArray[AccountsArray.length-1].getBalance());
						AccountWriter.close();
						System.out.printf("New source balance: %,.2f Saudi Riyals%n", AccountsArray[i].getBalance());
					}}}}}
	
	public static void AddNewAccount() throws IOException{
		Scanner kb = new Scanner (System.in);
		Scanner Input = new Scanner (new FileInputStream("AccountInfo.txt"));
		PrintWriter AccountWriter = new PrintWriter(new FileOutputStream("AccountInfo.txt", true));
		String Name, FirstName, SecondName, CombainedName;
		double Balance, balance;
		int AccountID, Index = 0;
		System.out.print("Enter accountID: ");
		int ID = kb.nextInt();
		
		Account AccountforSearch = new Account();
		AccountforSearch.SetAccountID(ID);
		boolean found = false;
		int i;
		for (i=0; i<AccountsArray.length; i++) {
			if(AccountforSearch.equals(AccountsArray[i])) {
				found = true;
				break;
			}
		}
		if (found)
			System.out.println("Error: AccountID is already exist");
		else {
			System.out.print("Enter account name:");
			kb.nextLine();
			Name = kb.nextLine();
			System.out.print("Enter Account balance:");
			balance = kb.nextDouble();
			Account NewAccount = new Account(ID, Name, balance);
			Account[] Array = new Account[AccountsArray.length+1]; 
			AccountWriter.printf("%n%d\t%s\t%16.2f",NewAccount.getAccountID(), NewAccount.getName(), NewAccount.getBalance());
			AccountWriter.close();
			
			while (Input.hasNextLine()) {
				AccountID = Input.nextInt();
				FirstName = Input.next();
				SecondName = Input.next();
				CombainedName = FirstName + " " + SecondName;
				Balance = Input.nextDouble();
				Array[Index] = new Account (AccountID, CombainedName, Balance );
				Index++;
			}
			AccountsArray = Array;
			Input.close();
			System.out.println("\nYour account has been created");	
		}	
	}
	public static void DeleteAccount() throws IOException{
		Scanner kb = new Scanner (System.in);
		Scanner Input = new Scanner (new FileInputStream("AccountInfo.txt"));
		PrintWriter AccountWriter = new PrintWriter(new FileOutputStream("AccountInfo.txt"),true);
		String Name,CombainedName,SecondName,FirstName;
		double balance,Balance;
		int Index ;
		int AccountID;
		System.out.print("Enter accountID: ");
		int ID = kb.nextInt();
		
		Account AccountforSearch = new Account();
		AccountforSearch.SetAccountID(ID);
		boolean found = false;
		int i;
		for (i=0; i<AccountsArray.length; i++) {
			if(AccountforSearch.equals(AccountsArray[i])) {
				found = true;
				break;
			}
		}
		if (!found)
			System.out.println("Error: AccountID is not exist");
		else {
			int IndexSearch;
			for( IndexSearch=0 ; IndexSearch<AccountsArray.length ; IndexSearch++) {
				if( ID==(AccountsArray[IndexSearch]).getAccountID())
					break;
			}
			Account[] Array = new Account[AccountsArray.length-1]; 
			Index = 0;
			for(int index = 0; index< (AccountsArray.length-1);) {
				if (Index !=IndexSearch) {
					Array[index] = new Account(AccountsArray[Index].getAccountID(),AccountsArray[Index].getName(),AccountsArray[Index].getBalance());
					index++;
				}
				Index++;
			}
			AccountsArray = Array;
			Input.close();
			for(int x =0 ; x<AccountsArray.length-1; x++) {
				AccountWriter.printf("%d\t%s\t%16.2f%n",AccountsArray[x].getAccountID(), AccountsArray[x].getName(), AccountsArray[x].getBalance());
			}
			AccountWriter.printf("%d\t%s\t%16.2f",AccountsArray[AccountsArray.length-1].getAccountID(), AccountsArray[AccountsArray.length-1].getName(), AccountsArray[AccountsArray.length-1].getBalance());
			AccountWriter.close();
			System.out.println("\nYour account has been deleted ...");
		}
	}
}