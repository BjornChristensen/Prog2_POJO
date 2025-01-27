package threads.bankappSynchronized;
// BankApp2 - Synchronized.
// Demo af Race conditions
// Bjørn Christensen, 26/1-2025

import java.time.LocalDate;
import java.util.ArrayList;

public class BankApp2Synchronized {
	public static void main(String[] args) throws  InterruptedException {
		Account a1=new Account("Joe Pass", 1.5);
		Customer Tom=new Customer(a1);
		Customer Jerry=new Customer(a1);
		Tom.start();
		Jerry.start();

		// Wait for Tom and Jerry to finish
		Tom.join();
		Jerry.join();;

		a1.printTransactions();
	}
}

class Customer extends Thread {
	Account account;
	Customer(Account account) {
		this.account=account;
	}
	public void run(){
		account.deposit(1000);
		account.withdraw(10);
		account.deposit(1000);
	}
}

class Account {
	int accountNo;
	String owner;
	double balance;
	double interestRate;	// rente i %
	ArrayList<Transaction> transactions=new ArrayList<Transaction>(); 
	private static int noOfAccounts=0;

	Account(String ow, double rate){
		noOfAccounts++;
		accountNo=noOfAccounts;
		owner=ow;
		interestRate=rate;
		balance=0;
	}

	synchronized
	void deposit(double amount) {
		balance=balance+amount;
		transactions.add(new Transaction("Indsat", amount, balance) );
	}

	synchronized
	void withdraw(double amount) {
		balance=balance-amount;
		transactions.add(new Transaction("Hævet", -amount, balance) );
	}
	
	void printTransactions() {
		System.out.println(this);
		System.out.println("Tekst"+"\t"+"Dato"+"\t\t"+"Beløb"+"\t"+"Saldo");
		for (Transaction t: transactions) {
			System.out.println(t);
		}
		System.out.println();
	}

	void anualInterest() {	// Til brug for BankApp.BankApp3
		double interest=balance*interestRate/100;
		balance=balance+interest;
	  transactions.add(new Transaction("Renter", interest, balance));
	}

	public String toString() {
		return "Konto "+accountNo+": "+owner+" "+balance;
	}
}

class Transaction {
	String text;
	double amount;
	double newBalance;
	LocalDate date;
	
	Transaction(String t, double a, double nb) {
		text=t;
		amount=a;
		newBalance=nb;
		date=LocalDate.now();
	}
	
	public String toString() {
		return text+"\t"+date+"\t"+amount+"\t"+newBalance;
//		return String.format("%s\t%s\t%6.2f\t%6.2f", text, date, amount, newBalance);
	}
}