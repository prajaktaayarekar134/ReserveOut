package com.stackroute.paymentservice.service;


import com.stackroute.paymentservice.exception.BillNotFoundException;
import com.stackroute.paymentservice.model.Bill;
import com.stackroute.paymentservice.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
	
	@Autowired
	private BillRepository billrepo;

	public Bill saveBill(Bill b) {
		return billrepo.save(b);


	}

	public Bill getBillDetailsByPaymentId(String paymentId) {
		if(billrepo.findById(paymentId)!=null) {
		return  billrepo.findById(paymentId).get();
		}
		else throw new BillNotFoundException("Could not get bill details");
	}

}
