package com.stackroute.paymentservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.paymentservice.exception.BillNotFoundException;
import com.stackroute.paymentservice.model.Bill;
import com.stackroute.paymentservice.repository.BillRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BillServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BillServiceImplTest {
    @MockBean
    private BillRepository billRepository;

    @Autowired
    private BillServiceImpl billServiceImpl;


    @Test
    void testSaveBill() {
        Bill bill = new Bill();
        bill.setCurrency("USD");
        bill.setPayerEmail("alex.mark@example.org");
        bill.setPayerFirstName("Alex");
        bill.setPayerLastName("Mark");
        bill.setPaymentId("102");
        bill.setPaymentMethod("Paypal");
        bill.setPaymentMode("INSTANT_TRANSFER");
        bill.setPaymentStatus("complete");
        bill.setPaymentTime(LocalDateTime.now());
        bill.setTotalAmount("10");
        when(billRepository.save((Bill) any())).thenReturn(bill);

        Bill bill1 = new Bill();
        bill1.setCurrency("USD");
        bill1.setPayerEmail("ana.jelly@example.org");
        bill1.setPayerFirstName("Ana");
        bill1.setPayerLastName("Jelly");
        bill1.setPaymentId("103");
        bill1.setPaymentMethod("Paypal");
        bill1.setPaymentMode("INSTANT_TRANSFER");
        bill1.setPaymentStatus("complete");
        bill.setPaymentTime(LocalDateTime.now());
        bill1.setTotalAmount("10");
        assertSame(bill, billServiceImpl.saveBill(bill1));
        verify(billRepository).save((Bill) any());
    }


    @Test
    void testSaveBill2() {
        when(billRepository.save((Bill) any())).thenThrow(new BillNotFoundException("An error occurred"));

        Bill bill = new Bill();
        bill.setCurrency("USD");
        bill.setPayerEmail("noa.altis@example.org");
        bill.setPayerFirstName("Noa");
        bill.setPayerLastName("Altis");
        bill.setPaymentId("105");
        bill.setPaymentMethod("Paypal");
        bill.setPaymentMode("INSTANT_TRANSFER");
        bill.setPaymentStatus("complete");
        bill.setPaymentTime(LocalDateTime.now());
        bill.setTotalAmount("10");
        assertThrows(BillNotFoundException.class, () -> billServiceImpl.saveBill(bill));
        verify(billRepository).save((Bill) any());
    }

    @Test
    void testGetBillDetailsByPaymentId() {
        Bill bill = new Bill();
        bill.setCurrency("USD");
        bill.setPayerEmail("smith.tk@example.org");
        bill.setPayerFirstName("Smith");
        bill.setPayerLastName("Tk");
        bill.setPaymentId("104");
        bill.setPaymentMethod("Paypal");
        bill.setPaymentMode("INSTANT_TRANSFER");
        bill.setPaymentStatus("Payment Status");
        bill.setPaymentTime(LocalDateTime.now());
        bill.setTotalAmount("10");
        Optional<Bill> ofResult = Optional.of(bill);
        when(billRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(bill, billServiceImpl.getBillDetailsByPaymentId("105"));
        verify(billRepository, atLeast(1)).findById((String) any());
    }


    @Test
    void testGetBillDetailsByPaymentId2() {
        when(billRepository.findById((String) any())).thenReturn(null);
        assertThrows(BillNotFoundException.class, () -> billServiceImpl.getBillDetailsByPaymentId("105"));
        verify(billRepository).findById((String) any());
    }

    @Test
    void testGetBillDetailsByPaymentId4() {
        when(billRepository.findById((String) any())).thenThrow(new BillNotFoundException("An error occurred"));
        assertThrows(BillNotFoundException.class, () -> billServiceImpl.getBillDetailsByPaymentId("104"));
        verify(billRepository).findById((String) any());
    }
}

