package pkgUT;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.LinkedList;
import org.junit.Test;
import app.controller.Payment;
import app.controller.Loan;

public class TestLoan {
	@Test
	public void testTwoDecimals() {	
		assertEquals(0.00, Loan.twoDecimals(0.00), 0.01);
		assertEquals(1.00, Loan.twoDecimals(1.00), 0.01);
		assertEquals(1.01, Loan.twoDecimals(1.013992), 0.01);
		assertEquals(1.01, Loan.twoDecimals(1.0099999), 0.01);	
	}
	@Test
	public void testLoan() {
		StudentLoan sl = new Loan(150000, 0.07, 20, LocalDate.parse("2019-08-28"), 100);
		assertEquals(106293.16, sl.getdTotalInterest(), 0.01);
		assertEquals(237241.80, sl.getdTotalPayments(), 0.01);
		assertEquals(1162.95, sl.getPMT(), 0.01);
		LinkedList<Payment> payments = sl.getPayments();
		assertEquals(204, payments.size());
	}
}
