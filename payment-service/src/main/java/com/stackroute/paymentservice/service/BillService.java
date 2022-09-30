package com.stackroute.paymentservice.service;


import com.stackroute.paymentservice.model.Bill;

public interface BillService {

	
	Bill saveBill(Bill b);
	Bill getBillDetailsByPaymentId(String paymentId);
}
