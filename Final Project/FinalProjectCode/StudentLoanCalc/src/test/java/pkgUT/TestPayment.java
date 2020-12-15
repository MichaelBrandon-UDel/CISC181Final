package pkgUT;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import app.controller.Payment;

public class TestPayment {
	@Test
	public void testGetters() {
		Payment test = new Payment(1, LocalDate.parse("2007-09-20"), 3.00, 4.00, 
				5.00, 6.00, 7.00);	
		assertEquals(test.getId(), 1);
		assertEquals(test.getDueDate(), "2007-09-20");
		assertEquals(test.getPayment(), 3.00, 0.01);
		assertEquals(test.getAdditionalPayment(), 4.00, 0.01);
		assertEquals(test.getInterest(), 5.00, 0.01);
		assertEquals(test.getPrincipal(), 6.00, 0.01);
		assertEquals(test.getBalance(), 7.00, 0.01);
	}
	@Test
	public void testSetters() {
		Payment test = new Payment(0, LocalDate.parse("2007-09-20"), 2.00, 3.00, 
				4.00, 5.00, 6.00);
		test.setId(1);
		test.setDueDate(LocalDate.parse("2007-09-21"));
		test.setPayment(3.00);
		test.setAdditionalPayment(4.00);
		test.setInterest(5.00);
		test.setPrincipal(6.00);
		test.setBalance(7.00);
		assertEquals(test.getId(), 1);
		assertEquals(test.getDueDate(), "2007-09-21");
		assertEquals(test.getPayment(), 3.00, 0.01);
		assertEquals(test.getAdditionalPayment(), 4.00, 0.01);
		assertEquals(test.getInterest(), 5.00, 0.01);
		assertEquals(test.getPrincipal(), 6.00, 0.01);
		assertEquals(test.getBalance(), 7.00, 0.01);
	}

}
