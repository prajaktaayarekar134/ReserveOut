package com.stackroute.paymentservice.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import java.util.NoSuchElementException;

import com.stackroute.paymentservice.model.Bill;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;





@DataMongoTest
public class BillRepositoryTest {

	@Autowired
	private BillRepository billRepository;
	private Bill bill;

	@BeforeEach
	public void setUp() throws Exception {
		bill = new Bill();
		bill.setPaymentId("100");
		bill.setCurrency("USD");
		bill.setPayerEmail("ana@gmail.com");
		bill.setPayerFirstName("Ana");
		bill.setPayerLastName("Js");
		bill.setPaymentMethod("Instant");
		bill.setPaymentMode("paypal");
		bill.setPaymentStatus("completed");
		bill.setPaymentTime(LocalDateTime.now());
		bill.setTotalAmount("10.00");
		
		billRepository.deleteById("100");
	}

	@AfterEach
	public void tearDown() throws Exception {

		billRepository.deleteById("100");

	}

	@Test
	public void savebillTest() {

		billRepository.insert(bill);
		Bill fetchbill = billRepository.findById("100").get();
		Assert.assertEquals(bill.getPayerEmail(), fetchbill.getPayerEmail());
	}
	
	 @Test
	    public void deletebillTest() {
		 billRepository.insert(bill);
	        Bill fetchbill = billRepository.findById("100").get();
	        Assert.assertEquals(bill.getPayerEmail(), fetchbill.getPayerEmail());
	        billRepository.delete(fetchbill);
	        assertThrows(NoSuchElementException.class, () -> {
	        	billRepository.findById("100").get();
			});
	    }

	 @Test
	    public void updatebillTest() {
		 billRepository.insert(bill);
	        Bill fetchedbill = billRepository.findById("100").get();
	        fetchedbill.setCurrency("USD");
	        billRepository.save(fetchedbill);
	        fetchedbill = billRepository.findById("100").get();
	        Assert.assertEquals("USD", fetchedbill.getCurrency());
	    }

	    @Test
	    public void getbillByIdTest() {
	    	billRepository.insert(bill);
	        Bill fetchedbill = billRepository.findById("100").get();
	        Assert.assertEquals(bill.getPaymentId(),fetchedbill.getPaymentId());

	    }

}
