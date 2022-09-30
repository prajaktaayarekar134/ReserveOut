package com.stackroute.paymentservice.repository;


import com.stackroute.paymentservice.model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill, String> {

}
