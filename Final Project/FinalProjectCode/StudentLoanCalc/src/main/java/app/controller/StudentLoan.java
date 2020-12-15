package app.controller;

import java.time.LocalDate;
import java.util.LinkedList;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class Loan {
	private double PMT;
	private LinkedList<Payment> payments = new LinkedList<Payment>();
	private double dTotalPayments = 0.0, dTotalInterest = 0.0;
	public Loan(double dLoanAmount, double dInterestRate, double dTerm, 
			LocalDate ld, double dAdditionalPayments) {	
		this.PMT = Math.abs(
				FinanceLib.pmt(
						dInterestRate / 12, 
						dTerm * 12, 
						dLoanAmount, 
						0,
						false)
				);
		
		double dInterest = 0.0, dPrincipal, dEndingBalance = dLoanAmount, 
				dPreviousEndingBalance;
		boolean bTerminate = false;		
		for(int i = 1; i < dTerm * 12; i++) {
			dPreviousEndingBalance = dEndingBalance;
			if(bTerminate) {
				break;
			}
			
			if(dEndingBalance == dInterest) {
				bTerminate = true;
			}
			
			if(dEndingBalance != 0.00) {
				dInterest = dEndingBalance * (dInterestRate/12);
				dPrincipal = PMT - dInterest + dAdditionalPayments;
			} else {
				dInterest = 0.00;
				dPrincipal = 0.00;
				dAdditionalPayments = 0.00;
				PMT = 0.00;
				break;
			}
					
			if(PMT + dAdditionalPayments <= dEndingBalance)
				dEndingBalance -= dPrincipal;			
				else
				dEndingBalance = 0.0;
			
			ld = ld.plusMonths(1L);
			
			if(dEndingBalance <= 0.00 && dAdditionalPayments > 0.0) {
				dPrincipal = dPreviousEndingBalance - dInterest;
				dEndingBalance = dInterest;
			}
			
			this.payments.add(new Payment(
				i,
				ld,
				twoDecimals(PMT),
				twoDecimals(dAdditionalPayments),
				twoDecimals(dInterest),
				twoDecimals(dPrincipal),
				twoDecimals(dEndingBalance)
					));
		}
				
		for(int i = 0; i < payments.size(); i++) {
			this.dTotalPayments += payments.get(i).getPayment();
			this.dTotalInterest += payments.get(i).getInterest();
		}
	}
	
	public static double twoDecimals(double d) {
		if((d * 100) % 1 <= 0.5)
			return Math.floor(d * 100) / 100;
		return Math.ceil(d * 100) / 100;
	}

	public double getPMT() {
		return PMT;
	}

	public LinkedList<Payment> getPayments() {
		return payments;
	}

	public double getdTotalPayments() {
		return Loan.twoDecimals(dTotalPayments);
	}

	public double getdTotalInterest() {
		return Loan.twoDecimals(dTotalInterest);
	}
}
